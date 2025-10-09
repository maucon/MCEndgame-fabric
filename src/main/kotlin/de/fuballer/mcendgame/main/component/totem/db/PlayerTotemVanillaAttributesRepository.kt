package de.fuballer.mcendgame.main.component.totem.db

import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.di.annotation.Logging
import de.maucon.mauconframework.stereotype.extension.InMemoryMapRepository
import org.slf4j.Logger
import java.util.*

@Injectable
class PlayerTotemVanillaAttributesRepository(
    @Logging private val log: Logger,
) : InMemoryMapRepository<UUID, PlayerTotemVanillaAttributesEntity>()