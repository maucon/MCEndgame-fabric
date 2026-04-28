package de.fuballer.mcendgame.main.util.extension.mixin

import de.fuballer.mcendgame.main.accessor.PersistentProjectileEntityDamageAccessor
import de.fuballer.mcendgame.main.accessor.PersistentProjectileEntityPierceLevelAccessor
import net.minecraft.entity.projectile.PersistentProjectileEntity

object PersistentProjectileEntityMixinExtension {
    fun PersistentProjectileEntity.setPierceLevel(level: Byte) = (this as PersistentProjectileEntityPierceLevelAccessor).`mcendgame$callSetPierceLevel`(level)

    fun PersistentProjectileEntity.getDamage() = (this as PersistentProjectileEntityDamageAccessor).`mcendgame$getDamage`()
}