package de.fuballer.mcendgame.components.dungeon.world.db

import de.maucon.mauconframework.annotation.Injectable
import de.maucon.mauconframework.extension.InMemoryMapRepository
import java.util.UUID

@Injectable
class DungeonWorldRepository : InMemoryMapRepository<UUID, DungeonWorldEntity>()