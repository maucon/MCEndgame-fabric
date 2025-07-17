package de.fuballer.mcendgame.main.util.extension.mixin

import de.fuballer.mcendgame.main.accessor.PlayerEntityDungeonLevelAccessor
import de.fuballer.mcendgame.main.accessor.PlayerEntityMixinAccessor
import de.fuballer.mcendgame.main.component.dungeon.level.PlayerDungeonLevel
import net.minecraft.entity.player.PlayerEntity

object PlayerEntityMixinExtension {
    fun PlayerEntity.getDungeonLevel(): PlayerDungeonLevel {
        val accessor = this as PlayerEntityDungeonLevelAccessor
        return accessor.`mcendgame$getDungeonLevel`()
    }

    fun PlayerEntity.setDungeonLevel(dungeonLevel: PlayerDungeonLevel) {
        val accessor = this as PlayerEntityDungeonLevelAccessor
        return accessor.`mcendgame$setDungeonLevel`(dungeonLevel)
    }

    fun PlayerEntity.getAttackCooldownMultiplier(): Float {
        val accessor = this as PlayerEntityMixinAccessor
        return accessor.`mcendgame$getLastAttackCharge`()
    }

    fun PlayerEntity.wasLastAttackCritical(): Boolean {
        val accessor = this as PlayerEntityMixinAccessor
        return accessor.`mcendgame$getLastAttackWasCritical`()
    }
}