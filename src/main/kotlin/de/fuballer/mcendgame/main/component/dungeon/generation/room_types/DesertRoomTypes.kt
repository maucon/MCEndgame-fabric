package de.fuballer.mcendgame.main.component.dungeon.generation.room_types

import de.fuballer.mcendgame.main.component.dungeon.generation.data.RoomType
import de.fuballer.mcendgame.main.messaging.server.ServerStartedEvent
import de.fuballer.mcendgame.main.util.random.RandomOption
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber

@Injectable
object DesertRoomTypes {
    lateinit var START_ROOM: RoomType
    lateinit var BOSS_ROOM: RoomType
    lateinit var ROOMS: List<RandomOption<RoomType>>

    @EventSubscriber(sync = true)
    fun on(event: ServerStartedEvent) {
        val templateManager = event.server.structureTemplateManager

        START_ROOM = RoomTypeLoader.load(templateManager, "dungeon/desert/start")

        BOSS_ROOM = RoomTypeLoader.load(templateManager, "dungeon/desert/boss")

        ROOMS = listOf(
            RandomOption(7, RoomTypeLoader.load(templateManager, "dungeon/desert/small_connector")),
            RandomOption(4, RoomTypeLoader.load(templateManager, "dungeon/desert/arches_red-sand-side-path_curve")),
            RandomOption(4, RoomTypeLoader.load(templateManager, "dungeon/desert/arches_red-sand-side-path_mini-drop")),
            RandomOption(4, RoomTypeLoader.load(templateManager, "dungeon/desert/chandelier-above-fountain")),
            RandomOption(4, RoomTypeLoader.load(templateManager, "dungeon/desert/levels_drop-into-pool_curve")),
            RandomOption(4, RoomTypeLoader.load(templateManager, "dungeon/desert/library_curve")),
            RandomOption(4, RoomTypeLoader.load(templateManager, "dungeon/desert/square_center-flower-pool_lecterns")),
            RandomOption(4, RoomTypeLoader.load(templateManager, "dungeon/desert/stairs-in-between-levels_curve")),
            RandomOption(1, RoomTypeLoader.load(templateManager, "dungeon/desert/stairwell_branching")),
        )
    }
}