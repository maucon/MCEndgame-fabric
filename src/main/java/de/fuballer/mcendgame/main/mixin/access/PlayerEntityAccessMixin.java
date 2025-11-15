package de.fuballer.mcendgame.main.mixin.access;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PlayerEntity.class)
public interface PlayerEntityAccessMixin {
    @Invoker("dropShoulderEntities")
    void invokeDropShoulderEntities();
}
