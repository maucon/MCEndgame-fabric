package de.fuballer.mcendgame.main.mixin.enemy.boss;

import de.fuballer.mcendgame.main.accessor.MobEntityDungeonBossAccessor;
import de.fuballer.mcendgame.main.component.dungeon.enemy.boss.DungeonBossService;
import de.fuballer.mcendgame.main.component.dungeon.generation.data.SpawnPosition;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public class MobEntityDungeonBossMixin implements MobEntityDungeonBossAccessor {
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
    public void mcendgame$setDungeonBoss(boolean isBoss) {
        isDungeonBoss = isBoss;
    }

    @Nullable
    @Override
    public SpawnPosition mcendgame$getSpawnPosition() {
        return spawnPosition;
    }

    @Override
    public void mcendgame$setSpawnPosition(SpawnPosition location) {
        spawnPosition = location;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    void tick(CallbackInfo ci) {
        if (!isDungeonBoss) return;
        var entity = (MobEntity) (Object) this;
        if (!entity.isAiDisabled()) return;
        if (!(entity.getEntityWorld() instanceof ServerWorld serverWorld)) return;

        var players = serverWorld.getNonSpectatingEntities(PlayerEntity.class, entity.getBoundingBox().expand(20.0, 5.0, 20.0));
        players = players.stream().filter(player -> !player.isInCreativeMode()).toList();

        for (PlayerEntity player : players) {
            if (!entity.canSee(player)) continue;

            DungeonBossService.INSTANCE.activateBoss(entity, player);
            return;
        }
    }

    @Inject(method = "writeCustomData", at = @At("TAIL"))
    private void writeCustomData(WriteView view, CallbackInfo ci) {
        if (isDungeonBoss) view.putBoolean(DUNGEON_BOSS_NBT, true);
        if (spawnPosition != null) {
            view.put(SPAWN_POSITION_NBT, SpawnPosition.Companion.getCODEC(), spawnPosition);
        }
    }

    @Inject(method = "readCustomData", at = @At("TAIL"))
    private void readNBT(ReadView view, CallbackInfo ci) {
        isDungeonBoss = view.getBoolean(DUNGEON_BOSS_NBT, false);
        spawnPosition = view.read(SPAWN_POSITION_NBT, SpawnPosition.Companion.getCODEC()).orElse(null);
    }
}
