package de.fuballer.mcendgame.client.mixin.renderer;

import com.llamalad7.mixinextras.sugar.Local;
import de.fuballer.mcendgame.client.component.item.custom.armor.geistergaloschen.GhostlyVertexConsumer;
import de.fuballer.mcendgame.client.util.EntityRenderStateMixinExtension;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.state.ArmedEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(HeldItemFeatureRenderer.class)
public class HeldItemFeatureRendererMixin<S extends ArmedEntityRenderState> {
    @ModifyArg(
            method = "renderItem",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/ItemRenderState;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V"),
            index = 1
    )
    VertexConsumerProvider render(
            VertexConsumerProvider vertexConsumers,
            @Local(argsOnly = true) S entityState
    ) {
        if (!EntityRenderStateMixinExtension.INSTANCE.isGhostly(entityState)) return vertexConsumers;
        return GhostlyVertexConsumer.Companion.convertToGhostlyVertexConsumerProvider(vertexConsumers);
    }
}