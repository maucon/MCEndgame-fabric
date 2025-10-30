package de.fuballer.mcendgame.main.accessor;

import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

public interface LivingEntityLinkAttributeAccessor {
    Map<UUID, Long> mcendgame$getLinkedEntities();

    void mcendgame$addLinkedBy(UUID uuid);

    void mcendgame$removeLinkedBy(UUID uuid);

    HashSet<UUID> mcendgame$getLinkedBy();
}
