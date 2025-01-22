package de.fuballer.mcendgame.item.custom.armor.helmet.iceborne

import de.fuballer.mcendgame.MCEndgame
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.state.BipedEntityRenderState
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

class IceborneArmorRenderer : ArmorRenderer {
    companion object {
        private val TEXTURE = Identifier.of(MCEndgame.MOD_ID, "textures/entity/equipment/custom_humanoid/iceborne.png")
    }

    val model = MinecraftClient.getInstance().loadedEntityModels.getModelPart(IceborneModel.MODEL_LAYER)

    override fun render(
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        stack: ItemStack,
        bipedEntityRenderState: BipedEntityRenderState,
        slot: EquipmentSlot,
        light: Int,
        contextModel: BipedEntityModel<BipedEntityRenderState>
    ) {
        println("rendererererererere")
        val vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(TEXTURE))
        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV)
    }
}