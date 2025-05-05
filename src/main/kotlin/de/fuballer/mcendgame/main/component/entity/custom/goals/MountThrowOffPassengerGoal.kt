package de.fuballer.mcendgame.main.component.entity.custom.goals

import de.fuballer.mcendgame.main.component.entity.custom.entities.mount.MountEntity
import net.minecraft.entity.EntityStatuses
import net.minecraft.entity.ai.NoPenaltyTargeting
import net.minecraft.util.math.Vec3d
import java.util.*

class MountThrowOffPassengerGoal(
    private val mount: MountEntity,
    private val speed: Double,
) : DisableAbleGoal() {
    private var throwOffTimer = 0
    private var target = Vec3d.ZERO

    init {
        controls = EnumSet.of(Control.MOVE)
    }

    override fun canStart(): Boolean {
        if (isDisabled) return false
        if (mount.isTame) return false
        if (!mount.hasPassengers()) return false

        target = NoPenaltyTargeting.find(mount, 5, 4) ?: return false
        return true
    }

    override fun start() {
        mount.navigation.startMovingTo(target.x, target.y, target.z, speed)
        throwOffTimer = getTickCount(40 + mount.random.nextInt(80))
    }

    override fun shouldContinue(): Boolean {
        if (isDisabled) return false
        if (mount.isTame) return false
        if (!mount.hasPassengers()) return false
        return --throwOffTimer > 0
    }

    override fun stop() {
        mount.removeAllPassengers()
        mount.playAngrySound()
        mount.world.sendEntityStatus(mount, EntityStatuses.ADD_NEGATIVE_PLAYER_REACTION_PARTICLES)
    }

    override fun tick() {
        updateTarget()
    }

    private fun updateTarget() {
        if (!mount.navigation.isIdle) return
        target = NoPenaltyTargeting.find(mount, 5, 4) ?: return
        mount.navigation.startMovingTo(target.x, target.y, target.z, speed)
    }
}