package de.fuballer.mcendgame.event

import net.minecraft.entity.LivingEntity
import net.minecraft.world.World

class DungeonBossDeathEvent(
    val isClient: Boolean,
    val world: World,
    val entity: LivingEntity,
    val killer: LivingEntity?,
)