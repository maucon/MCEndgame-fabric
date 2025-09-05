package de.fuballer.mcendgame.client.mixin.ghostly_appearance;

import de.fuballer.mcendgame.client.accessor.LivingEntityRenderStateGhostlyAccessor;
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererGhostlyMixin<T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<? super S>> {
    @Shadow
    public abstract Identifier getTexture(S state);

    @Inject(
            method = "updateRenderState(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;F)V",
            at = @At("TAIL")
    )
    public void updateRenderState(
            LivingEntity livingEntity,
            LivingEntityRenderState livingEntityRenderState,
            float f,
            CallbackInfo ci
    ) {
        if (!(livingEntityRenderState instanceof LivingEntityRenderStateGhostlyAccessor accessor)) return;

        var ghostly = CustomAttributesExtensions.INSTANCE.isGhostly(livingEntity);
        accessor.mcendgame$setGhostly(ghostly);
    }
}
