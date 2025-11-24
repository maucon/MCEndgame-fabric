package de.fuballer.mcendgame.main.component.totem.db

import de.fuballer.mcendgame.main.functional.persistent_repository.PersistentMapRepository
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.di.annotation.Logging
import org.slf4j.Logger
import java.util.*

@Injectable
class PlayerTotemsRepository(
    @Logging private val log: Logger,
) : PersistentMapRepository<UUID, PlayerTotemsEntity>(
    log,
    "player_totems",
    PlayerTotemsEntity.CODEC,
)