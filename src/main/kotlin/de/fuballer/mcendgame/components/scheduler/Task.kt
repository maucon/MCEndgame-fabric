package de.fuballer.mcendgame.components.scheduler

import de.maucon.mauconframework.stereotype.Entity
import java.util.*

data class Task(
    override var id: UUID,
    val runnable: Runnable,
    val inMinecraftThread: Boolean,
    val startTick: Int,
    val period: Int
) : Entity<UUID> {
    constructor(
        runnable: Runnable,
        inMinecraftThread: Boolean,
        startTick: Int,
        period: Int
    ) : this(UUID.randomUUID(), runnable, inMinecraftThread, startTick, period)
}
