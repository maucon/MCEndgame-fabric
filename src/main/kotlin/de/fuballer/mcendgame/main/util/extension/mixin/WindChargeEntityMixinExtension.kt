package de.fuballer.mcendgame.main.util.extension.mixin

import de.fuballer.mcendgame.main.accessor.WindChargeEntityExplosionPowerAccessor
import net.minecraft.entity.projectile.WindChargeEntity

object WindChargeEntityMixinExtension {
    fun WindChargeEntity.setExplosionPower(power: Float) {
        val accessor = this as WindChargeEntityExplosionPowerAccessor
        accessor.`mcendgame$setExplosionPower`(power)
    }
}