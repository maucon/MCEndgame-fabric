package de.fuballer.mcendgame.main.messaging.dungeon

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity
import net.minecraft.world.World

/**
 * FIXME currently only thrown server-side
 */
data class DungeonBossDeathEvent(
    val isClient: Boolean,
    val world: World,
    val bossEntity: MobEntity,
    val killer: LivingEntity?,
)