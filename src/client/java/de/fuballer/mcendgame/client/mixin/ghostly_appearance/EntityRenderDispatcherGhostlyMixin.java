package de.fuballer.mcendgame.client.mixin.ghostly_appearance;

import com.llamalad7.mixinextras.sugar.Local;
import de.fuballer.mcendgame.client.accessor.LivingEntityRenderStateGhostlyAccessor;
import de.fuballer.mcendgame.client.component.item.custom.armor.geistergaloschen.GhostlyVertexConsumerProvider;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.state.EntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherGhostlyMixin {
    @ModifyVariable(
            method = "render(Lnet/minecraft/client/render/entity/state/EntityRenderState;DDDLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/EntityRenderer;)V",
            at = @At("HEAD"),
            index = 9,
            argsOnly = true
    )
    VertexConsumerProvider render(
            VertexConsumerProvider vertexConsumers,
            @Local(argsOnly = true) EntityRenderState renderState
    ) {
        if (!(renderState instanceof LivingEntityRenderStateGhostlyAccessor accessor)) return vertexConsumers;
        if (!accessor.mcendgame$isGhostly()) return vertexConsumers;

        return new GhostlyVertexConsumerProvider(vertexConsumers);
    }
}
