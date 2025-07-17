package de.fuballer.mcendgame.main.messaging.dungeon

import net.minecraft.entity.LivingEntity
import net.minecraft.world.World

data class DungeonEnemyDeathEvent(
    val isClient: Boolean,
    val world: World,
    val enemyEntity: LivingEntity,
    val killer: LivingEntity?,
)