package de.fuballer.mcendgame.components.item.custom.armor

import de.fuballer.mcendgame.components.item.custom.armor.CustomArmorItems
import de.fuballer.mcendgame.components.item.custom.armor.chestplate.bound_abyss.BoundAbyssModel
import de.fuballer.mcendgame.components.item.custom.armor.helmet.iceborne.IceborneModel
import de.fuballer.mcendgame.util.IdentifierUtil
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
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/iceborne.png"),
            IceborneModel(ctx.getPart(IceborneModel.MODEL_LAYER))
        )
        texturedArmorModels[CustomArmorItems.BOUND_ABYSS] = TexturedArmorModel(
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/bound_abyss.png"),
            BoundAbyssModel(ctx.getPart(BoundAbyssModel.MODEL_LAYER))
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
            bipedEntityRenderState,
            matrixStack,
            vertexConsumerProvider,
            bipedEntityRenderState.equippedChestStack,
            light
        )
        this.renderArmor(
            bipedEntityRenderState,
            matrixStack,
            vertexConsumerProvider,
            bipedEntityRenderState.equippedLegsStack,
            light
        )
        this.renderArmor(
            bipedEntityRenderState,
            matrixStack,
            vertexConsumerProvider,
            bipedEntityRenderState.equippedFeetStack,
            light
        )
        this.renderArmor(
            bipedEntityRenderState,
            matrixStack,
            vertexConsumerProvider,
            bipedEntityRenderState.equippedHeadStack,
            light
        )
    }

    private fun renderArmor(
        bipedEntityRenderState: S,
        matrices: MatrixStack,
        vertexConsumerProvider: VertexConsumerProvider,
        itemStack: ItemStack,
        light: Int,
    ) {
        val item = itemStack.item
        val texturedArmorModel = texturedArmorModels[item] ?: return

        val model = texturedArmorModel.MODEL
        this.contextModel.copyTransforms(model)
        if (model is Animated) {
            model.animate(bipedEntityRenderState)
        }

        renderModel(
            bipedEntityRenderState,
            model,
            texturedArmorModel.TEXTURE,
            matrices,
            vertexConsumerProvider,
            light,
            itemStack.hasGlint(),
        )
    }

    private fun renderModel(
        bipedEntityRenderState: S,
        model: Model,
        texture: Identifier,
        matrices: MatrixStack,
        vertexConsumerProvider: VertexConsumerProvider,
        light: Int,
        glint: Boolean,
    ) {
        var vertexConsumer =
            ItemRenderer.getArmorGlintConsumer(vertexConsumerProvider, RenderLayer.getArmorCutoutNoCull(texture), glint)
        if (model is CustomVertexConsumer) {
            vertexConsumer = model.getVertexConsumer(bipedEntityRenderState, vertexConsumerProvider, vertexConsumer)
        }

        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV)
        model.render(matrices, vertexConsumer, 0xF000F0, OverlayTexture.DEFAULT_UV)
    }
}