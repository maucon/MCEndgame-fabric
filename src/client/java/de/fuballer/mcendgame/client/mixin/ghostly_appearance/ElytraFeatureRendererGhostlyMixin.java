package de.fuballer.mcendgame.client.mixin.ghostly_appearance;

import com.llamalad7.mixinextras.sugar.Local;
import de.fuballer.mcendgame.client.util.EntityRenderStateMixinExtension;
import de.fuballer.mcendgame.client.util.EquipmentRendererMixinExtension;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.client.render.entity.equipment.EquipmentRenderer;
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer;
import net.minecraft.client.render.entity.model.ElytraEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ElytraFeatureRenderer.class)
public class ElytraFeatureRendererGhostlyMixin<S extends BipedEntityRenderState> {
    @Shadow
    @Final
    private EquipmentRenderer equipmentRenderer;

    @Redirect(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/state/BipedEntityRenderState;FF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/equipment/EquipmentRenderer;render(Lnet/minecraft/client/render/entity/equipment/EquipmentModel$LayerType;Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/client/model/Model;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/util/Identifier;)V")
    )
    void render(
            EquipmentRenderer instance,
            EquipmentModel.LayerType layerType,
            RegistryKey<EquipmentAsset> assetKey,
            Model model,
            ItemStack stack,
            MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider,
            int light,
            @Nullable Identifier texture,
            @Local(argsOnly = true) S bipedEntityRenderState,
            @Local EquippableComponent equippableComponent,
            @Local ElytraEntityModel elytraEntityModel,
            @Local ItemStack itemStack,
            @Local Identifier identifier
    ) {
        if (!EntityRenderStateMixinExtension.INSTANCE.isGhostly(bipedEntityRenderState)) {
            equipmentRenderer.render(
                    EquipmentModel.LayerType.WINGS,
                    equippableComponent.assetId().get(),
                    elytraEntityModel,
                    itemStack,
                    matrixStack,
                    vertexConsumerProvider,
                    light,
                    identifier
            );
        } else {
            EquipmentRendererMixinExtension.INSTANCE.renderTranslucent(
                    equipmentRenderer,
                    EquipmentModel.LayerType.WINGS,
                    equippableComponent.assetId().get(),
                    elytraEntityModel,
                    itemStack,
                    matrixStack,
                    vertexConsumerProvider,
                    light,
                    identifier
            );
        }
    }
}
