package de.fuballer.mcendgame.mixin.client;

import de.fuballer.mcendgame.item.custom.armor.CustomHumanoidArmorFeatureRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin {
    @Inject(at = @At("TAIL"), method = "<init>")
    private void init(
            EntityRendererFactory.Context ctx,
            boolean slim,
            CallbackInfo ci
    ) {
        LivingEntityRendererAccessor accessor = (LivingEntityRendererAccessor) this;

        PlayerEntityRenderer renderer = (PlayerEntityRenderer) (Object) this;

        accessor.invokeAddFeature(new CustomHumanoidArmorFeatureRenderer<>(renderer, ctx.getEntityModels()));
    }
}
