package de.fuballer.mcendgame.client.mixin.renderer;

import de.fuballer.mcendgame.client.accessor.BipedEntityRenderStateAccessor;
import de.fuballer.mcendgame.client.util.EntityRenderStateMixinExtension;
import de.fuballer.mcendgame.client.util.EquipmentRendererMixinExtension;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.client.render.entity.equipment.EquipmentRenderer;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorFeatureRenderer.class)
public abstract class ArmorFeatureRendererMixin<S extends BipedEntityRenderState, M extends BipedEntityModel<S>, A extends BipedEntityModel<S>> {
    @Shadow
    public static boolean hasModel(ItemStack stack, EquipmentSlot slot) {
        return false;
    }

    @Shadow
    protected abstract void setVisible(A bipedModel, EquipmentSlot slot);

    @Shadow
    protected abstract boolean usesInnerModel(EquipmentSlot slot);

    @Shadow
    @Final
    private EquipmentRenderer equipmentRenderer;

    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/state/BipedEntityRenderState;FF)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/feature/ArmorFeatureRenderer;renderArmor(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/EquipmentSlot;ILnet/minecraft/client/render/entity/model/BipedEntityModel;)V"
            ), cancellable = true)
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, S bipedEntityRenderState, float f, float g, CallbackInfo ci) {
        var stateAccessor = (BipedEntityRenderStateAccessor) bipedEntityRenderState;
        var translucent = EntityRenderStateMixinExtension.INSTANCE.isGhostly(bipedEntityRenderState);

        if (!stateAccessor.mcendgame$getHiddenArmor().contains(EquipmentSlot.CHEST)) {
            renderArmor(
                    matrixStack,
                    vertexConsumerProvider,
                    bipedEntityRenderState.equippedChestStack,
                    EquipmentSlot.CHEST,
                    light,
                    invokeGetModel(bipedEntityRenderState, EquipmentSlot.CHEST),
                    translucent
            );
        }

        if (!stateAccessor.mcendgame$getHiddenArmor().contains(EquipmentSlot.LEGS)) {
            renderArmor(
                    matrixStack,
                    vertexConsumerProvider,
                    bipedEntityRenderState.equippedLegsStack,
                    EquipmentSlot.LEGS,
                    light,
                    invokeGetModel(bipedEntityRenderState, EquipmentSlot.LEGS),
                    translucent
            );
        }

        if (!stateAccessor.mcendgame$getHiddenArmor().contains(EquipmentSlot.FEET)) {
            renderArmor(
                    matrixStack,
                    vertexConsumerProvider,
                    bipedEntityRenderState.equippedFeetStack,
                    EquipmentSlot.FEET,
                    light,
                    invokeGetModel(bipedEntityRenderState, EquipmentSlot.FEET),
                    translucent
            );
        }

        if (!stateAccessor.mcendgame$getHiddenArmor().contains(EquipmentSlot.HEAD)) {
            renderArmor(
                    matrixStack,
                    vertexConsumerProvider,
                    bipedEntityRenderState.equippedHeadStack,
                    EquipmentSlot.HEAD,
                    light,
                    invokeGetModel(bipedEntityRenderState, EquipmentSlot.HEAD),
                    translucent
            );
        }

        ci.cancel();
    }

    @Unique
    private void renderArmor(
            MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider,
            ItemStack stack,
            EquipmentSlot slot,
            int light,
            A armorModel,
            boolean translucent
    ) {
        if (translucent) renderArmorTranslucent(matrixStack, vertexConsumerProvider, stack, slot, light, armorModel);
        else invokeRenderArmor(matrixStack, vertexConsumerProvider, stack, slot, light, armorModel);
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

    @Unique
    private void renderArmorTranslucent(
            MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider,
            ItemStack stack,
            EquipmentSlot slot,
            int light,
            A armorModel
    ) {
        var equippableComponent = stack.get(DataComponentTypes.EQUIPPABLE);
        if (equippableComponent == null) return;
        if (!hasModel(stack, slot)) return;

        var renderer = (ArmorFeatureRenderer<S, M, A>) (Object) this;
        renderer.getContextModel().copyTransforms(armorModel);
        setVisible(armorModel, slot);
        EquipmentModel.LayerType layerType = usesInnerModel(slot) ? EquipmentModel.LayerType.HUMANOID_LEGGINGS : EquipmentModel.LayerType.HUMANOID;

        EquipmentRendererMixinExtension.INSTANCE.renderTranslucent(
                equipmentRenderer,
                layerType,
                equippableComponent.assetId().orElseThrow(),
                armorModel,
                stack,
                matrixStack,
                vertexConsumerProvider,
                light,
                null
        );
    }
}