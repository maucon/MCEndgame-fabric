package de.fuballer.mcendgame.main.functional.scheduler

import de.maucon.mauconframework.stereotype.Entity
import java.util.*

data class Task(
    override var id: UUID,
    val runnable: Runnable,
    val startTick: Int,
    val period: Int = -1,
) : Entity<UUID> {
    constructor(
        runnable: Runnable,
        startTick: Int,
        period: Int = -1,
    ) : this(UUID.randomUUID(), runnable, startTick, period)
}
