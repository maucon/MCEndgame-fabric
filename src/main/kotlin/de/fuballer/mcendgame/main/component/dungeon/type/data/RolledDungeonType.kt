package de.fuballer.mcendgame.main.component.dungeon.type.data

import de.fuballer.mcendgame.main.component.dungeon.generation.layout.DungeonLayoutType
import de.fuballer.mcendgame.main.component.entity.EntityTypeStats
import de.fuballer.mcendgame.main.util.random.RandomOption

data class RolledDungeonType(
    val layoutType: DungeonLayoutType,
    val entityTypes: List<RandomOption<EntityTypeStats>>,
    val bossEntityTypes: List<EntityTypeStats>
)