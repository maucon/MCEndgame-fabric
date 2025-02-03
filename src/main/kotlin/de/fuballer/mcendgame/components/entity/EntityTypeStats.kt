package de.fuballer.mcendgame.components.entity

import net.minecraft.entity.EntityType
import net.minecraft.entity.mob.MobEntity

interface EntityTypeStats {
    val type: EntityType<out MobEntity>

    val canHaveWeapons: Boolean
    val isRanged: Boolean
    val canHaveArmor: Boolean
    val canBeInvisible: Boolean

    val baseHealth: Double
    val healthPerTier: Double
    val baseDamage: Double
    val damagePerTier: Double
    val baseSpeed: Double
    val speedPerTier: Double
    val knockbackResistance: Double
}