package de.fuballer.mcendgame.main.functional.scheduler

import de.maucon.mauconframework.stereotype.Entity
import java.util.*

const val NOT_REPEATING = -1

data class Task(
    override var id: UUID,
    val runnable: Runnable,
    val startTick: Int,
    val period: Int = NOT_REPEATING,
) : Entity<UUID> {
    constructor(
        runnable: Runnable,
        startTick: Int,
        period: Int = NOT_REPEATING,
    ) : this(UUID.randomUUID(), runnable, startTick, period)
}
