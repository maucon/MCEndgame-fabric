package de.fuballer.mcendgame.client.mixin.renderer;

import de.fuballer.mcendgame.client.accessor.EquipmentRendererAccessor;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.client.render.entity.equipment.EquipmentModelLoader;
import net.minecraft.client.render.entity.equipment.EquipmentRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.trim.ArmorTrim;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(EquipmentRenderer.class)
public abstract class EquipmentRendererMixin implements EquipmentRendererAccessor {
    @Shadow
    @Final
    private EquipmentModelLoader equipmentModelLoader;

    @Unique
    private SpriteAtlasTexture armorTrimsAtlas;

    @Shadow
    private static int getDyeColor(EquipmentModel.Layer layer, int dyeColor) {
        return 0;
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(
            EquipmentModelLoader equipmentModelLoader,
            SpriteAtlasTexture armorTrimsAtlas,
            CallbackInfo ci
    ) {
        this.armorTrimsAtlas = armorTrimsAtlas;
    }

    @Override
    public void mcendgame$renderGhostly(
            EquipmentModel.LayerType layerType,
            RegistryKey<EquipmentAsset> assetKey,
            Model model,
            ItemStack stack,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            @Nullable Identifier texture
    ) {
        List<EquipmentModel.Layer> list = equipmentModelLoader.get(assetKey).getLayers(layerType);
        if (list.isEmpty()) return;

        int stackColor = DyedColorComponent.getColor(stack, 0);
        boolean hasGlint = stack.hasGlint();

        for (EquipmentModel.Layer layer : list) {
            int color = getDyeColor(layer, stackColor);
            if (color == 0) continue;

            Identifier identifier = (layer.usePlayerTexture() && texture != null) ? texture : layer.getFullTextureId(layerType);
            VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers, RenderLayer.getEntityTranslucent(identifier), hasGlint);
            model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, color);
            hasGlint = false;
        }

        ArmorTrim armorTrim = stack.get(DataComponentTypes.TRIM);
        if (armorTrim == null) return;
        var spriteIdentifier = armorTrim.getTextureId(layerType.getTrimsDirectory(), assetKey);
        var sprite = armorTrimsAtlas.getSprite(spriteIdentifier);

        var translucentRenderLayer = RenderLayer.getEntityTranslucent(sprite.getAtlasId());
        var trimVertexConsumer = vertexConsumers.getBuffer(translucentRenderLayer);
        trimVertexConsumer = sprite.getTextureSpecificVertexConsumer(trimVertexConsumer);

        model.render(matrices, trimVertexConsumer, light, OverlayTexture.DEFAULT_UV);
    }
}
