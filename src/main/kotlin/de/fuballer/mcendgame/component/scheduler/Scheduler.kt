package de.fuballer.mcendgame.component.scheduler

import de.fuballer.mcendgame.configuration.RuntimeConfig
import de.fuballer.mcendgame.messaging.server.ServerEndTickEvent
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import java.util.*

@Injectable
class Scheduler(
    private val taskRepo: TaskRepository
) {
    @EventSubscriber
    fun on(event: ServerEndTickEvent) {
        taskRepo.findAll().forEach { task ->
            val tickDifference = event.server.ticks - task.startTick
            if (tickDifference < 0) return@forEach
            if (tickDifference % task.period != 0) return@forEach

            task.runnable.run()
        }
    }

    fun repeating(delay: Int, period: Int, runnable: Runnable): UUID {
        return createRepeating(delay, period, runnable)
    }

    fun repeating(period: Int, runnable: Runnable): UUID {
        return repeating(0, period, runnable)
    }

    private fun createRepeating(delay: Int, period: Int, runnable: Runnable): UUID {
        assert(period > 0)

        val startTick = RuntimeConfig.SERVER.ticks + delay
        val task = Task(runnable, startTick, period)

        return taskRepo.save(task).id
    }
}