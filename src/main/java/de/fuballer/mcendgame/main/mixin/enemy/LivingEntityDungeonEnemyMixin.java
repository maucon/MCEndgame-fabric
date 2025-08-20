package de.fuballer.mcendgame.main.mixin.enemy;

import de.fuballer.mcendgame.main.accessor.LivingEntityDungeonEnemyAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityDungeonEnemyMixin implements LivingEntityDungeonEnemyAccessor {
    @Unique
    private static final String DUNGEON_ENEMY_NBT = "isDungeonEnemy";
    @Unique
    private static final String LOOT_MULTIPLIER_NBT = "lootMultiplier";

    @Unique
    private boolean isDungeonEnemy = false;
    @Unique
    private double lootMultiplier = 1.0;

    @Override
    public boolean mcendgame$isDungeonEnemy() {
        return isDungeonEnemy;
    }

    @Override
    public void mcendgame$setDungeonEnemy(boolean enemy) {
        isDungeonEnemy = enemy;
    }

    @Override
    public double mcendgame$getLootMultiplier() {
        return lootMultiplier;
    }

    @Override
    public void mcendgame$setLootMultiplier(double multiplier) {
        lootMultiplier = multiplier;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeNBT(NbtCompound nbt, CallbackInfo ci) {
        if (isDungeonEnemy) nbt.putBoolean(DUNGEON_ENEMY_NBT, true);
        if (lootMultiplier != 1.0) nbt.putDouble(LOOT_MULTIPLIER_NBT, lootMultiplier);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readNBT(NbtCompound nbt, CallbackInfo ci) {
        isDungeonEnemy = nbt.getBoolean(DUNGEON_ENEMY_NBT).orElse(false);
        lootMultiplier = nbt.getDouble(LOOT_MULTIPLIER_NBT).orElse(1.0);
    }
}
