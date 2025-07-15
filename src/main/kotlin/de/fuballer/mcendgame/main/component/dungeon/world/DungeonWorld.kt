package de.fuballer.mcendgame.main.component.dungeon.world

import de.fuballer.mcendgame.main.accessor.DungeonWorldAccessor
import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem
import net.minecraft.server.world.ServerWorld

class DungeonWorld(
    val world: ServerWorld
) {
    companion object {
        fun ServerWorld.toDungeonWorld(): DungeonWorld {
            return DungeonWorld(this)
        }
    }

    private val accessor = world as DungeonWorldAccessor

    var isCompleted: Boolean
        get() = accessor.`mcendgame$isCompleted`()
        set(value) = accessor.`mcendgame$setCompleted`(value)

    fun setCompleted() {
        isCompleted = true
    }

    var level: Int
        get() = accessor.`mcendgame$getLevel`()
        set(value) = accessor.`mcendgame$setLevel`(value)

    var aspects: Map<AspectItem, Int>
        get() = accessor.`mcendgame$getAspects`()
        set(value) = accessor.`mcendgame$setAspects`(value)
}