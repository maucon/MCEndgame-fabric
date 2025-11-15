package de.fuballer.mcendgame.main.mixin.access;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Entity.class)
public interface EntityAccessMixin {
    @Invoker("scheduleVelocityUpdate")
    void invokeScheduleVelocityUpdate();
}
