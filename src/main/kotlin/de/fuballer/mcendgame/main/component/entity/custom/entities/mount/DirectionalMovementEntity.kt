package de.fuballer.mcendgame.main.component.entity.custom.entities.mount

import de.fuballer.mcendgame.main.component.entity.custom.entities.arachne.ArachneEntity
import de.fuballer.mcendgame.main.component.entity.custom.interfaces.CustomPosesEntity
import net.minecraft.entity.AnimationState
import net.minecraft.entity.EntityType
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.PathAwareEntity
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import kotlin.math.PI
import kotlin.math.abs

abstract class DirectionalMovementEntity(
    type: EntityType<out ArachneEntity>,
    world: World,
) : PathAwareEntity(type, world) {
    val idleAnimationState = AnimationState()
    val walkAnimationState = AnimationState()
    val walkBWAnimationState = AnimationState()
    val walkLeftAnimationState = AnimationState()
    val walkRightAnimationState = AnimationState()

    companion object {
        val MOVEMENT_POSE: TrackedData<CustomPosesEntity.CustomPose> =
            DataTracker.registerData(DirectionalMovementEntity::class.java, CustomPosesEntity.CUSTOM_POSE_TDH)
        val ANIMATION_MOVEMENT_SPEED: TrackedData<Float> =
            DataTracker.registerData(DirectionalMovementEntity::class.java, TrackedDataHandlerRegistry.FLOAT)
    }

    override fun initDataTracker(builder: DataTracker.Builder) {
        super.initDataTracker(builder)
        builder.add(MOVEMENT_POSE, CustomPosesEntity.CustomPose.IDLING)
        builder.add(ANIMATION_MOVEMENT_SPEED, 0F)
    }

    override fun onTrackedDataSet(data: TrackedData<*>) {
        if (data == MOVEMENT_POSE && world.isClient) {
            when (dataTracker.get(MOVEMENT_POSE)) {
                CustomPosesEntity.CustomPose.IDLING -> startMovementAnimation(idleAnimationState)
                CustomPosesEntity.CustomPose.WALKING -> startMovementAnimation(walkAnimationState)
                CustomPosesEntity.CustomPose.WALKING_BW -> startMovementAnimation(walkBWAnimationState)
                CustomPosesEntity.CustomPose.WALKING_LEFT -> startMovementAnimation(walkLeftAnimationState)
                CustomPosesEntity.CustomPose.WALKING_RIGHT -> startMovementAnimation(walkRightAnimationState)

                else -> {}
            }
        }
        super.onTrackedDataSet(data)
    }

    private fun stopMovementAnimations() {
        idleAnimationState.stop()
        walkAnimationState.stop()
        walkBWAnimationState.stop()
        walkLeftAnimationState.stop()
        walkRightAnimationState.stop()
    }

    open fun startMovementAnimation(animationState: AnimationState) {
        if (animationState.isRunning) return
        stopMovementAnimations()
        animationState.start(age)
    }

    override fun tick() {
        super.tick()
        updateMovementState()
    }

    open fun updateMovementState() {
        if (world.isClient) return

        updateMovementPose()
        updateAnimationMovementSpeed()
    }

    open fun updateMovementPose() {
        val currentPose = dataTracker.get(MOVEMENT_POSE)
        val newPose = when (getRelativeMovementDirection()) {
            MovementDirection.NONE -> CustomPosesEntity.CustomPose.IDLING
            MovementDirection.FORWARD -> CustomPosesEntity.CustomPose.WALKING
            MovementDirection.BACKWARD -> CustomPosesEntity.CustomPose.WALKING_BW
            MovementDirection.LEFT -> CustomPosesEntity.CustomPose.WALKING_LEFT
            MovementDirection.RIGHT -> CustomPosesEntity.CustomPose.WALKING_RIGHT
        }
        if (currentPose == newPose) return

        dataTracker.set(MOVEMENT_POSE, newPose)
    }

    private fun updateAnimationMovementSpeed() {
        val currentMovementSpeed = dataTracker.get(ANIMATION_MOVEMENT_SPEED)
        var newMovementSpeed = movementSpeed
        if (abs(currentMovementSpeed - newMovementSpeed) < 0.01) return
        dataTracker.set(ANIMATION_MOVEMENT_SPEED, newMovementSpeed)
    }

    private fun getRelativeMovement(): Vec3d = movement.rotateY(bodyYaw / 180F * PI.toFloat())

    private fun getRelativeMovementDirection(): MovementDirection {
        val relativeMovement = getRelativeMovement()
        if (abs(relativeMovement.x) + abs(relativeMovement.z) < 0.05) return MovementDirection.NONE

        if (relativeMovement.z < -0.05) return MovementDirection.BACKWARD
        if (relativeMovement.z > 0.05) return MovementDirection.FORWARD
        if (relativeMovement.x > 0.05) return MovementDirection.LEFT
        if (relativeMovement.x < -0.05) return MovementDirection.RIGHT

        return MovementDirection.FORWARD
    }
}