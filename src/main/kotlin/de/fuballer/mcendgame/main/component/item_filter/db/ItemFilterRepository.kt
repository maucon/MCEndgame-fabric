package de.fuballer.mcendgame.main.component.item_filter.db

import de.fuballer.mcendgame.main.functional.persistent_repository.PersistentMapRepository
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.di.annotation.Logging
import org.slf4j.Logger
import java.util.*

@Injectable
class ItemFilterRepository(
    @Logging private val log: Logger,
) : PersistentMapRepository<UUID, ItemFilterEntity>(
    log,
    "item_filter",
    ItemFilterEntity.CODEC
) {
    // TODO save when?
}