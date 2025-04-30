package de.fuballer.mcendgame.framework.event.dungeon

import net.minecraft.entity.LivingEntity
import net.minecraft.world.World

data class DungeonBossDeathEvent(
    val isClient: Boolean,
    val world: World,
    val entity: LivingEntity,
    val killer: LivingEntity?,
)