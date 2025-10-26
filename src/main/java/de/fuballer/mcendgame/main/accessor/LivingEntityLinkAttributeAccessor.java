package de.fuballer.mcendgame.main.accessor;

import java.util.Map;
import java.util.UUID;

public interface LivingEntityLinkAttributeAccessor {
    Map<UUID, Long> mcendgame$getLinkedEntities();
}
