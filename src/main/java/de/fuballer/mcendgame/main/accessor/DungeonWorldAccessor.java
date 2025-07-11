package de.fuballer.mcendgame.main.accessor;

public interface DungeonWorldAccessor {
    boolean mcendgame$isCompleted();

    void mcendgame$setCompleted();

    int mcendgame$getLevel();

    void mcendgame$setLevel(int level);
}