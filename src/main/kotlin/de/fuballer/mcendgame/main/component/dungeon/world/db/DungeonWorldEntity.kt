package de.fuballer.mcendgame.main.component.dungeon.world.db

import de.maucon.mauconframework.stereotype.Entity
import net.minecraft.server.world.ServerWorld
import java.time.Instant
import java.util.*

data class DungeonWorldEntity(
    override var id: UUID,

    var world: ServerWorld,
    var emptySince: Instant = Instant.now()
) : Entity<UUID> {
    constructor(
        world: ServerWorld,
    ) : this(UUID.randomUUID(), world)
}