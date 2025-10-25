package de.fuballer.mcendgame.main.component.block.totem_statue

import net.minecraft.entity.LivingEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos

data class TotemStatueSpawnEnemiesCommand(
    val world: ServerWorld,
    val positions: List<BlockPos>,
    var enemies: List<LivingEntity> = listOf(),
)