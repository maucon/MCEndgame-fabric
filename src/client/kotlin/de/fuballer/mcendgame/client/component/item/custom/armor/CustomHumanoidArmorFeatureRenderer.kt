package de.fuballer.mcendgame.client.component.item.custom.armor

import de.fuballer.mcendgame.client.accessor.BipedEntityRenderStateAccessor
import de.fuballer.mcendgame.client.component.item.custom.armor.bound_abyss.BoundAbyssModel
import de.fuballer.mcendgame.client.component.item.custom.armor.druids.DruidsBootsModel
import de.fuballer.mcendgame.client.component.item.custom.armor.druids.DruidsChestplateModel
import de.fuballer.mcendgame.client.component.item.custom.armor.druids.DruidsHelmetModel
import de.fuballer.mcendgame.client.component.item.custom.armor.druids.DruidsLeggingsModel
import de.fuballer.mcendgame.client.component.item.custom.armor.emberchant.EmberchantModel
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
            IceborneModel(ctx.getPart(IceborneModel.MODEL_LAYER)),
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/iceborne.png"),
        )
        texturedArmorModels[CustomArmorItems.BOUND_ABYSS] = TexturedArmorModel(
            BoundAbyssModel(ctx.getPart(BoundAbyssModel.MODEL_LAYER)),
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/bound_abyss.png"),
        )
        texturedArmorModels[CustomArmorItems.DRUIDS_HELMET] = TexturedArmorModel(
            DruidsHelmetModel(ctx.getPart(DruidsHelmetModel.MODEL_LAYER)),
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/druids.png"),
        )
        texturedArmorModels[CustomArmorItems.DRUIDS_CHESTPLATE] = TexturedArmorModel(
            DruidsChestplateModel(ctx.getPart(DruidsChestplateModel.MODEL_LAYER)),
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/druids.png"),
        )
        texturedArmorModels[CustomArmorItems.DRUIDS_LEGGINGS] = TexturedArmorModel(
            DruidsLeggingsModel(ctx.getPart(DruidsLeggingsModel.MODEL_LAYER)),
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/druids.png"),
        )
        texturedArmorModels[CustomArmorItems.DRUIDS_BOOTS] = TexturedArmorModel(
            DruidsBootsModel(ctx.getPart(DruidsBootsModel.MODEL_LAYER)),
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/druids.png"),
        )
        texturedArmorModels[CustomArmorItems.EMBERCHANT] = TexturedArmorModel(
            EmberchantModel(ctx.getPart(EmberchantModel.MODEL_LAYER)),
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/emberchant.png"),
        )
        texturedArmorModels[CustomArmorItems.LAMIAS_GIFT] = TexturedArmorModel(
            LamiasGiftModel(ctx.getPart(LamiasGiftModel.MODEL_LAYER)),
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/lamias_gift.png"),
        )
        texturedArmorModels[CustomArmorItems.WITHER_ROSE_HELMET] = TexturedArmorModel(
            WitherRoseHelmetModel(ctx.getPart(WitherRoseHelmetModel.MODEL_LAYER)),
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/wither_rose.png"),
        )
        texturedArmorModels[CustomArmorItems.WITHER_ROSE_CHESTPLATE] = TexturedArmorModel(
            WitherRoseChestplateModel(ctx.getPart(WitherRoseChestplateModel.MODEL_LAYER)),
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/wither_rose.png"),
        )
        texturedArmorModels[CustomArmorItems.WITHER_ROSE_LEGGINGS] = TexturedArmorModel(
            WitherRoseLeggingsModel(ctx.getPart(WitherRoseLeggingsModel.MODEL_LAYER)),
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/wither_rose.png"),
        )
        texturedArmorModels[CustomArmorItems.WITHER_ROSE_BOOTS] = TexturedArmorModel(
            WitherRoseBootsModel(ctx.getPart(WitherRoseBootsModel.MODEL_LAYER)),
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/wither_rose.png"),
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

        val translucentTexture = texturedArmorModel.translucentTexture ?: return
        renderModel(
            bipedEntityRenderState,
            model,
            translucentTexture,
            matrices,
            vertexConsumerProvider,
            light,
            itemStack.hasGlint(),
            true,
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
        translucent: Boolean = false,
    ) {
        var vertexConsumer = ItemRenderer.getArmorGlintConsumer(
            vertexConsumerProvider,
            if (translucent) RenderLayer.getEntityTranslucent(texture) else RenderLayer.getArmorCutoutNoCull(texture),
            glint
        )

        if (model is CustomVertexConsumer) {
            vertexConsumer = model.getVertexConsumer(bipedEntityRenderState, vertexConsumerProvider, vertexConsumer)
        }

        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV)
    }
}