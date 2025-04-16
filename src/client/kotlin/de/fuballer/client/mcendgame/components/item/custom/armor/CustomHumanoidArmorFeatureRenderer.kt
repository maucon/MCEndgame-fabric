package de.fuballer.client.mcendgame.components.item.custom.armor

import de.fuballer.client.mcendgame.components.item.custom.armor.boots.druids_boots.DruidsBootsModel
import de.fuballer.client.mcendgame.components.item.custom.armor.boots.geistergaloschen.GeistergaloschenModel
import de.fuballer.client.mcendgame.components.item.custom.armor.chestplate.bound_abyss.BoundAbyssModel
import de.fuballer.client.mcendgame.components.item.custom.armor.chestplate.druids_chestplate.DruidsChestplateModel
import de.fuballer.client.mcendgame.components.item.custom.armor.helmet.druids_helmet.DruidsHelmetModel
import de.fuballer.client.mcendgame.components.item.custom.armor.helmet.emberchant.EmberchantModel
import de.fuballer.client.mcendgame.components.item.custom.armor.helmet.iceborne.IceborneModel
import de.fuballer.client.mcendgame.components.item.custom.armor.leggings.druids_leggings.DruidsLeggingsModel
import de.fuballer.client.mcendgame.components.item.custom.armor.leggings.lamias_gift.LamiasGiftModel
import de.fuballer.client.mcendgame.mixin_interfaces.BipedEntityRenderStateAccessor
import de.fuballer.mcendgame.components.item.custom.armor.CustomArmorItems
import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.model.Model
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.feature.FeatureRenderer
import net.minecraft.client.render.entity.feature.FeatureRendererContext
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.state.ArmorStandEntityRenderState
import net.minecraft.client.render.entity.state.BipedEntityRenderState
import net.minecraft.client.render.item.ItemRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import kotlin.math.PI

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
        texturedArmorModels[CustomArmorItems.DRUIDS_HELMET] = TexturedArmorModel(
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/druids.png"),
            DruidsHelmetModel(ctx.getPart(DruidsHelmetModel.MODEL_LAYER))
        )
        texturedArmorModels[CustomArmorItems.DRUIDS_CHESTPLATE] = TexturedArmorModel(
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/druids.png"),
            DruidsChestplateModel(ctx.getPart(DruidsChestplateModel.MODEL_LAYER))
        )
        texturedArmorModels[CustomArmorItems.DRUIDS_LEGGINGS] = TexturedArmorModel(
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/druids.png"),
            DruidsLeggingsModel(ctx.getPart(DruidsLeggingsModel.MODEL_LAYER))
        )
        texturedArmorModels[CustomArmorItems.DRUIDS_BOOTS] = TexturedArmorModel(
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/druids.png"),
            DruidsBootsModel(ctx.getPart(DruidsBootsModel.MODEL_LAYER))
        )
        texturedArmorModels[CustomArmorItems.EMBERCHANT] = TexturedArmorModel(
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/emberchant.png"),
            EmberchantModel(ctx.getPart(EmberchantModel.MODEL_LAYER))
        )
        texturedArmorModels[CustomArmorItems.LAMIAS_GIFT] = TexturedArmorModel(
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/lamias_gift.png"),
            LamiasGiftModel(ctx.getPart(LamiasGiftModel.MODEL_LAYER))
        )
        texturedArmorModels[CustomArmorItems.GEISTERGALOSCHEN] = TexturedArmorModel(
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/geistergaloschen.png"),
            GeistergaloschenModel(ctx.getPart(GeistergaloschenModel.MODEL_LAYER))
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
        val stateAccessor = bipedEntityRenderState as BipedEntityRenderStateAccessor

        renderArmor(
            bipedEntityRenderState,
            matrixStack,
            vertexConsumerProvider,
            bipedEntityRenderState.equippedChestStack,
            light
        )
        renderArmor(
            bipedEntityRenderState,
            matrixStack,
            vertexConsumerProvider,
            bipedEntityRenderState.equippedLegsStack,
            light
        )
        if (!stateAccessor.`mcendgame$getHideBoots`()) {
            renderArmor(
                bipedEntityRenderState,
                matrixStack,
                vertexConsumerProvider,
                bipedEntityRenderState.equippedFeetStack,
                light
            )
        }
        renderArmor(
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

        val model = texturedArmorModel.model
        contextModel.copyTransforms(model)

        model.setAngles(bipedEntityRenderState)

        if (bipedEntityRenderState is ArmorStandEntityRenderState) {
            model.head.yaw += bipedEntityRenderState.yaw * PI.toFloat() / 180F
        }

        renderModel(
            bipedEntityRenderState,
            model,
            texturedArmorModel.texture,
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
    }
}