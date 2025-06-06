package de.fuballer.mcendgame.main.messaging.dungeon

import net.minecraft.entity.LivingEntity
import net.minecraft.world.World

data class DungeonEntityDeathEvent(
    val isClient: Boolean,
    val world: World,
    val entity: LivingEntity,
    val killer: LivingEntity?,
)