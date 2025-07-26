package de.fuballer.mcendgame.main.util.extension.mixin

import de.fuballer.mcendgame.main.accessor.PersistentProjectileEntityIsAdditionalAccessor
import net.minecraft.entity.projectile.PersistentProjectileEntity

object PersistentProjectileEntityMixinExtension {
    fun PersistentProjectileEntity.setIsAdditional(isAdditional: Boolean = true) = (this as PersistentProjectileEntityIsAdditionalAccessor).`mcendgame$setIsAdditional`(isAdditional)

    fun PersistentProjectileEntity.isAdditional() = (this as PersistentProjectileEntityIsAdditionalAccessor).`mcendgame$isAdditional`()
}