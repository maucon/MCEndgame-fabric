package de.fuballer.mcendgame.mixin.boss;

import de.fuballer.mcendgame.accessor.MobEntityBossAccessor;
import de.fuballer.mcendgame.component.dungeon.generation.data.SpawnPosition;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public class MobEntityBossMixin implements MobEntityBossAccessor {
    @Unique
    private static final String DUNGEON_BOSS_NBT = "isDungeonBoss";
    @Unique
    private static final String SPAWN_POSITION_NBT = "SpawnLocation";

    @Unique
    private boolean isDungeonBoss = false;
    @Nullable
    @Unique
    private SpawnPosition spawnPosition = null;

    @Override
    public boolean mcendgame$isDungeonBoss() {
        return isDungeonBoss;
    }

    @Override
    public void mcendgame$setDungeonBoss() {
        isDungeonBoss = true;
    }

    @Nullable
    @Override
    public SpawnPosition mcendgame$getSpawnLocation() {
        return spawnPosition;
    }

    @Override
    public void mcendgame$setSpawnLocation(SpawnPosition location) {
        spawnPosition = location;
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
    private void writeNBT(NbtCompound nbt, CallbackInfo ci) {
        nbt.putBoolean(DUNGEON_BOSS_NBT, isDungeonBoss);
        if (spawnPosition != null) {
            nbt.put(SPAWN_POSITION_NBT, SpawnPosition.Companion.getCODEC(), spawnPosition);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readNBT(NbtCompound nbt, CallbackInfo ci) {
        isDungeonBoss = nbt.getBoolean(DUNGEON_BOSS_NBT).orElse(false);
        spawnPosition = nbt.get(SPAWN_POSITION_NBT, SpawnPosition.Companion.getCODEC()).orElse(null);
    }
}
