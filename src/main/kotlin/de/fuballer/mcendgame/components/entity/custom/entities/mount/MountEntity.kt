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

abstract class MountEntity(
    type: EntityType<out ArachneEntity>,
    world: World,
    private val tameFood: Map<Item, Double>,
) : AbstractHorseEntity(type, world) {
    open val backwardsSpeedMulti = 0.25F
    open val sidewaysSpeedMulti = 0.5F
    abstract val passengerPos: Vec3d

    val idleAnimationState = AnimationState()
    val walkAnimationState = AnimationState()
    val walkBWAnimationState = AnimationState()
    val walkLeftAnimationState = AnimationState()
    val walkRightAnimationState = AnimationState()

    companion object {
        val MOVEMENT_POSE: TrackedData<CustomPosesEntity.CustomPose> =
            DataTracker.registerData(MountEntity::class.java, CustomPosesEntity.CUSTOM_POSE_TDH)
    }

    override fun initDataTracker(builder: DataTracker.Builder) {
        super.initDataTracker(builder)
        builder.add(MOVEMENT_POSE, CustomPosesEntity.CustomPose.IDLING)
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
        if (this.isOnGround && this.jumpStrength == 0.0f && this.isAngry && !this.jumping) {
            return Vec3d.ZERO
        } else {
            val speed = getAttributeValue(EntityAttributes.MOVEMENT_SPEED)

            var forwardMovement = controllingPlayer.forwardSpeed * speed
            if (forwardMovement <= 0.0f) {
                forwardMovement *= backwardsSpeedMulti
            }
            val sidewaysMovement = controllingPlayer.sidewaysSpeed * sidewaysSpeedMulti * speed

            return Vec3d(sidewaysMovement, 0.0, forwardMovement)
        }
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

    private fun getMovementDirectionSpeedMultiplier() = when (dataTracker.get(MOVEMENT_POSE)) {
        CustomPosesEntity.CustomPose.IDLING -> 0F
        CustomPosesEntity.CustomPose.WALKING -> 1F
        CustomPosesEntity.CustomPose.WALKING_BW -> backwardsSpeedMulti
        CustomPosesEntity.CustomPose.WALKING_LEFT,
        CustomPosesEntity.CustomPose.WALKING_RIGHT -> sidewaysSpeedMulti

        else -> 0F
    }

    fun getAnimationMovementSpeed(): Float {
        val attribute = getAttributeValue(EntityAttributes.MOVEMENT_SPEED).toFloat()
        val directionFactor = getMovementDirectionSpeedMultiplier()
        return attribute * directionFactor
    }

    override fun getInventoryColumns() = 3
}