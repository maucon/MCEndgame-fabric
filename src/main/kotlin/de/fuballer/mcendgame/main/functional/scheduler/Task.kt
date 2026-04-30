package de.fuballer.mcendgame.main.functional.scheduler

import de.maucon.mauconframework.stereotype.Entity
import java.util.*

const val NOT_REPEATING = -1
const val INFINITE_REPEATING = -1

data class Task(
    override var id: UUID,
    val runnable: (Int) -> Unit,
    val startTick: Int,
    val period: Int = NOT_REPEATING,
    val repeatDuration: Int = INFINITE_REPEATING,
) : Entity<UUID> {
    constructor(
        runnable: (Int) -> Unit,
        startTick: Int,
        period: Int = NOT_REPEATING,
        repeatDuration: Int = NOT_REPEATING,
    ) : this(UUID.randomUUID(), runnable, startTick, period, repeatDuration)
}
