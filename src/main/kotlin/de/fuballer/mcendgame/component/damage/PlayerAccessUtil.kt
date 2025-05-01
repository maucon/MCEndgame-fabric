package de.fuballer.mcendgame.component.damage

import de.fuballer.mcendgame.accessor.PlayerEntityMixinAccessor
import net.minecraft.entity.Entity

object PlayerAccessUtil {
    fun getAttackCooldownMultiplier(entity: Entity?) =
        (entity as? PlayerEntityMixinAccessor)?.`mcendgame$getLastAttackCharge`()
            ?: 1.0F

    fun getIsCritical(entity: Entity?) =
        (entity as? PlayerEntityMixinAccessor)?.`mcendgame$getLastAttackWasCritical`()
            ?: false
}