package de.fuballer.mcendgame.components.entity

import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity

interface EntityTypeStats {
    val type: EntityType<out LivingEntity>

    val canHaveWeapons: Boolean
    val isRanged: Boolean
    val canHaveArmor: Boolean

    val baseHealth: Double
    val healthPerTier: Double
    val baseDamage: Double
    val damagePerTier: Double
    val baseSpeed: Double
    val speedPerTier: Double
    val knockbackResistance: Double
}