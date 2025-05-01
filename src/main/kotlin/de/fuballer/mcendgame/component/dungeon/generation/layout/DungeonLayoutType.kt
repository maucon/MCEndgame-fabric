package de.fuballer.mcendgame.component.dungeon.generation.layout

import de.fuballer.mcendgame.component.dungeon.generation.room_types.StrongholdRoomTypes

enum class DungeonLayoutType(
    val layoutGeneratorProvider: () -> LayoutGenerator
) {
    STRONGHOLD({ LinearLayoutGenerator(StrongholdRoomTypes.START_ROOM, StrongholdRoomTypes.BOSS_ROOM, StrongholdRoomTypes.ROOMS) })
}