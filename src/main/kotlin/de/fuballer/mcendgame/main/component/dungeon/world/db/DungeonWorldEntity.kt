package de.fuballer.mcendgame.main.component.dungeon.world.db

import de.maucon.mauconframework.stereotype.Entity
import net.minecraft.server.world.ServerWorld
import java.util.*
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class DungeonWorldEntity @OptIn(ExperimentalTime::class) constructor(
    override var id: UUID,

    var world: ServerWorld,
    var emptySince: Instant = Clock.System.now()
) : Entity<UUID> {
    constructor(
        world: ServerWorld,
    ) : this(UUID.randomUUID(), world)
}