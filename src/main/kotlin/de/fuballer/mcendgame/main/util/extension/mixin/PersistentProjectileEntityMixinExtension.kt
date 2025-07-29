package de.fuballer.mcendgame.main.util.extension.mixin

import de.fuballer.mcendgame.main.accessor.PersistentProjectileEntityIsAdditionalAccessor
import de.fuballer.mcendgame.main.accessor.PersistentProjectileEntityPierceLevelAccessor
import net.minecraft.entity.projectile.PersistentProjectileEntity

object PersistentProjectileEntityMixinExtension {
    fun PersistentProjectileEntity.setLoadProcessed(processed: Boolean = true) = (this as PersistentProjectileEntityIsAdditionalAccessor).`mcendgame$setLoadProcessed`(processed)

    fun PersistentProjectileEntity.hasLoadBeenProcessed() = (this as PersistentProjectileEntityIsAdditionalAccessor).`mcendgame$hasLoadBeenProcessed`()

    fun PersistentProjectileEntity.setIsAdditional(isAdditional: Boolean = true) = (this as PersistentProjectileEntityIsAdditionalAccessor).`mcendgame$setIsAdditional`(isAdditional)

    fun PersistentProjectileEntity.isAdditional() = (this as PersistentProjectileEntityIsAdditionalAccessor).`mcendgame$isAdditional`()

    fun PersistentProjectileEntity.setPierceLevel(level: Byte) = (this as PersistentProjectileEntityPierceLevelAccessor).`mcendgame$callSetPierceLevel`(level)
}