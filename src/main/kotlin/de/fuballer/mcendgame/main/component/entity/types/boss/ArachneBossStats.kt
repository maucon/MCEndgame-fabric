package de.fuballer.mcendgame.main.component.entity.types.boss

import de.fuballer.mcendgame.main.component.entity.EntityTypeStats
import de.fuballer.mcendgame.main.component.entity.custom.CustomEntities
import de.fuballer.mcendgame.main.component.entity.custom.entities.arachne.ArachneEntity
import net.minecraft.entity.EntityType

object ArachneBossStats : EntityTypeStats {
    override val type: EntityType<ArachneEntity> = CustomEntities.ARACHNE

    override val canHaveWeapons = false
    override val isRanged = false
    override val canHaveArmor = false
    override val canBeInvisible = false

    override val baseHealth = 100.0
    override val healthPerTier = 10.0
    override val baseDamage = 10.0
    override val damagePerTier = 2.5
    override val baseSpeed = 0.3
    override val speedPerTier = 0.0
    override val knockbackResistance = 0.8
}