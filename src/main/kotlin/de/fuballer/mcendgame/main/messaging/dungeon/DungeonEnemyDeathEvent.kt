package de.fuballer.mcendgame.main.messaging.dungeon

import de.fuballer.mcendgame.main.component.dungeon.enemy.DungeonEnemyEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.world.World

data class DungeonEnemyDeathEvent(
    val isClient: Boolean,
    val world: World,
    val enemyEntity: DungeonEnemyEntity,
    val killer: LivingEntity?,
)