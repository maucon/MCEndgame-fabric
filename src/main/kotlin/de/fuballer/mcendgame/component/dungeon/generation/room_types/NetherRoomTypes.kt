package de.fuballer.mcendgame.component.dungeon.generation.room_types

import de.fuballer.mcendgame.component.dungeon.generation.data.RoomType
import de.fuballer.mcendgame.messaging.server.ServerStartedEvent
import de.fuballer.mcendgame.util.random.RandomOption
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber

@Injectable
object NetherRoomTypes {
    lateinit var START_ROOM: RoomType
    lateinit var BOSS_ROOM: RoomType
    lateinit var ROOMS: List<RandomOption<RoomType>>

    @EventSubscriber
    fun on(event: ServerStartedEvent) {
        val templateManager = event.server.structureTemplateManager

        START_ROOM = RoomTypeLoader.load(templateManager, "dungeon/nether/start")

        BOSS_ROOM = RoomTypeLoader.load(templateManager, "dungeon/nether/boss")

        ROOMS = listOf(
            RandomOption(7, RoomTypeLoader.load(templateManager, "dungeon/nether/small_connector")),
            RandomOption(4, RoomTypeLoader.load(templateManager, "dungeon/nether/basalt-lava-pools_branching")),
            RandomOption(3, RoomTypeLoader.load(templateManager, "dungeon/nether/bridge-over-warped_brewing-room_curve")),
            RandomOption(4, RoomTypeLoader.load(templateManager, "dungeon/nether/around-burning-soulsand_elevating_curve")),
            RandomOption(4, RoomTypeLoader.load(templateManager, "dungeon/nether/soulsand-basalt-arena_spiral_curve")),
            RandomOption(4, RoomTypeLoader.load(templateManager, "dungeon/nether/tunnel-path-around-lava")),
            RandomOption(2, RoomTypeLoader.load(templateManager, "dungeon/nether/blackstone-isles-over-magma_curve")),
        )
    }
}