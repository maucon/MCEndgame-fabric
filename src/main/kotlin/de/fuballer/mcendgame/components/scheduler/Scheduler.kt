package de.fuballer.mcendgame.components.scheduler

import de.fuballer.mcendgame.configuration.RuntimeConfig
import de.maucon.mauconframework.initializer.Initializer
import de.maucon.mauconframework.di.annotation.Injectable
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import java.util.*

@Injectable
class Scheduler(
    private val taskRepo: TaskRepository
) {
    @Initializer
    fun init() = ServerTickEvents.END_SERVER_TICK.register { server ->
        taskRepo.findAll().forEach { task ->
            val tickDifference = server.ticks - task.startTick
            if (tickDifference < 0) return@forEach
            if (tickDifference % task.period != 0) return@forEach

            if (task.inMinecraftThread) {
                RuntimeConfig.SERVER.executeSync(task.runnable)
            } else {
                task.runnable.run()
            }
        }
    }

    fun repeating(delay: Int, period: Int, runnable: Runnable): UUID {
        return createRepeating(delay, period, runnable, false)
    }

    fun repeating(period: Int, runnable: Runnable): UUID {
        return repeating(0, period, runnable)
    }

    fun repeatingSafe(delay: Int, period: Int, runnable: Runnable): UUID {
        return createRepeating(delay, period, runnable, true)
    }

    fun repeatingSafe(period: Int, runnable: Runnable): UUID {
        return repeatingSafe(0, period, runnable)
    }

    private fun createRepeating(delay: Int, period: Int, runnable: Runnable, minecraftSafe: Boolean): UUID {
        assert(period > 0)

        val startTick = RuntimeConfig.SERVER.ticks + delay
        val task = Task(runnable, minecraftSafe, startTick, period)

        return taskRepo.save(task).id
    }
}