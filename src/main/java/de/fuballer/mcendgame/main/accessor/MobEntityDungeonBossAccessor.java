package de.fuballer.mcendgame.main.accessor;

import de.fuballer.mcendgame.main.component.dungeon.generation.data.SpawnPosition;
import org.jetbrains.annotations.Nullable;

public interface MobEntityDungeonBossAccessor {
    boolean mcendgame$isDungeonBoss();

    void mcendgame$setDungeonBoss(boolean isBoss);

    @Nullable
    SpawnPosition mcendgame$getSpawnPosition();

    void mcendgame$setSpawnPosition(SpawnPosition location);
}
