package de.fuballer.mcendgame.main.component.entity.types

import de.fuballer.mcendgame.main.component.entity.EntityTypeStats
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.mob.HuskEntity

object HuskStats : EntityTypeStats {
    override val type: EntityType<HuskEntity> = EntityType.HUSK

    override val canHaveWeapons = true
    override val isRanged = false
    override val canHaveArmor = true
    override val canBeInvisible = true

    override val health = 25.0
    override val attackDamage = 5.0
    override val movementSpeed = 0.23
    override val knockbackResistance = 0.0

    override fun applyMisc(entity: Entity) {
        val huskEntity = entity as? HuskEntity ?: return
        huskEntity.isBaby = false
    }
}