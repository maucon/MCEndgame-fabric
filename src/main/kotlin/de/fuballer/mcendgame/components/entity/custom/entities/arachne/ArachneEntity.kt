package de.fuballer.mcendgame.components.entity.custom.entities.arachne

import de.fuballer.mcendgame.components.block.CustomBlocks
import de.fuballer.mcendgame.components.entity.custom.CustomEntities
import de.fuballer.mcendgame.components.entity.custom.entities.mount.MountEntity
import de.fuballer.mcendgame.components.entity.custom.entities.webhook.WebhookEntity
import de.fuballer.mcendgame.components.entity.custom.entities.webshot.WebshotEntity
import de.fuballer.mcendgame.components.entity.custom.goals.HookAttackGoal
import de.fuballer.mcendgame.components.entity.custom.goals.KeepDistanceToTargetGoal
import de.fuballer.mcendgame.components.entity.custom.goals.MountThrowOffPassengerGoal
import de.fuballer.mcendgame.components.entity.custom.goals.TameableActiveTargetGoal
import de.fuballer.mcendgame.components.entity.custom.interfaces.CustomPosesEntity
import de.fuballer.mcendgame.components.entity.custom.interfaces.HookAttackMob
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
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
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
import java.util.*
import kotlin.math.sqrt

class ArachneEntity(
    type: EntityType<out ArachneEntity>,
    world: World,
) : MountEntity(type, world, TAME_FOOD), Monster, RangedAttackMob, HookAttackMob {
    override val passengerPos = Vec3d(0.0, 0.75, -0.65)
    override val riddenSpeedMulti = 1.5
    override val backwardsSpeedMulti = 0.5
    override val sidewaysSpeedMulti = 0.5

    private var attackAnimationTime = 0
    val spitAnimationState = AnimationState()

    override val hooker = this
    override val hookPullCount = 3
    override val hookPullInterval = 15
    override val hookPullStrength = 1.0
    override val hookPullAdditionalY = 0.2
    override val hookedEntityUuidMap = mutableMapOf<UUID, Pair<Int, Int>>()
    override val hookedEntityIds = mutableListOf<Int>()

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

        val ATTACK_POSE: TrackedData<CustomPosesEntity.CustomPose> =
            DataTracker.registerData(ArachneEntity::class.java, CustomPosesEntity.CUSTOM_POSE_TDH)
    }

    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))
        goalSelector.add(1, MountThrowOffPassengerGoal(this, 1.2))
        //goalSelector.add(2, NoMovementProjectileAttackGoal(this, 50, 15F))
        goalSelector.add(3, HookAttackGoal(this, 50, 15F))
        goalSelector.add(4, KeepDistanceToTargetGoal(this, 1.0, 10F, 15F))
        goalSelector.add(7, WanderAroundFarGoal(this, 1.0))
        goalSelector.add(8, LookAtEntityGoal(this, PlayerEntity::class.java, 8.0f))
        goalSelector.add(8, LookAroundGoal(this))

        targetSelector.add(1, RevengeGoal(this))
        targetSelector.add(2, TameableActiveTargetGoal(this, PlayerEntity::class.java, true))
        targetSelector.add(3, TameableActiveTargetGoal(this, VillagerEntity::class.java, true))
    }

    override fun initDataTracker(builder: DataTracker.Builder) {
        super.initDataTracker(builder)
        builder.add(ATTACK_POSE, CustomPosesEntity.CustomPose.IDLING)
    }

    override fun onTrackedDataSet(data: TrackedData<*>) {
        if (data == ATTACK_POSE) {
            when (dataTracker.get(ATTACK_POSE)) {
                CustomPosesEntity.CustomPose.SPITTING -> {
                    spitAnimationState.start(age)
                }

                else -> {}
            }
        }
        super.onTrackedDataSet(data)
    }

    override fun tick() {
        super.tick()
        updateAttackPose()
        tickHooks()
    }

    private fun updateAttackPose() {
        if (world.isClient) return
        if (attackAnimationTime <= 0) return
        if (--attackAnimationTime > 0) return

        dataTracker.set(ATTACK_POSE, CustomPosesEntity.CustomPose.IDLING)
    }

    private fun changeAttackPose(pose: CustomPosesEntity.CustomPose, animationTime: Int) {
        if (attackAnimationTime > 0) return
        dataTracker.set(ATTACK_POSE, pose)
        attackAnimationTime = animationTime
    }

    override fun startMovementAnimation(animationState: AnimationState) {
        if (animationState == walkLeftAnimationState || animationState == walkRightAnimationState)
            return super.startMovementAnimation(walkAnimationState)

        super.startMovementAnimation(animationState)
    }

    private fun shootAt(
        target: LivingEntity,
        projectile: ProjectileEntity,
    ) {
        val serverWorld = world as? ServerWorld ?: return

        val xDistance = target.x - x
        val zDistance = target.z - z
        val aimY = target.eyeY - 1.1f
        val addedYVelocity = sqrt(xDistance * xDistance + zDistance * zDistance) * 0.2f

        changeAttackPose(CustomPosesEntity.CustomPose.SPITTING, 9)// anim is 0.42s

        val itemStack = ItemStack(Items.AIR)
        ProjectileEntity.spawn(projectile, serverWorld, itemStack)
        { entity: ProjectileEntity ->
            entity.setVelocity(xDistance, aimY - entity.y + addedYVelocity, zDistance, 1.6f, 2.0f)
        }
    }

    override fun shootAt(
        target: LivingEntity,
        pullProgress: Float,
    ) {
        val serverWorld = world as? ServerWorld ?: return
        val projectile = WebshotEntity(CustomEntities.WEBSHOT, serverWorld, this)
        shootAt(target, projectile)
    }

    override fun shootHookAt(
        target: LivingEntity,
    ) {
        val serverWorld = world as? ServerWorld ?: return
        val projectile = WebhookEntity(CustomEntities.WEBHOOK, serverWorld, this)
        shootAt(target, projectile)
        addHookedEntity(projectile.uuid)
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

    override fun getLeashOffset() = Vec3d(0.0, standingEyeHeight * 0.9, width * 0.4)
}