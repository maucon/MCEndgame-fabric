package de.fuballer.mcendgame.main.component.dungeon.generation.encounter.encounters.scarred_one.messaging

import net.minecraft.server.world.ServerWorld

data class CollectScarredOneEffectCountCommand(
    val world: ServerWorld,
    var positive: Int,
    var negative: Int,
)