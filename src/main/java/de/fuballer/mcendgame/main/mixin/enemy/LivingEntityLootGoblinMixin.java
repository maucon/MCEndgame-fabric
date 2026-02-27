package de.fuballer.mcendgame.main.mixin.enemy;

import de.fuballer.mcendgame.main.accessor.LivingEntityLootGoblinAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityLootGoblinMixin implements LivingEntityLootGoblinAccessor {
    @Unique
    private static final String LOOT_GOBLIN_NBT = "isLootGoblin";
    @Unique
    private boolean isLootGoblin = false;

    @Override
    public boolean mcendgame$isLootGoblin() {
        return isLootGoblin;
    }

    @Override
    public void mcendgame$setLootGoblin(boolean isLootGoblin) {
        this.isLootGoblin = isLootGoblin;
    }

    @Inject(method = "writeCustomData", at = @At("TAIL"))
    private void writeNBT(WriteView view, CallbackInfo ci) {
        if (!isLootGoblin) return;
        view.putBoolean(LOOT_GOBLIN_NBT, true);
    }

    @Inject(method = "readCustomData", at = @At("TAIL"))
    private void readNBT(ReadView view, CallbackInfo ci) {
        isLootGoblin = view.getBoolean(LOOT_GOBLIN_NBT, false);
    }
}
