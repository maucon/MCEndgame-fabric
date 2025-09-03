package de.fuballer.mcendgame.main.component.entity.types

import de.fuballer.mcendgame.main.component.entity.EntityTypeStats
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.mob.HuskEntity

object HuskStats : EntityTypeStats() {
    override val type: EntityType<HuskEntity> = EntityType.HUSK

    override val canHaveWeapons = true
    override val isRanged = false
    override val canHaveArmor = true
    override val canBeInvisible = true

    override val baseHealth = 25.0
    override val healthPerTier = 0.0
    override val baseDamage = 5.0
    override val damagePerTier = 2.5
    override val baseSpeed = 0.23
    override val speedPerTier = 0.0
    override val knockbackResistance = 0.0

    override val applyMisc: (entity: Entity) -> Unit = applyMisc@{ entity ->
        val huskEntity = entity as? HuskEntity ?: return@applyMisc
        huskEntity.isBaby = false
    }
}