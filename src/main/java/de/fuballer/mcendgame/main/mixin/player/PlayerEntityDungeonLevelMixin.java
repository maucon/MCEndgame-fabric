package de.fuballer.mcendgame.main.mixin.player;

import de.fuballer.mcendgame.main.accessor.PlayerEntityDungeonLevelAccessor;
import de.fuballer.mcendgame.main.component.dungeon.level.PlayerDungeonLevel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityDungeonLevelMixin implements PlayerEntityDungeonLevelAccessor {
    @Unique
    private static final String DUNGEON_LEVEL_NBT = "dungeonLevel";
    @Unique
    private static final String DUNGEON_LEVEL_PROGRESS_NBT = "dungeonLevelProgress";
    @Unique
    private int dungeonLevel = 1;
    @Unique
    private int dungeonLevelProgress = 0;

    @Override
    public PlayerDungeonLevel mcendgame$getDungeonLevel() {
        return new PlayerDungeonLevel(dungeonLevel, dungeonLevelProgress);
    }

    @Override
    public void mcendgame$setDungeonLevel(PlayerDungeonLevel dungeonLevel) {
        this.dungeonLevel = dungeonLevel.getLevel();
        this.dungeonLevelProgress = dungeonLevel.getLevelProgress();
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeNBT(NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt(DUNGEON_LEVEL_NBT, dungeonLevel);
        nbt.putInt(DUNGEON_LEVEL_PROGRESS_NBT, dungeonLevelProgress);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readNBT(NbtCompound nbt, CallbackInfo ci) {
        dungeonLevel = nbt.getInt(DUNGEON_LEVEL_NBT).orElse(1);
        dungeonLevelProgress = nbt.getInt(DUNGEON_LEVEL_PROGRESS_NBT).orElse(0);
    }
}
