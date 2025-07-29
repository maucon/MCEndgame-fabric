package de.fuballer.mcendgame.main.mixin.world;

import de.fuballer.mcendgame.main.accessor.DungeonWorldAccessor;
import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.HashMap;
import java.util.Map;

@Mixin(ServerWorld.class)
public class DungeonWorldMixin implements DungeonWorldAccessor {
    @Unique
    private boolean isCompleted = false;
    @Unique
    private int level = 0;

    @Unique
    private PlayerEntity opener;

    @Unique
    private Map<AspectItem, Integer> aspects = new HashMap<>();

    @Override
    public boolean mcendgame$isCompleted() {
        return isCompleted;
    }

    @Override
    public void mcendgame$setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    @Override
    public int mcendgame$getLevel() {
        return level;
    }

    @Override
    public void mcendgame$setLevel(int level) {
        this.level = level;
    }

    @Override
    public void mcendgame$setOpener(PlayerEntity opener) {
        this.opener = opener;
    }

    @Override
    public PlayerEntity mcendgame$getOpener() {
        return opener;
    }

    @Override
    public void mcendgame$setAspects(Map<AspectItem, Integer> aspects) {
        this.aspects = aspects;
    }

    @Override
    public Map<AspectItem, Integer> mcendgame$getAspects() {
        return aspects;
    }
}