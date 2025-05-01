package de.fuballer.mcendgame.component.entity.custom.util

import de.fuballer.mcendgame.component.entity.custom.interfaces.DisableAbleGoalsMob
import net.minecraft.entity.mob.MobEntity

class BlockedMovementManager<T>(
    val entity: T,
) where T : MobEntity, T : DisableAbleGoalsMob {
    private var blockedTicks = 0

    fun tick() {
        updateBlockMovementTicks()
    }

    private fun updateBlockMovementTicks() {
        if (blockedTicks <= 0) return
        entity.moveControl.strafeTo(0F, 0F)
        if (--blockedTicks > 0) return
        entity.updateGoals()
    }

    fun blockMovement(ticks: Int) {
        if (ticks <= 0) return
        val oldTicks = blockedTicks
        blockedTicks = ticks
        if (oldTicks > 0) return

        entity.updateGoals()
        entity.navigation.stop()
    }

    fun isBlocked() = blockedTicks > 0
}