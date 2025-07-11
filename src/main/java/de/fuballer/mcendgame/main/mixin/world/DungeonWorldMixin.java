package de.fuballer.mcendgame.main.mixin.world;

import de.fuballer.mcendgame.main.accessor.DungeonWorldAccessor;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(World.class)
public class DungeonWorldMixin implements DungeonWorldAccessor {
    @Unique
    private boolean isCompleted = false;
    @Unique
    private int level = 0;

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
}
