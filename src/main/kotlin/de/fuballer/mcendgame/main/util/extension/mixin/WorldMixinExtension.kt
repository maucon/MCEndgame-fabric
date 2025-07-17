package de.fuballer.mcendgame.main.util.extension.mixin

import de.fuballer.mcendgame.main.accessor.DungeonWorldAccessor
import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem
import net.minecraft.entity.LivingEntity
import net.minecraft.server.world.ServerWorld

object WorldMixinExtension {
    fun ServerWorld.setDungeonCompleted(completed: Boolean = true) {
        val accessor = this as DungeonWorldAccessor
        return accessor.`mcendgame$setCompleted`(completed)
    }

    fun ServerWorld.isDungeonCompleted(): Boolean {
        val accessor = this as DungeonWorldAccessor
        return accessor.`mcendgame$isCompleted`()
    }

    fun ServerWorld.setDungeonLevel(dungeonLevel: Int) {
        val accessor = this as DungeonWorldAccessor
        return accessor.`mcendgame$setLevel`(dungeonLevel)
    }

    fun ServerWorld.getDungeonLevel(): Int {
        val accessor = this as DungeonWorldAccessor
        return accessor.`mcendgame$getLevel`()
    }

    fun ServerWorld.setDungeonAspects(aspects: Map<AspectItem, Int>) {
        val accessor = this as DungeonWorldAccessor
        return accessor.`mcendgame$setAspects`(aspects)
    }

    fun ServerWorld.getDungeonAspects(): Map<AspectItem, Int> {
        val accessor = this as DungeonWorldAccessor
        return accessor.`mcendgame$getAspects`()
    }
}