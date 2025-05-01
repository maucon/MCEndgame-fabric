package de.fuballer.mcendgame.components.dungeon.type.data

import de.fuballer.mcendgame.components.dungeon.generation.layout.DungeonLayoutType
import de.fuballer.mcendgame.components.entity.EntityTypeStats
import de.fuballer.mcendgame.util.random.RandomOption

data class RolledDungeonType(
    val layoutType: DungeonLayoutType,
    val entityTypes: List<RandomOption<EntityTypeStats>>,
    val bossEntityTypes: List<EntityTypeStats>
)