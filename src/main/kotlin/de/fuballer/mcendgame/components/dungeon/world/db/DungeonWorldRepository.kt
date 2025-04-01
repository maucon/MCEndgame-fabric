package de.fuballer.mcendgame.components.dungeon.world.db

import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.stereotype.extension.InMemoryMapRepository
import java.util.*

@Injectable
class DungeonWorldRepository : InMemoryMapRepository<UUID, DungeonWorldEntity>()