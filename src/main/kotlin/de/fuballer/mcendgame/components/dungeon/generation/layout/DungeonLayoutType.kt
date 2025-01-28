package de.fuballer.mcendgame.components.dungeon.generation.layout

import de.fuballer.mcendgame.components.dungeon.generation.RoomTypes

enum class DungeonLayoutType(
    val layoutGeneratorProvider: () -> LayoutGenerator
) {
    STRONGHOLD({ LinearLayoutGenerator(RoomTypes.STRONGHOLD_START_ROOM, RoomTypes.STRONGHOLD_BOSS_ROOM, RoomTypes.STRONGHOLD_ROOMS) })
}