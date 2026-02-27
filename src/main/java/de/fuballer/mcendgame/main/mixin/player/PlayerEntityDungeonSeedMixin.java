package de.fuballer.mcendgame.main.mixin.player;

import de.fuballer.mcendgame.main.accessor.PlayerEntityDungeonSeedAccessor;
import de.fuballer.mcendgame.main.component.dungeon.seed.PlayerDungeonSeed;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityDungeonSeedMixin implements PlayerEntityDungeonSeedAccessor {
    @Unique
    @Nullable
    private PlayerDungeonSeed dungeonSeed = null;

    @Override
    @Nullable
    public PlayerDungeonSeed mcendgame$getDungeonSeed() {
        return dungeonSeed;
    }

    @Override
    public void mcendgame$setDungeonSeed(@Nullable PlayerDungeonSeed dungeonSeed) {
        this.dungeonSeed = dungeonSeed;
    }

    @Inject(method = "writeCustomData", at = @At("TAIL"))
    private void writeNBT(WriteView view, CallbackInfo ci) {
        if (dungeonSeed == null) return;
        PlayerDungeonSeed.Companion.write(dungeonSeed, view);
    }

    @Inject(method = "readCustomData", at = @At("TAIL"))
    private void readNBT(ReadView view, CallbackInfo ci) {
        dungeonSeed = PlayerDungeonSeed.Companion.read(view);
    }
}
