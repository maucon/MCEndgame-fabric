package de.fuballer.mcendgame.components.entity.custom.entities.arachne

import de.fuballer.mcendgame.components.entity.custom.entities.mount.MountEntity
import de.fuballer.mcendgame.components.entity.custom.goals.TameableActiveTargetGoal
import de.fuballer.mcendgame.components.entity.custom.interfaces.CustomPosesEntity
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.entity.AnimationState
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.mob.Monster
import net.minecraft.entity.passive.VillagerEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

class ArachneEntity(
    type: EntityType<out ArachneEntity>,
    world: World,
) : MountEntity(type, world, TAME_FOOD), Monster {
    override val passengerPos = Vec3d(0.0, 0.75, -0.65)

    private var prevPos = Vec3d.ZERO
    val walkAnimationState = AnimationState()

    companion object {
        val TAME_FOOD = mapOf<Item, Double>(Items.ROTTEN_FLESH to 0.1)

        val CUSTOM_POSE = DataTracker.registerData(ArachneEntity::class.java, CustomPosesEntity.CUSTOM_POSE_TDH)

        fun createAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.FOLLOW_RANGE, 35.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.4)
                .add(EntityAttributes.STEP_HEIGHT, 1.1)
                .add(EntityAttributes.ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.ARMOR, 0.0)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, 0.8)
                .add(EntityAttributes.MOVEMENT_EFFICIENCY, 0.85)
        }
    }

    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))
        //goalSelector.add(1, HorseBondWithPlayerGoal(this, 1.2))
        goalSelector.add(1, MeleeAttackGoal(this, 1.0, false))
        goalSelector.add(7, WanderAroundFarGoal(this, 1.0))
        goalSelector.add(8, LookAtEntityGoal(this, PlayerEntity::class.java, 8.0f))
        goalSelector.add(8, LookAroundGoal(this))

        targetSelector.add(1, TameableActiveTargetGoal(this, PlayerEntity::class.java, true))
        targetSelector.add(1, TameableActiveTargetGoal(this, VillagerEntity::class.java, true))
    }

    override fun initDataTracker(builder: DataTracker.Builder) {
        super.initDataTracker(builder)
        builder.add(CUSTOM_POSE, CustomPosesEntity.CustomPose.IDLING)
    }

    override fun onTrackedDataSet(data: TrackedData<*>) {
        if (data == CUSTOM_POSE) {
            when (dataTracker.get(CUSTOM_POSE)) {
                CustomPosesEntity.CustomPose.IDLING -> {
                    walkAnimationState.stop()
                    //idleAnimationState.start(age)
                }

                CustomPosesEntity.CustomPose.WALKING -> {
                    //idleAnimationState.stop()
                    walkAnimationState.start(age)
                }

                else -> {}
            }
        }
        super.onTrackedDataSet(data)
    }

    override fun tick() {
        super.tick()
        updateMovementState()
    }

    private fun updateMovementState() {
        if (world.isClient) return

        if (isMoving()) {
            if (dataTracker.get(CUSTOM_POSE) == CustomPosesEntity.CustomPose.WALKING) return
            dataTracker.set(CUSTOM_POSE, CustomPosesEntity.CustomPose.WALKING)
        } else {
            if (dataTracker.get(CUSTOM_POSE) == CustomPosesEntity.CustomPose.IDLING) return
            dataTracker.set(CUSTOM_POSE, CustomPosesEntity.CustomPose.IDLING)
        }
    }

    private fun isMoving(): Boolean {
        val change = prevPos.subtract(pos).length()
        prevPos = pos
        return change > 0.01
    }

    override fun getInventoryColumns() = 3

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