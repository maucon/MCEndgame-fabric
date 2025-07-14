package de.fuballer.mcendgame.main.component.dungeon.world.db

import de.maucon.mauconframework.stereotype.Entity
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import net.minecraft.server.world.ServerWorld
import java.util.*

data class DungeonWorldEntity(
    override var id: UUID,

    var world: ServerWorld,
    var emptySince: Instant = Clock.System.now()
) : Entity<UUID> {
    constructor(
        world: ServerWorld,
    ) : this(UUID.randomUUID(), world)
}