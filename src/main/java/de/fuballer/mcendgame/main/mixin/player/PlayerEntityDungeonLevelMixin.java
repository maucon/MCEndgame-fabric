package de.fuballer.mcendgame.main.mixin.player;

import de.fuballer.mcendgame.main.accessor.PlayerEntityDungeonLevelAccessor;
import de.fuballer.mcendgame.main.component.dungeon.level.PlayerDungeonLevel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityDungeonLevelMixin implements PlayerEntityDungeonLevelAccessor {
    @Unique
    private PlayerDungeonLevel playerDungeonLevel = new PlayerDungeonLevel();

    @Override
    public PlayerDungeonLevel mcendgame$getDungeonLevel() {
        return playerDungeonLevel;
    }

    @Override
    public void mcendgame$setDungeonLevel(PlayerDungeonLevel dungeonLevel) {
        this.playerDungeonLevel = dungeonLevel;
    }

    @Inject(method = "writeCustomData", at = @At("TAIL"))
    private void writeData(WriteView view, CallbackInfo ci) {
        PlayerDungeonLevel.Companion.write(playerDungeonLevel, view);
    }

    @Inject(method = "readCustomData", at = @At("TAIL"))
    private void readData(ReadView view, CallbackInfo ci) {
        playerDungeonLevel = PlayerDungeonLevel.Companion.read(view);
    }
}
