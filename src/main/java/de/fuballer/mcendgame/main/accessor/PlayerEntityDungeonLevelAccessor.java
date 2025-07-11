package de.fuballer.mcendgame.main.accessor;

import de.fuballer.mcendgame.main.component.dungeon.level.PlayerDungeonLevel;

public interface PlayerEntityDungeonLevelAccessor {
    PlayerDungeonLevel mcendgame$getDungeonLevel();

    void mcendgame$setDungeonLevel(PlayerDungeonLevel dungeonLevel);
}