package de.fuballer.mcendgame.client.mixin.renderer;

import de.fuballer.mcendgame.client.accessor.BipedEntityRenderStateAccessor;
import net.minecraft.client.render.VertexConsumerProvider;
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
    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/state/BipedEntityRenderState;FF)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/feature/ArmorFeatureRenderer;renderArmor(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/EquipmentSlot;ILnet/minecraft/client/render/entity/model/BipedEntityModel;)V"
            ), cancellable = true)
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, S bipedEntityRenderState, float f, float g, CallbackInfo ci) {
        var stateAccessor = (BipedEntityRenderStateAccessor) bipedEntityRenderState;

        if (!stateAccessor.mcendgame$getHiddenArmor().contains(EquipmentSlot.CHEST)) {
            invokeRenderArmor(
                    matrixStack,
                    vertexConsumerProvider,
                    bipedEntityRenderState.equippedChestStack,
                    EquipmentSlot.CHEST,
                    light,
                    invokeGetModel(bipedEntityRenderState, EquipmentSlot.CHEST)
            );
        }

        if (!stateAccessor.mcendgame$getHiddenArmor().contains(EquipmentSlot.LEGS)) {
            invokeRenderArmor(
                    matrixStack,
                    vertexConsumerProvider,
                    bipedEntityRenderState.equippedLegsStack,
                    EquipmentSlot.LEGS,
                    light,
                    invokeGetModel(bipedEntityRenderState, EquipmentSlot.LEGS)
            );
        }

        if (!stateAccessor.mcendgame$getHiddenArmor().contains(EquipmentSlot.FEET)) {
            invokeRenderArmor(
                    matrixStack,
                    vertexConsumerProvider,
                    bipedEntityRenderState.equippedFeetStack,
                    EquipmentSlot.FEET,
                    light,
                    invokeGetModel(bipedEntityRenderState, EquipmentSlot.FEET)
            );
        }

        if (!stateAccessor.mcendgame$getHiddenArmor().contains(EquipmentSlot.HEAD)) {
            invokeRenderArmor(
                    matrixStack,
                    vertexConsumerProvider,
                    bipedEntityRenderState.equippedHeadStack,
                    EquipmentSlot.HEAD,
                    light,
                    invokeGetModel(bipedEntityRenderState, EquipmentSlot.HEAD)
            );
        }

        ci.cancel();
    }

    @Invoker
    public abstract void invokeRenderArmor(
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            ItemStack stack,
            EquipmentSlot slot,
            int light,
            A armorModel
    );

    @Invoker
    public abstract A invokeGetModel(S state, EquipmentSlot slot);
}