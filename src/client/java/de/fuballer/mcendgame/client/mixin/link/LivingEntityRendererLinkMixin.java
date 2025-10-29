package de.fuballer.mcendgame.client.mixin.link;

import de.fuballer.mcendgame.client.accessor.LivingEntityRenderStateLinkAccessor;
import de.fuballer.mcendgame.client.component.entity.custom.data.MultipleEntityConnectionData;
import de.fuballer.mcendgame.client.messaging.CreateLinkDataCommand;
import de.fuballer.mcendgame.client.messaging.RenderLinksCommand;
import de.fuballer.mcendgame.main.accessor.LivingEntityLinkAttributeAccessor;
import de.maucon.mauconframework.command.CommandGateway;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererLinkMixin<T extends LivingEntity, S extends LivingEntityRenderState> {
    @Inject(
            method = "updateRenderState(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;F)V",
            at = @At("TAIL")
    )
    void updateRenderState(T entity, S renderState, float tickDelta, CallbackInfo ci) {
        var linkedEntities = ((LivingEntityLinkAttributeAccessor) entity).mcendgame$getLinkedEntities();
        var renderStateAccessor = (LivingEntityRenderStateLinkAccessor) renderState;
        if (linkedEntities.isEmpty()) {
            renderStateAccessor.mcendgame$setLinksData(new MultipleEntityConnectionData());
            return;
        }

        var command = new CreateLinkDataCommand(linkedEntities, entity, tickDelta, new MultipleEntityConnectionData());
        var cmd = CommandGateway.INSTANCE.apply(command);
        renderStateAccessor.mcendgame$setLinksData(cmd.getData());
    }

    @Inject(
            method = "render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At("TAIL")
    )
    void render(S renderState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, CallbackInfo ci) {
        var data = ((LivingEntityRenderStateLinkAccessor) renderState).mcendgame$getLinksData();
        var cmd = new RenderLinksCommand(renderState, matrixStack, vertexConsumerProvider, data);
        CommandGateway.INSTANCE.apply(cmd);
    }
}
