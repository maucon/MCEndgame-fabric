package de.fuballer.mcendgame.components.dungeon.generation.room_types

import de.fuballer.mcendgame.components.dungeon.generation.data.RoomType
import de.fuballer.mcendgame.framework.event.server.ServerStartedEvent
import de.fuballer.mcendgame.util.random.RandomOption
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.util.math.BlockPos

@Injectable
object StrongholdRoomTypes {
    lateinit var START_ROOM: RoomType
    lateinit var BOSS_ROOM: RoomType
    lateinit var ROOMS: List<RandomOption<RoomType>>

    @EventSubscriber
    fun on(event: ServerStartedEvent) {
        val templateManager = event.server.structureTemplateManager

        START_ROOM = RoomTypeLoader.load(templateManager, "dungeon/stronghold/start")

        BOSS_ROOM = RoomTypeLoader.load(
            templateManager, "dungeon/stronghold/boss",
            mapOf(
                "dungeon/stronghold/boss2" to BlockPos(0, 0, 48)
            )
        )

        ROOMS = listOf(
            RandomOption(4, RoomTypeLoader.load(templateManager, "dungeon/stronghold/arches_side-stairs")),
            RandomOption(4, RoomTypeLoader.load(templateManager, "dungeon/stronghold/arena")),
            RandomOption(4, RoomTypeLoader.load(templateManager, "dungeon/stronghold/around-bars_workshop-corner_curve")),
            RandomOption(4, RoomTypeLoader.load(templateManager, "dungeon/stronghold/bridge_dive_plantrooms")),
            RandomOption(3, RoomTypeLoader.load(templateManager, "dungeon/stronghold/copper-sewers")),
            RandomOption(1, RoomTypeLoader.load(templateManager, "dungeon/stronghold/decayed-staircase_branching")),
            RandomOption(7, RoomTypeLoader.load(templateManager, "dungeon/stronghold/flat_chandelier_branching")),
            RandomOption(1, RoomTypeLoader.load(templateManager, "dungeon/stronghold/giant_tower")),
            RandomOption(3, RoomTypeLoader.load(templateManager, "dungeon/stronghold/inverted-pyramid_curve")),
            RandomOption(4, RoomTypeLoader.load(templateManager, "dungeon/stronghold/loop-around-chandelier_curve")),
            RandomOption(1, RoomTypeLoader.load(templateManager, "dungeon/stronghold/parcour_curve")),
            RandomOption(4, RoomTypeLoader.load(templateManager, "dungeon/stronghold/slimebounce")),
            RandomOption(7, RoomTypeLoader.load(templateManager, "dungeon/stronghold/small_connector_curve")),
            RandomOption(7, RoomTypeLoader.load(templateManager, "dungeon/stronghold/small_connector_sloped")),
            RandomOption(3, RoomTypeLoader.load(templateManager, "dungeon/stronghold/small_sewers")),
            RandomOption(5, RoomTypeLoader.load(templateManager, "dungeon/stronghold/small_stair-between-barrels_curve")),
            RandomOption(5, RoomTypeLoader.load(templateManager, "dungeon/stronghold/stair_chandelier_sidedrop_curve")),
            RandomOption(7, RoomTypeLoader.load(templateManager, "dungeon/stronghold/stair_elevated_branching")),
            RandomOption(3, RoomTypeLoader.load(templateManager, "dungeon/stronghold/stairs_statue")),
            RandomOption(4, RoomTypeLoader.load(templateManager, "dungeon/stronghold/tight_hanging-lamps_curve")),
            RandomOption(7, RoomTypeLoader.load(templateManager, "dungeon/stronghold/tiny_flat_connector")),
            RandomOption(4, RoomTypeLoader.load(templateManager, "dungeon/stronghold/tunnelbridge_curve")),
            RandomOption(4, RoomTypeLoader.load(templateManager, "dungeon/stronghold/zigzag-stairs_ponds")),
            RandomOption(4, RoomTypeLoader.load(templateManager, "dungeon/stronghold/zigzag_waterfalls_head_curve")),
        )
    }
}