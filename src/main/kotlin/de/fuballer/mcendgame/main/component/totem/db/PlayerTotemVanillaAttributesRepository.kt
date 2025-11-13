package de.fuballer.mcendgame.main.component.totem.db

import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.stereotype.extension.InMemoryMapRepository
import java.util.*

@Injectable
class PlayerTotemVanillaAttributesRepository(
) : InMemoryMapRepository<UUID, PlayerTotemVanillaAttributesEntity>()