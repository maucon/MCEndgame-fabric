package de.fuballer.mcendgame.main.component.entity.types

import de.fuballer.mcendgame.main.component.entity.EntityTypeStats
import net.minecraft.entity.EntityType
import net.minecraft.entity.mob.StrayEntity

object StrayStats : EntityTypeStats {
    override val type: EntityType<StrayEntity> = EntityType.STRAY

    override val canHaveWeapons = true
    override val isRanged = true
    override val canHaveArmor = true
    override val canBeInvisible = true

    override val baseHealth = 15.0
    override val healthPerTier = 0.0
    override val baseDamage = 4.0
    override val damagePerTier = 2.0
    override val baseSpeed = 0.25
    override val speedPerTier = 0.0
    override val knockbackResistance = 0.0
}