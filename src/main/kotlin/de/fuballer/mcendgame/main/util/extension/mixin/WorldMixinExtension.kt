package de.fuballer.mcendgame.main.util.extension.mixin

import de.fuballer.mcendgame.main.accessor.DungeonWorldAccessor
import de.fuballer.mcendgame.main.component.dungeon.type.DungeonType
import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.GlobalPos

object WorldMixinExtension {
    fun ServerWorld.setDungeonCompleted(completed: Boolean = true) {
        val accessor = this as DungeonWorldAccessor
        accessor.`mcendgame$setCompleted`(completed)
    }

    fun ServerWorld.isDungeonCompleted(): Boolean {
        val accessor = this as DungeonWorldAccessor
        return accessor.`mcendgame$isCompleted`()
    }

    fun ServerWorld.setDungeonLevel(dungeonLevel: Int) {
        val accessor = this as DungeonWorldAccessor
        accessor.`mcendgame$setLevel`(dungeonLevel)
    }

    fun ServerWorld.getDungeonLevel(): Int {
        val accessor = this as DungeonWorldAccessor
        return accessor.`mcendgame$getLevel`()
    }

    fun ServerWorld.setTotalBossCount(count: Int) {
        val accessor = this as DungeonWorldAccessor
        accessor.`mcendgame$setTotalBossCount`(count)
    }

    fun ServerWorld.getTotalBossCount(): Int {
        val accessor = this as DungeonWorldAccessor
        return accessor.`mcendgame$getTotalBossCount`()
    }

    fun ServerWorld.increaseBossesKilled() {
        val accessor = this as DungeonWorldAccessor
        accessor.`mcendgame$increaseBossesKilled`()
    }

    fun ServerWorld.getBossesKilled(): Int {
        val accessor = this as DungeonWorldAccessor
        return accessor.`mcendgame$getBossesKilled`()
    }

    fun ServerWorld.setOpener(opener: PlayerEntity) {
        val accessor = this as DungeonWorldAccessor
        accessor.`mcendgame$setOpener`(opener)
    }

    fun ServerWorld.getOpener(): PlayerEntity {
        val accessor = this as DungeonWorldAccessor
        return accessor.`mcendgame$getOpener`()
    }

    fun ServerWorld.setDungeonAspects(aspects: Map<AspectItem, Int>) {
        val accessor = this as DungeonWorldAccessor
        accessor.`mcendgame$setAspects`(aspects)
    }

    fun ServerWorld.getDungeonAspects(): Map<AspectItem, Int> {
        val accessor = this as DungeonWorldAccessor
        return accessor.`mcendgame$getAspects`()
    }

    fun ServerWorld.setDungeonType(type: DungeonType) {
        val accessor = this as DungeonWorldAccessor
        accessor.`mcendgame$setDungeonType`(type)
    }

    fun ServerWorld.getDungeonType(): DungeonType {
        val accessor = this as DungeonWorldAccessor
        return accessor.`mcendgame$getDungeonType`()
    }

    fun ServerWorld.setDungeonExitPos(pos: GlobalPos) {
        val accessor = this as DungeonWorldAccessor
        accessor.`mcendgame$setDungeonExitPos`(pos)
    }

    fun ServerWorld.getDungeonExitPos(): GlobalPos {
        val accessor = this as DungeonWorldAccessor
        return accessor.`mcendgame$getDungeonExitPos`()
    }
}