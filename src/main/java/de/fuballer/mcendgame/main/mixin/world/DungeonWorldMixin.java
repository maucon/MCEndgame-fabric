package de.fuballer.mcendgame.main.mixin.world;

import de.fuballer.mcendgame.main.accessor.DungeonWorldAccessor;
import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem;
import net.minecraft.server.world.ServerWorld;
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
    private Map<AspectItem, Integer> aspects = new HashMap<>();

    @Override
    public boolean mcendgame$isCompleted() {
        return isCompleted;
    }

    @Override
    public void mcendgame$setCompleted() {
        this.isCompleted = true;
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
    public void mcendgame$setAspects(Map<AspectItem, Integer> aspects) {
        this.aspects = aspects;
    }

    @Override
    public Map<AspectItem, Integer> mcendgame$getAspects() {
        return aspects;
    }
}