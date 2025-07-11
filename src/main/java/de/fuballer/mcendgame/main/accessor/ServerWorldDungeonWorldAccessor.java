package de.fuballer.mcendgame.main.accessor;

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem;

import java.util.HashMap;

public interface ServerWorldDungeonWorldAccessor {
    void mcendgame$setAspects(HashMap<AspectItem, Integer> aspects);

    int mcendgame$getAdditionalElitesCount();
}
