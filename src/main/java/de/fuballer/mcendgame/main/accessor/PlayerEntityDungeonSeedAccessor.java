package de.fuballer.mcendgame.main.accessor;

import de.fuballer.mcendgame.main.component.dungeon.seed.PlayerDungeonSeed;
import org.jetbrains.annotations.Nullable;

public interface PlayerEntityDungeonSeedAccessor {
    @Nullable
    PlayerDungeonSeed mcendgame$getDungeonSeed();

    void mcendgame$setDungeonSeed(@Nullable PlayerDungeonSeed dungeonSeed);
}