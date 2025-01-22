package de.fuballer.mcendgame.components.scheduler

import de.maucon.mauconframework.annotation.Injectable
import de.maucon.mauconframework.extension.InMemoryMapRepository
import java.util.*

@Injectable
class TaskRepository : InMemoryMapRepository<UUID, Task>()