package de.fuballer.mcendgame.components.entity.types

import de.fuballer.mcendgame.components.entity.EntityTypeStats
import net.minecraft.entity.EntityType
import net.minecraft.entity.mob.WitherSkeletonEntity

object WitherSkeletonStats : EntityTypeStats {
    override val type: EntityType<WitherSkeletonEntity> = EntityType.WITHER_SKELETON

    override val canHaveWeapons = true
    override val isRanged = false
    override val canHaveArmor = true
    override val canBeInvisible = true

    override val baseHealth = 20.0
    override val healthPerTier = 0.0
    override val baseDamage = 5.0
    override val damagePerTier = 2.5
    override val baseSpeed = 0.23
    override val speedPerTier = 0.0
    override val knockbackResistance = 0.0
}