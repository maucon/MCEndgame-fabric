package de.fuballer.mcendgame.main.component.killer.db

import de.fuballer.mcendgame.main.functional.persistent_repository.PersistentMapRepository
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.di.annotation.Logging
import org.slf4j.Logger
import java.util.*

@Injectable
class KillerRepository(
    @Logging private val log: Logger,
) : PersistentMapRepository<UUID, KillerEntity>(
    log,
    "killer_entity",
    KillerEntity.CODEC
) {
    // TODO save when?
}