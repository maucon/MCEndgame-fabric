package de.fuballer.mcendgame.client.util

import de.fuballer.mcendgame.client.accessor.EquipmentRendererAccessor
import net.minecraft.client.model.Model
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.equipment.EquipmentModel
import net.minecraft.client.render.entity.equipment.EquipmentRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.ItemStack
import net.minecraft.item.equipment.EquipmentAsset
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Identifier

object EquipmentRendererMixinExtension {
    fun EquipmentRenderer.renderGhostly(
        layerType: EquipmentModel.LayerType,
        assetKey: RegistryKey<EquipmentAsset>,
        model: Model,
        stack: ItemStack,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        texture: Identifier? = null,
    ) {
        (this as EquipmentRendererAccessor).`mcendgame$renderGhostly`(
            layerType,
            assetKey,
            model,
            stack,
            matrices,
            vertexConsumers,
            light,
            texture
        )
    }
}