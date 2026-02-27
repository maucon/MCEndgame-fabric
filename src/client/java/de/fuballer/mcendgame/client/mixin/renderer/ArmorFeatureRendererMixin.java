package de.fuballer.mcendgame.client.mixin.renderer;

import de.fuballer.mcendgame.client.accessor.BipedEntityRenderStateAccessor;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorFeatureRenderer.class)
public abstract class ArmorFeatureRendererMixin<S extends BipedEntityRenderState, M extends BipedEntityModel<S>, A extends BipedEntityModel<S>> {
    @Inject(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;ILnet/minecraft/client/render/entity/state/BipedEntityRenderState;FF)V",
            at = @At("HEAD"),
            cancellable = true
    )
    public void render(MatrixStack matrixStack, OrderedRenderCommandQueue queue, int i, S renderState, float f, float g, CallbackInfo ci) {
        var stateAccessor = (BipedEntityRenderStateAccessor) renderState;

        if (!stateAccessor.mcendgame$getHiddenArmor().contains(EquipmentSlot.CHEST)) {
            invokeRenderArmor(
                    matrixStack,
                    queue,
                    renderState.equippedChestStack,
                    EquipmentSlot.CHEST,
                    renderState.light,
                    renderState
            );
        }

        if (!stateAccessor.mcendgame$getHiddenArmor().contains(EquipmentSlot.LEGS)) {
            invokeRenderArmor(
                    matrixStack,
                    queue,
                    renderState.equippedLegsStack,
                    EquipmentSlot.LEGS,
                    renderState.light,
                    renderState
            );
        }

        if (!stateAccessor.mcendgame$getHiddenArmor().contains(EquipmentSlot.FEET)) {
            invokeRenderArmor(
                    matrixStack,
                    queue,
                    renderState.equippedFeetStack,
                    EquipmentSlot.FEET,
                    renderState.light,
                    renderState
            );
        }

        if (!stateAccessor.mcendgame$getHiddenArmor().contains(EquipmentSlot.HEAD)) {
            invokeRenderArmor(
                    matrixStack,
                    queue,
                    renderState.equippedHeadStack,
                    EquipmentSlot.HEAD,
                    renderState.light,
                    renderState
            );
        }

        ci.cancel();
    }

    @Invoker
    public abstract void invokeRenderArmor(
            MatrixStack matrices,
            OrderedRenderCommandQueue queue,
            ItemStack stack,
            EquipmentSlot slot,
            int light,
            S state
    );
}