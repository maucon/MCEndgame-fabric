package de.fuballer.mcendgame.main.accessor;

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem;

import java.util.Map;

public interface DungeonWorldAccessor {
    boolean mcendgame$isCompleted();

    void mcendgame$setCompleted();

    int mcendgame$getLevel();

    void mcendgame$setLevel(int level);

    void mcendgame$setAspects(Map<AspectItem, Integer> aspects);

    Map<AspectItem, Integer> mcendgame$getAspects();
}
