package de.fuballer.mcendgame.main.component.dungeon.player

import de.fuballer.mcendgame.main.accessor.PlayerEntityDungeonLevelAccessor
import de.fuballer.mcendgame.main.component.dungeon.level.PlayerDungeonLevel
import net.minecraft.entity.player.PlayerEntity

class DungeonPlayerEntity(
    val playerEntity: PlayerEntity,
) {
    companion object {
        fun PlayerEntity.toDungeonPlayerEntity() = DungeonPlayerEntity(this)
    }

    private val accessor = playerEntity as PlayerEntityDungeonLevelAccessor

    var dungeonLevel: PlayerDungeonLevel
        get() = accessor.`mcendgame$getDungeonLevel`()
        set(value) = accessor.`mcendgame$setDungeonLevel`(value)
}
