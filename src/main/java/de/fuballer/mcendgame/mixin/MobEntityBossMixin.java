package de.fuballer.mcendgame.mixin;

import de.fuballer.mcendgame.accessors.MobEntityBossAccessor;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public class MobEntityBossMixin implements MobEntityBossAccessor {
    @Unique
    private boolean isDungeonBoss = false;

    @Override
    public boolean mcendgame$isDungeonBoss() {
        return isDungeonBoss;
    }

    @Override
    public void mcendgame$setDungeonBoss() {
        isDungeonBoss = true;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    void tick(CallbackInfo ci) {
        if (!isDungeonBoss) return;
        var entity = (MobEntity) (Object) this;
        if (!entity.isAiDisabled()) return;
        if (!(entity.getWorld() instanceof ServerWorld serverWorld)) return;

        var players = serverWorld.getNonSpectatingEntities(PlayerEntity.class, entity.getBoundingBox().expand(20.0, 5.0, 20.0));
        players = players.stream().filter(player -> !player.isInCreativeMode()).toList();

        for (PlayerEntity player : players) {
            if (!entity.canSee(player)) continue;

            entity.setAiDisabled(false);
            entity.setTarget(player);

            return;
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeBossFlag(NbtCompound nbt, CallbackInfo ci) {
        nbt.putBoolean("isDungeonBoss", isDungeonBoss);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readBossFlag(NbtCompound nbt, CallbackInfo ci) {
        isDungeonBoss = nbt.getBoolean("isDungeonBoss");
    }
}
