package de.fuballer.mcendgame.client.component.item.custom.armor

import de.fuballer.mcendgame.client.accessor.BipedEntityRenderStateAccessor
import de.fuballer.mcendgame.client.component.item.custom.armor.bound_abyss.BoundAbyssModel
import de.fuballer.mcendgame.client.component.item.custom.armor.druids.DruidsBootsModel
import de.fuballer.mcendgame.client.component.item.custom.armor.druids.DruidsChestplateModel
import de.fuballer.mcendgame.client.component.item.custom.armor.druids.DruidsHelmetModel
import de.fuballer.mcendgame.client.component.item.custom.armor.druids.DruidsLeggingsModel
import de.fuballer.mcendgame.client.component.item.custom.armor.emberchant.EmberchantModel
import de.fuballer.mcendgame.client.component.item.custom.armor.fae.FaeLeggingsModel
import de.fuballer.mcendgame.client.component.item.custom.armor.iceborne.IceborneModel
import de.fuballer.mcendgame.client.component.item.custom.armor.lamias_gift.LamiasGiftModel
import de.fuballer.mcendgame.client.component.item.custom.armor.wither_rose.WitherRoseBootsModel
import de.fuballer.mcendgame.client.component.item.custom.armor.wither_rose.WitherRoseChestplateModel
import de.fuballer.mcendgame.client.component.item.custom.armor.wither_rose.WitherRoseHelmetModel
import de.fuballer.mcendgame.client.component.item.custom.armor.wither_rose.WitherRoseLeggingsModel
import de.fuballer.mcendgame.main.component.item.custom.armor.CustomArmorItems
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
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
import net.minecraft.entity.EquipmentSlot
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
        texturedArmorModels[CustomArmorItems.WITHER_ROSE_HELMET] = TexturedArmorModel(
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/wither_rose.png"),
            WitherRoseHelmetModel(ctx.getPart(WitherRoseHelmetModel.MODEL_LAYER))
        )
        texturedArmorModels[CustomArmorItems.WITHER_ROSE_CHESTPLATE] = TexturedArmorModel(
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/wither_rose.png"),
            WitherRoseChestplateModel(ctx.getPart(WitherRoseChestplateModel.MODEL_LAYER))
        )
        texturedArmorModels[CustomArmorItems.WITHER_ROSE_LEGGINGS] = TexturedArmorModel(
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/wither_rose.png"),
            WitherRoseLeggingsModel(ctx.getPart(WitherRoseLeggingsModel.MODEL_LAYER))
        )
        texturedArmorModels[CustomArmorItems.WITHER_ROSE_BOOTS] = TexturedArmorModel(
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/wither_rose.png"),
            WitherRoseBootsModel(ctx.getPart(WitherRoseBootsModel.MODEL_LAYER))
        )
        texturedArmorModels[CustomArmorItems.FAE_LEGGINGS] = TexturedArmorModel(
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/fae.png"),
            FaeLeggingsModel(ctx.getPart(FaeLeggingsModel.MODEL_LAYER))
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
        val hiddenArmor = stateAccessor.`mcendgame$getHiddenArmor`()

        if (!hiddenArmor.contains(EquipmentSlot.HEAD)) {
            renderArmor(
                bipedEntityRenderState,
                matrixStack,
                vertexConsumerProvider,
                bipedEntityRenderState.equippedHeadStack,
                light
            )
        }
        if (!hiddenArmor.contains(EquipmentSlot.CHEST)) {
            renderArmor(
                bipedEntityRenderState,
                matrixStack,
                vertexConsumerProvider,
                bipedEntityRenderState.equippedChestStack,
                light
            )
        }
        if (!hiddenArmor.contains(EquipmentSlot.LEGS)) {
            renderArmor(
                bipedEntityRenderState,
                matrixStack,
                vertexConsumerProvider,
                bipedEntityRenderState.equippedLegsStack,
                light
            )
        }
        if (!hiddenArmor.contains(EquipmentSlot.FEET)) {
            renderArmor(
                bipedEntityRenderState,
                matrixStack,
                vertexConsumerProvider,
                bipedEntityRenderState.equippedFeetStack,
                light
            )
        }
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
            ItemRenderer.getArmorGlintConsumer(vertexConsumerProvider, RenderLayer.getArmorCutoutNoCull(texture), glint) // RenderLayer.getEntityTranslucent
        if (model is CustomVertexConsumer) {
            vertexConsumer = model.getVertexConsumer(bipedEntityRenderState, vertexConsumerProvider, vertexConsumer)
        }

        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV)
    }
}