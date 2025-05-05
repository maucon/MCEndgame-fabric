package de.fuballer.mcendgame.main.component.dungeon.world.db

import de.maucon.mauconframework.stereotype.Entity
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import java.util.*

data class DungeonWorldEntity(
    override var id: UUID,

    var player: PlayerEntity,
    var world: ServerWorld,
    var emptySince: Instant = Clock.System.now()
) : Entity<UUID> {
    constructor(
        player: PlayerEntity,
        world: ServerWorld,
    ) : this(UUID.randomUUID(), player, world)
}