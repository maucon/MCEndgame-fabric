package de.fuballer.mcendgame.components.entity.custom.swamp_golem

import de.fuballer.mcendgame.components.entity.custom.goals.SlamAttackGoal
import de.fuballer.mcendgame.components.entity.custom.interfaces.CustomPosesEntity
import de.fuballer.mcendgame.components.entity.custom.interfaces.SlamAttacker
import net.minecraft.entity.AnimationState
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.goal.ActiveTargetGoal
import net.minecraft.entity.ai.goal.LookAroundGoal
import net.minecraft.entity.ai.goal.LookAtEntityGoal
import net.minecraft.entity.ai.goal.WanderAroundFarGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.entity.passive.ChickenEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World

class SwampGolemEntity(
    type: EntityType<out SwampGolemEntity>,
    world: World,
) : HostileEntity(type, world), SlamAttacker {
    val slamAnimationState = AnimationState()
    val idleAnimationState = AnimationState()

    companion object {
        val CUSTOM_POSE = DataTracker.registerData(SwampGolemEntity::class.java, CustomPosesEntity.CUSTOM_POSE_TDH)

        fun createAttributes(): DefaultAttributeContainer.Builder {
            return createHostileAttributes()
                .add(EntityAttributes.FOLLOW_RANGE, 35.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.20)
                .add(EntityAttributes.ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.ARMOR, 5.0)
        }
    }

    //Slam Attacker properties
    override val slamAttacker = this
    override val slamRadius = 3.0
    override val minSlamStrength = 0.3
    override val slamCenterFacingOffset = 1.1
    override val applyScale = true
    override val knockbackStrength = 1.0
    override fun shouldDamage(target: LivingEntity) = target.isAlive

    override fun initGoals() {
        goalSelector.add(2, SlamAttackGoal(this, 1.0, 25, 17, 50))
        goalSelector.add(7, WanderAroundFarGoal(this, 1.0))
        goalSelector.add(8, LookAtEntityGoal(this, PlayerEntity::class.java, 8.0f))
        goalSelector.add(8, LookAroundGoal(this))

        targetSelector.add(1, ActiveTargetGoal(this, PlayerEntity::class.java, true))
        targetSelector.add(1, ActiveTargetGoal(this, ChickenEntity::class.java, true))
    }

    override fun initDataTracker(builder: DataTracker.Builder) {
        super.initDataTracker(builder)
        builder.add(CUSTOM_POSE, CustomPosesEntity.CustomPose.IDLING)
    }

    override fun onTrackedDataSet(data: TrackedData<*>) {
        if (data == CUSTOM_POSE) {
            when (dataTracker.get(CUSTOM_POSE)) {
                CustomPosesEntity.CustomPose.SLAMMING -> slamAnimationState.start(age)
                CustomPosesEntity.CustomPose.IDLING -> idleAnimationState.start(age)
                else -> {}
            }
        }
        super.onTrackedDataSet(data)
    }

    override fun rotate(yaw: Float, pitch: Float) {
        if (isRotationLocked()) return
        super.rotate(yaw, pitch)
    }

    override fun setYaw(yaw: Float) {
        if (isRotationLocked()) return
        super.setYaw(yaw)
    }

    override fun setPose(pose: CustomPosesEntity.CustomPose) {
        dataTracker.set(CUSTOM_POSE, pose)
    }

    private fun isRotationLocked() = dataTracker.get(CUSTOM_POSE) == CustomPosesEntity.CustomPose.SLAMMING
}