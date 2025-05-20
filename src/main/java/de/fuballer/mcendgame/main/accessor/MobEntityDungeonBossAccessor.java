package de.fuballer.mcendgame.main.accessor;

import de.fuballer.mcendgame.main.component.dungeon.generation.data.SpawnPosition;
import org.jetbrains.annotations.Nullable;

public interface MobEntityDungeonBossAccessor {
    boolean mcendgame$isDungeonBoss();

    void mcendgame$setDungeonBoss();

    @Nullable
    SpawnPosition mcendgame$getSpawnLocation();

    void mcendgame$setSpawnLocation(SpawnPosition location);
}
