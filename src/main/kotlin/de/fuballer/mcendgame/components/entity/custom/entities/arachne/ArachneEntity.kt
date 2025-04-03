package de.fuballer.mcendgame.components.entity.custom.entities.arachne

import de.fuballer.mcendgame.components.block.CustomBlocks
import de.fuballer.mcendgame.components.entity.custom.CustomEntities
import de.fuballer.mcendgame.components.entity.custom.entities.mount.MountEntity
import de.fuballer.mcendgame.components.entity.custom.entities.webshot.WebshotEntity
import de.fuballer.mcendgame.components.entity.custom.goals.MountThrowOffPassengerGoal
import de.fuballer.mcendgame.components.entity.custom.goals.StrafeProjectileAttackGoal
import de.fuballer.mcendgame.components.entity.custom.goals.TameableActiveTargetGoal
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.entity.AnimationState
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.RangedAttackMob
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.mob.Monster
import net.minecraft.entity.passive.VillagerEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.ProjectileEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import kotlin.math.sqrt

class ArachneEntity(
    type: EntityType<out ArachneEntity>,
    world: World,
) : MountEntity(type, world, TAME_FOOD), Monster, RangedAttackMob {
    override val passengerPos = Vec3d(0.0, 0.75, -0.65)
    override val backwardsSpeedMulti = 0.5F
    override val sidewaysSpeedMulti = 0.5F

    companion object {
        val TAME_FOOD = mapOf<Item, Double>(Items.ROTTEN_FLESH to 0.1)

        fun createAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.FOLLOW_RANGE, 35.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.4)
                .add(EntityAttributes.STEP_HEIGHT, 1.1)
                .add(EntityAttributes.SAFE_FALL_DISTANCE, 10.0)
                .add(EntityAttributes.FALL_DAMAGE_MULTIPLIER, 0.2)
                .add(EntityAttributes.ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.ARMOR, 0.0)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, 0.8)
                .add(EntityAttributes.MOVEMENT_EFFICIENCY, 0.85)
        }
    }

    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))
        goalSelector.add(1, MountThrowOffPassengerGoal(this, 1.2))
        goalSelector.add(2, StrafeProjectileAttackGoal(this, 1.0, 40, 10F))
        goalSelector.add(7, WanderAroundFarGoal(this, 1.0))
        goalSelector.add(8, LookAtEntityGoal(this, PlayerEntity::class.java, 8.0f))
        goalSelector.add(8, LookAroundGoal(this))

        targetSelector.add(1, RevengeGoal(this))
        targetSelector.add(2, TameableActiveTargetGoal(this, PlayerEntity::class.java, true))
        targetSelector.add(3, TameableActiveTargetGoal(this, VillagerEntity::class.java, true))
    }

    override fun startMovementAnimation(animationState: AnimationState) {
        if (animationState == walkLeftAnimationState || animationState == walkRightAnimationState)
            return super.startMovementAnimation(walkAnimationState)

        super.startMovementAnimation(animationState)
    }

    override fun shootAt(
        target: LivingEntity,
        pullProgress: Float,
    ) {
        val serverWorld = world as? ServerWorld ?: return

        val xDistance = target.x - x
        val zDistance = target.z - z
        val aimY = target.eyeY - 1.1f
        val addedYVelocity = sqrt(xDistance * xDistance + zDistance * zDistance) * 0.2f

        val itemStack = ItemStack(Items.SNOWBALL)
        ProjectileEntity.spawn(WebshotEntity(CustomEntities.WEBSHOT, serverWorld, this), serverWorld, itemStack)
        { entity: WebshotEntity ->
            entity.setVelocity(xDistance, aimY - entity.y + addedYVelocity, zDistance, 1.6f, 2.0f)
        }
    }

    override fun slowMovement(
        state: BlockState,
        multiplier: Vec3d
    ) {
        onLanding()
        if (state.isOf(Blocks.COBWEB)) return
        if (state.isOf(CustomBlocks.DECAYING_COBWEB)) return
        movementMultiplier = multiplier
    }

    override fun handleFallDamage(fallDistance: Float, damageMultiplier: Float, damageSource: DamageSource) = false

    override fun occludeVibrationSignals() = true

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.ENTITY_SPIDER_AMBIENT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_SPIDER_DEATH
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.ENTITY_SPIDER_HURT
    }

    override fun getAngrySound(): SoundEvent {
        return SoundEvents.ENTITY_SPIDER_HURT
    }

    override fun playEatSound() {
        playSound(SoundEvents.ENTITY_SPIDER_AMBIENT, 1.0f, 1.0f)
    }

    override fun playWalkSound(group: BlockSoundGroup) {
        this.playSound(SoundEvents.ENTITY_SPIDER_STEP, group.getVolume() * 0.15f, group.getPitch())
    }

    override fun playStepSound(
        pos: BlockPos,
        state: BlockState
    ) {
        if (state.isLiquid) return

        val blockState = world.getBlockState(pos.up())
        val blockSoundGroup = if (blockState.isOf(Blocks.SNOW)) blockState.soundGroup else state.soundGroup

        if (!hasPassengers()) {
            playWalkSound(blockSoundGroup)
            return
        }

        if (++soundTicks > 5 && soundTicks % 2 != 0) return
        playWalkSound(blockSoundGroup)
    }
}