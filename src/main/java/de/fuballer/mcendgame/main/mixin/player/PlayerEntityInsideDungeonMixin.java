package de.fuballer.mcendgame.main.mixin.player;

import de.fuballer.mcendgame.main.accessor.PlayerEntityInsideDungeonAccessor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityInsideDungeonMixin implements PlayerEntityInsideDungeonAccessor {
    @Unique
    private static final String IS_INSIDE_DUNGEON_NBT = "isInsideDungeon";

    @Unique
    private boolean isInsideDungeon = false;

    @Override
    public boolean mcendgame$isInsideDungeon() {
        return isInsideDungeon;
    }

    @Override
    public void mcendgame$setInsideDungeon(boolean isInsideDungeon) {
        this.isInsideDungeon = isInsideDungeon;
    }

    @Inject(method = "writeCustomData", at = @At("TAIL"))
    private void writeNBT(WriteView view, CallbackInfo ci) {
        if (!isInsideDungeon) return;
        view.putBoolean(IS_INSIDE_DUNGEON_NBT, true);
    }

    @Inject(method = "readCustomData", at = @At("TAIL"))
    private void readNBT(ReadView view, CallbackInfo ci) {
        isInsideDungeon = view.getBoolean(IS_INSIDE_DUNGEON_NBT, false);
    }
}
