package de.fuballer.mcendgame.main.component.dungeon.generation.encounter.encounters.scarred_one.event

import de.fuballer.mcendgame.main.component.entity.custom.entities.scarred_one.ScarredOneEntity
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld

data class ScarredOneInteractEvent(
    val scarredOne: ScarredOneEntity,
    val player: ServerPlayerEntity,
    val serverWorld: ServerWorld,
)