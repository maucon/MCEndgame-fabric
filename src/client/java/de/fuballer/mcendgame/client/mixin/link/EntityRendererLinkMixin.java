package de.fuballer.mcendgame.client.mixin.link;

import de.fuballer.mcendgame.main.accessor.LivingEntityLinkAttributeAccessor;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(EntityRenderer.class)
public class EntityRendererLinkMixin<T extends Entity> {
    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
    void shouldRender(T entity, Frustum frustum, double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        if (!(entity instanceof LivingEntityLinkAttributeAccessor accessor)) return;

        var world = entity.getWorld();
        var shouldRender = accessor.mcendgame$getLinkedEntities().keySet()
                .stream()
                .map(world::getEntity)
                .filter(Objects::nonNull)
                .anyMatch(e -> e.shouldRender(x, y, z));

        if (shouldRender) cir.setReturnValue(true);
    }
}
