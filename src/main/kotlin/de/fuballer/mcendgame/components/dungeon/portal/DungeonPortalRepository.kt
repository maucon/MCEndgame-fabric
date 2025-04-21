package de.fuballer.mcendgame.components.dungeon.portal

import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.stereotype.extension.InMemoryMapRepository
import java.util.*

@Injectable
class DungeonPortalRepository : InMemoryMapRepository<Int, DungeonPortalEntity>()