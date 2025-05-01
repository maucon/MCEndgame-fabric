package de.fuballer.mcendgame.components.dungeon.generation.layout

import de.fuballer.mcendgame.components.dungeon.generation.room_types.NetherRoomTypes
import de.fuballer.mcendgame.components.dungeon.generation.room_types.StrongholdRoomTypes

enum class DungeonLayoutType(
    val layoutGeneratorProvider: () -> LayoutGenerator
) {
    STRONGHOLD({ LinearLayoutGenerator(StrongholdRoomTypes.START_ROOM, StrongholdRoomTypes.BOSS_ROOM, StrongholdRoomTypes.ROOMS) }),
    NETHER({ LinearLayoutGenerator(NetherRoomTypes.START_ROOM, NetherRoomTypes.BOSS_ROOM, NetherRoomTypes.ROOMS) }),
}