package de.fuballer.mcendgame.client.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ArmorFeatureRenderer.class)
public abstract class ArmorFeatureRendererMixin<S extends BipedEntityRenderState, M extends BipedEntityModel<S>, A extends BipedEntityModel<S>> {
    /**
     * @author
     * @reason
     *//*
    @Overwrite
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, S bipedEntityRenderState, float f, float g) {
        var stateAccessor = (BipedEntityRenderStateAccessor) bipedEntityRenderState;

        invokeRenderArmor(
                matrixStack,
                vertexConsumerProvider,
                bipedEntityRenderState.equippedChestStack,
                EquipmentSlot.CHEST,
                i,
                invokeGetModel(bipedEntityRenderState, EquipmentSlot.CHEST)
        );

        invokeRenderArmor(
                matrixStack,
                vertexConsumerProvider,
                bipedEntityRenderState.equippedLegsStack,
                EquipmentSlot.LEGS,
                i,
                invokeGetModel(bipedEntityRenderState, EquipmentSlot.LEGS)
        );

        if (!stateAccessor.mcendgame$getHideBoots()) {
            invokeRenderArmor(
                    matrixStack,
                    vertexConsumerProvider,
                    bipedEntityRenderState.equippedFeetStack,
                    EquipmentSlot.FEET,
                    i,
                    invokeGetModel(bipedEntityRenderState, EquipmentSlot.FEET)
            );
        }

        invokeRenderArmor(
                matrixStack,
                vertexConsumerProvider,
                bipedEntityRenderState.equippedHeadStack,
                EquipmentSlot.HEAD,
                i,
                invokeGetModel(bipedEntityRenderState, EquipmentSlot.HEAD)
        );
    }*/

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