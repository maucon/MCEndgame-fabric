package de.fuballer.mcendgame.components.damage

import de.fuballer.mcendgame.accessors.PlayerCooldownAccessor
import net.minecraft.entity.Entity

object DamageUtil {
    fun getAttackCooldownMultiplier(entity: Entity?) =
        (entity as? PlayerCooldownAccessor)?.`mcendgame$getAttackCooldown`()
            ?: 1.0F
}