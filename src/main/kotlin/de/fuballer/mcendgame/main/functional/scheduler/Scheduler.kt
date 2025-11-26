package de.fuballer.mcendgame.main.functional.scheduler

import de.fuballer.mcendgame.main.configuration.RuntimeConfig
import de.fuballer.mcendgame.main.messaging.server.ServerEndTickEvent
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

            if (task.period == NOT_REPEATING) {
                task.runnable.run()
                taskRepo.delete(task)

                return@forEach
            }

            if (tickDifference % task.period != 0) return@forEach
            task.runnable.run()
        }
    }

    fun repeating(period: Int, runnable: Runnable): UUID {
        return repeating(0, period, runnable)
    }

    fun repeating(delay: Int, period: Int, runnable: Runnable): UUID {
        assert(period > 0)

        val startTick = RuntimeConfig.SERVER.ticks + delay
        val task = Task(runnable, startTick, period)

        return taskRepo.save(task).id
    }

    fun delayed(delay: Int, runnable: Runnable): UUID {
        val startTick = RuntimeConfig.SERVER.ticks + delay
        val task = Task(runnable, startTick)

        return taskRepo.save(task).id
    }
}