package de.fuballer.mcendgame.components.entity.custom.entities.mount

import de.fuballer.mcendgame.components.entity.custom.entities.arachne.ArachneEntity
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.passive.AbstractHorseEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

abstract class MountEntity(
    type: EntityType<out ArachneEntity>,
    world: World,
    private val tameFood: Map<Item, Double>,
) : AbstractHorseEntity(type, world) {
    abstract val passengerPos: Vec3d

    override fun getPassengerAttachmentPos(
        passenger: Entity,
        dimensions: EntityDimensions,
        scaleFactor: Float
    ): Vec3d {
        var pos = passengerPos.multiply(scaleFactor.toDouble())
        pos = pos.rotateY(-bodyYaw * (Math.PI / 180F).toFloat())
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
                forwardMovement *= 0.25f
            }
            val sidewaysMovement = controllingPlayer.sidewaysSpeed * 0.5f * speed

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
                }

                return ActionResult.SUCCESS_SERVER
            }
            if (world.isClient) {
                return ActionResult.CONSUME
            }
        }

        return super.interactMob(player, hand)
    }
}