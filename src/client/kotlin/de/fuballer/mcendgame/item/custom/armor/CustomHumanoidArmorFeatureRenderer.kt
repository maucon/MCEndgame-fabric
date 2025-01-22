package de.fuballer.mcendgame.item.custom.armor

import de.fuballer.mcendgame.MCEndgame
import de.fuballer.mcendgame.item.custom.armor.helmet.iceborne.IceborneModel
import net.minecraft.client.model.ModelPart
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.feature.FeatureRenderer
import net.minecraft.client.render.entity.feature.FeatureRendererContext
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.LoadedEntityModels
import net.minecraft.client.render.entity.state.BipedEntityRenderState
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

class CustomHumanoidArmorFeatureRenderer<S : BipedEntityRenderState, M : BipedEntityModel<S>>(
    featureContext: FeatureRendererContext<S, M>,
    entityModels: LoadedEntityModels,
) : FeatureRenderer<S, M>(featureContext) {

    val texturedArmorModels: MutableMap<Item, TexturedArmorModel> = mutableMapOf()

    init {
        texturedArmorModels[CustomArmorItems.ICEBORNE] = TexturedArmorModel(
            Identifier.of(MCEndgame.MOD_ID, "textures/entity/equipment/custom_humanoid/iceborne.png"),
            entityModels.getModelPart(IceborneModel.MODEL_LAYER)
        )
    }

    override fun render(
        matrixStack: MatrixStack,
        vertexConsumerProvider: VertexConsumerProvider,
        light: Int,
        bipedEntityRenderState: S,
        limbAngle: Float,
        limbDistance: Float
    ) {
        this.renderArmor(
            matrixStack,
            vertexConsumerProvider,
            bipedEntityRenderState.equippedChestStack,
            EquipmentSlot.CHEST,
            light
        )
        this.renderArmor(
            matrixStack,
            vertexConsumerProvider,
            bipedEntityRenderState.equippedLegsStack,
            EquipmentSlot.LEGS,
            light
        )
        this.renderArmor(
            matrixStack,
            vertexConsumerProvider,
            bipedEntityRenderState.equippedFeetStack,
            EquipmentSlot.FEET,
            light
        )
        this.renderArmor(
            matrixStack,
            vertexConsumerProvider,
            bipedEntityRenderState.equippedHeadStack,
            EquipmentSlot.HEAD,
            light
        )
    }

    private fun renderArmor(
        matrices: MatrixStack,
        vertexConsumerProvider: VertexConsumerProvider,
        itemStack: ItemStack,
        slot: EquipmentSlot,
        light: Int,
    ) {
        val item = itemStack.item
        val texturedArmorModel = texturedArmorModels[item] ?: return

        val model = texturedArmorModel.MODEL_PART
        copyTransforms(model, slot)

        println(this.contextModel.head.pitch)

        renderModel(model, texturedArmorModel.TEXTURE, matrices, vertexConsumerProvider, light)
    }

    private fun renderModel(
        model: ModelPart,
        texture: Identifier,
        matrices: MatrixStack,
        vertexConsumerProvider: VertexConsumerProvider,
        light: Int,
    ) {
        model.render(
            matrices,
            vertexConsumerProvider.getBuffer(RenderLayer.getArmorCutoutNoCull(texture)),
            light,
            OverlayTexture.DEFAULT_UV
        )
    }

    private fun copyTransforms(
        model: ModelPart,
        slot: EquipmentSlot,
    ) {
        when (slot) {
            EquipmentSlot.HEAD -> this.contextModel.head.copyTransform(model)
            else -> return
        }
    }
}