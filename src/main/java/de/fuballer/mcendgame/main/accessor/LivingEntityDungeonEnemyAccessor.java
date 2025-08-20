package de.fuballer.mcendgame.main.accessor;

public interface LivingEntityDungeonEnemyAccessor {
    boolean mcendgame$isDungeonEnemy();

    void mcendgame$setDungeonEnemy(boolean enemy);

    double mcendgame$getLootMultiplier();

    void mcendgame$setLootMultiplier(double multiplier);
}
