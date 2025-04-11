package de.fuballer.mcendgame.components.entity.custom.entities.mount

import de.fuballer.mcendgame.components.entity.custom.entities.arachne.ArachneEntity
import de.fuballer.mcendgame.components.entity.custom.interfaces.CustomPosesEntity
import net.minecraft.entity.AnimationState
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.passive.AbstractHorseEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.sign

abstract class MountEntity(
    type: EntityType<out ArachneEntity>,
    world: World,
    private val tameFood: Map<Item, Double>,
) : AbstractHorseEntity(type, world) {
    open val riddenSpeedMulti = 1.0
    open val backwardsSpeedMulti = 0.25
    open val sidewaysSpeedMulti = 0.5
    abstract val passengerPos: Vec3d

    val idleAnimationState = AnimationState()
    val walkAnimationState = AnimationState()
    val walkBWAnimationState = AnimationState()
    val walkLeftAnimationState = AnimationState()
    val walkRightAnimationState = AnimationState()

    companion object {
        val MOVEMENT_POSE: TrackedData<CustomPosesEntity.CustomPose> =
            DataTracker.registerData(MountEntity::class.java, CustomPosesEntity.CUSTOM_POSE_TDH)
        val ANIMATION_MOVEMENT_SPEED: TrackedData<Float> =
            DataTracker.registerData(MountEntity::class.java, TrackedDataHandlerRegistry.FLOAT)
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

        if (isControlledByPlayer) {
            newMovementSpeed = getAttributeValue(EntityAttributes.MOVEMENT_SPEED).toFloat()
            newMovementSpeed *= riddenSpeedMulti.toFloat()
            newMovementSpeed *= getRiddenMovementDirectionSpeedMultiplier()
        }

        if (abs(currentMovementSpeed - newMovementSpeed) < 0.01) return
        dataTracker.set(ANIMATION_MOVEMENT_SPEED, newMovementSpeed)
    }

    override fun getPassengerAttachmentPos(
        passenger: Entity,
        dimensions: EntityDimensions,
        scaleFactor: Float
    ): Vec3d {
        var pos = passengerPos.multiply(scaleFactor.toDouble())
        pos = pos.rotateY(-bodyYaw * Math.PI.toFloat() / 180F)
        return pos
    }

    override fun getControlledMovementInput(
        controllingPlayer: PlayerEntity,
        movementInput: Vec3d
    ): Vec3d {
        if (isOnGround && jumpStrength == 0.0f && isAngry && !jumping) return Vec3d.ZERO

        val speed = getAttributeValue(EntityAttributes.MOVEMENT_SPEED) * riddenSpeedMulti

        var forwardMovement = sign(controllingPlayer.forwardSpeed.toDouble())
        if (forwardMovement < 0) forwardMovement *= backwardsSpeedMulti
        forwardMovement *= speed

        var sidewaysMovement = sign(controllingPlayer.sidewaysSpeed.toDouble())
        sidewaysMovement *= sidewaysSpeedMulti
        sidewaysMovement *= speed

        return Vec3d(sidewaysMovement, 0.0, forwardMovement)
    }

    override fun interactMob(
        player: PlayerEntity,
        hand: Hand
    ): ActionResult {
        val stack = player.getStackInHand(hand)
        val item = stack.item

        if (!isTame && tameFood.contains(item)) {
            if (!world.isClient && canEat()) {
                eat(player, hand, stack)
                playEatSound()

                if (random.nextDouble() < tameFood[item]!!) {
                    bondWithPlayer(player)
                    target = null
                }

                return ActionResult.SUCCESS_SERVER
            }
            if (world.isClient) {
                return ActionResult.CONSUME
            }
        }

        return super.interactMob(player, hand)
    }

    override fun isInvulnerableTo(world: ServerWorld, source: DamageSource): Boolean {
        if (firstPassenger != null && firstPassenger == source.attacker) return true
        return super.isInvulnerableTo(world, source)
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

    private fun getRiddenMovementDirectionSpeedMultiplier() = when (dataTracker.get(MOVEMENT_POSE)) {
        CustomPosesEntity.CustomPose.IDLING -> 0F
        CustomPosesEntity.CustomPose.WALKING -> 1F
        CustomPosesEntity.CustomPose.WALKING_BW -> backwardsSpeedMulti.toFloat()
        CustomPosesEntity.CustomPose.WALKING_LEFT,
        CustomPosesEntity.CustomPose.WALKING_RIGHT -> sidewaysSpeedMulti.toFloat()

        else -> 0F
    }

    override fun getInventoryColumns() = 3
}