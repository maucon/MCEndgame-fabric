package de.fuballer.mcendgame.item.custom.armor

import de.fuballer.mcendgame.MCEndgame
import de.fuballer.mcendgame.item.custom.armor.helmet.iceborne.IceborneModel
import net.minecraft.client.model.Model
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.feature.FeatureRenderer
import net.minecraft.client.render.entity.feature.FeatureRendererContext
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.state.BipedEntityRenderState
import net.minecraft.client.render.item.ItemRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

class CustomHumanoidArmorFeatureRenderer<S : BipedEntityRenderState, M : BipedEntityModel<S>>(
    featureContext: FeatureRendererContext<S, M>,
    ctx: EntityRendererFactory.Context,
) : FeatureRenderer<S, M>(featureContext) {

    private val texturedArmorModels: MutableMap<Item, TexturedArmorModel<BipedEntityModel<S>>> =
        mutableMapOf()

    init {
        texturedArmorModels[CustomArmorItems.ICEBORNE] = TexturedArmorModel(
            Identifier.of(MCEndgame.MOD_ID, "textures/entity/equipment/custom_humanoid/iceborne.png"),
            IceborneModel(ctx.getPart(IceborneModel.MODEL_LAYER))
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
            light
        )
        this.renderArmor(
            matrixStack,
            vertexConsumerProvider,
            bipedEntityRenderState.equippedLegsStack,
            light
        )
        this.renderArmor(
            matrixStack,
            vertexConsumerProvider,
            bipedEntityRenderState.equippedFeetStack,
            light
        )
        this.renderArmor(
            matrixStack,
            vertexConsumerProvider,
            bipedEntityRenderState.equippedHeadStack,
            light
        )
    }

    private fun renderArmor(
        matrices: MatrixStack,
        vertexConsumerProvider: VertexConsumerProvider,
        itemStack: ItemStack,
        light: Int,
    ) {
        val item = itemStack.item
        val texturedArmorModel = texturedArmorModels[item] ?: return

        val model = texturedArmorModel.MODEL
        this.contextModel.copyTransforms(model)

        renderModel(model, texturedArmorModel.TEXTURE, matrices, vertexConsumerProvider, light, itemStack.hasGlint())
    }

    private fun renderModel(
        model: Model,
        texture: Identifier,
        matrices: MatrixStack,
        vertexConsumerProvider: VertexConsumerProvider,
        light: Int,
        glint: Boolean,
    ) {
        model.render(
            matrices,
            ItemRenderer.getArmorGlintConsumer(
                vertexConsumerProvider,
                RenderLayer.getArmorCutoutNoCull(texture),
                glint
            ),
            light,
            OverlayTexture.DEFAULT_UV
        )
    }
}