package de.fuballer.mcendgame.main.component.entity

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.mob.MobEntity

abstract class EntityTypeStats {
    abstract val type: EntityType<out MobEntity>

    abstract val canHaveWeapons: Boolean
    abstract val isRanged: Boolean
    abstract val canHaveArmor: Boolean
    abstract val canBeInvisible: Boolean

    abstract val baseHealth: Double
    abstract val healthPerTier: Double
    abstract val baseDamage: Double
    abstract val damagePerTier: Double
    abstract val baseSpeed: Double
    abstract val speedPerTier: Double
    abstract val knockbackResistance: Double

    open val applyMisc: (entity: Entity) -> Unit = {}
}