package de.fuballer.mcendgame.main.accessor;

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem;

import java.util.HashMap;

public interface DungeonWorldAccessor {
    boolean mcendgame$isCompleted();

    void mcendgame$setCompleted();

    int mcendgame$getLevel();

    void mcendgame$setLevel(int level);

    void mcendgame$setAspects(HashMap<AspectItem, Integer> aspects);

    HashMap<AspectItem, Integer> mcendgame$getAspects();
}
