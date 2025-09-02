package de.fuballer.mcendgame.client.mixin.ghostly_appearance;

import com.llamalad7.mixinextras.sugar.Local;
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.item.model.BasicItemModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BasicItemModel.class)
public class BasicItemModelGhostlyMixin {
    @ModifyArg(
            method = "update",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/ItemRenderState$LayerRenderState;setRenderLayer(Lnet/minecraft/client/render/RenderLayer;)V")
    )
    RenderLayer setRenderLayer(
            RenderLayer layer,
            @Local(argsOnly = true) ItemStack stack,
            @Local(argsOnly = true) @Nullable LivingEntity user
    ) {
        if (user == null) return layer;
        if (!CustomAttributesExtensions.INSTANCE.isGhostly(user)) return layer;

        return TexturedRenderLayers.getItemEntityTranslucentCull();
    }
}
