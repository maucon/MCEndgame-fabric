package de.fuballer.mcendgame.main.functional.scheduler

import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.stereotype.extension.InMemoryMapRepository
import java.util.*

@Injectable
class TaskRepository : InMemoryMapRepository<UUID, Task>()