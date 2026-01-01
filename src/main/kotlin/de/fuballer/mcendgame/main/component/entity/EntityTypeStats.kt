package de.fuballer.mcendgame.main.component.entity

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.mob.MobEntity

interface EntityTypeStats {
    val type: EntityType<out MobEntity>

    val canHaveWeapons: Boolean
    val isRanged: Boolean
    val canHaveArmor: Boolean
    val canBeInvisible: Boolean

    val health: Double
    val attackDamage: Double
    val movementSpeed: Double
    val knockbackResistance: Double

    fun applyMisc(entity: Entity) {}
}