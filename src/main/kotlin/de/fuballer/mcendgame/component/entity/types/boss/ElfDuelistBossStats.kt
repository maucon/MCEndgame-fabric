package de.fuballer.mcendgame.component.entity.types.boss

import de.fuballer.mcendgame.component.entity.EntityTypeStats
import de.fuballer.mcendgame.component.entity.custom.CustomEntities
import de.fuballer.mcendgame.component.entity.custom.entities.elf_duelist.ElfDuelistEntity
import net.minecraft.entity.EntityType

object ElfDuelistBossStats : EntityTypeStats {
    override val type: EntityType<ElfDuelistEntity> = CustomEntities.ELF_DUELIST

    override val canHaveWeapons = false
    override val isRanged = false
    override val canHaveArmor = false
    override val canBeInvisible = false

    override val baseHealth = 100.0
    override val healthPerTier = 10.0
    override val baseDamage = 5.0
    override val damagePerTier = 1.5
    override val baseSpeed = 0.33
    override val speedPerTier = 0.0
    override val knockbackResistance = 0.8
}