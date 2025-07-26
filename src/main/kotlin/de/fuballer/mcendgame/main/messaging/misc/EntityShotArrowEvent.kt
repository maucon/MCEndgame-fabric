package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.item.ItemStack

data class EntityShotArrowEvent(
    val arrow: PersistentProjectileEntity,
    val owner: LivingEntity,
    val weapon: ItemStack?,
) {
    companion object {
        fun of(arrow: PersistentProjectileEntity, owner: LivingEntity) = EntityShotArrowEvent(arrow, owner, arrow.weaponStack)
    }
}