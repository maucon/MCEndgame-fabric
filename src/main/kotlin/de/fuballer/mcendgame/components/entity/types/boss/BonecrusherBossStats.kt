package de.fuballer.mcendgame.components.entity.types.boss

import de.fuballer.mcendgame.components.entity.EntityTypeStats
import de.fuballer.mcendgame.components.entity.custom.CustomEntities
import de.fuballer.mcendgame.components.entity.custom.entities.bonecrusher.BonecrusherEntity
import net.minecraft.entity.EntityType

object BonecrusherBossStats : EntityTypeStats {
    override val type: EntityType<BonecrusherEntity> = CustomEntities.BONECRUSHER

    override val canHaveWeapons = false
    override val isRanged = false
    override val canHaveArmor = false
    override val canBeInvisible = false

    override val baseHealth = 100.0
    override val healthPerTier = 10.0
    override val baseDamage = 10.0
    override val damagePerTier = 2.5
    override val baseSpeed = 0.25
    override val speedPerTier = 0.0
    override val knockbackResistance = 0.8
}