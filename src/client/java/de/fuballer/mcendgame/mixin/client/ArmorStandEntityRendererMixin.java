package de.fuballer.mcendgame.mixin.client;

import de.fuballer.mcendgame.components.item.custom.armor.CustomHumanoidArmorFeatureRenderer;
import net.fabricmc.fabric.mixin.client.rendering.LivingEntityRendererAccessor;
import net.minecraft.client.render.entity.ArmorStandEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorStandEntityRenderer.class)
public abstract class ArmorStandEntityRendererMixin {
    @Inject(at = @At("TAIL"), method = "<init>")
    private void init(
            EntityRendererFactory.Context ctx,
            CallbackInfo ci
    ) {
        LivingEntityRendererAccessor accessor = (LivingEntityRendererAccessor) this;

        ArmorStandEntityRenderer renderer = (ArmorStandEntityRenderer) (Object) this;

        accessor.callAddFeature(new CustomHumanoidArmorFeatureRenderer<>(renderer, ctx));
    }
}
