package de.fuballer.mcendgame.client.mixin.renderer;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(EntityRenderer.class)
public interface EntityRendererAccessorMixin<T extends Entity> {
    @Invoker("getBlockLight")
    int callGetBlockLight(T entity, BlockPos pos);
}
