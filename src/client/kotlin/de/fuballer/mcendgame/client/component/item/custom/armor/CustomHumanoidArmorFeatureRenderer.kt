package de.fuballer.mcendgame.client.component.item.custom.armor

import de.fuballer.mcendgame.client.component.item.custom.armor.model.bound_abyss.BoundAbyssModel
import de.fuballer.mcendgame.client.component.item.custom.armor.model.druids.DruidsBootsModel
import de.fuballer.mcendgame.client.component.item.custom.armor.model.druids.DruidsChestplateModel
import de.fuballer.mcendgame.client.component.item.custom.armor.model.druids.DruidsHelmetModel
import de.fuballer.mcendgame.client.component.item.custom.armor.model.druids.DruidsLeggingsModel
import de.fuballer.mcendgame.client.component.item.custom.armor.model.emberchant.EmberchantModel
import de.fuballer.mcendgame.client.component.item.custom.armor.model.geistergaloschen.GeistergaloschenModel
import de.fuballer.mcendgame.client.component.item.custom.armor.model.iceborne.IceborneModel
import de.fuballer.mcendgame.client.component.item.custom.armor.model.lamias_gift.LamiasGiftModel
import de.fuballer.mcendgame.client.component.item.custom.armor.model.moonshadow.MoonshadowModel
import de.fuballer.mcendgame.client.component.item.custom.armor.model.stoneward.StonewardModel
import de.fuballer.mcendgame.client.component.item.custom.armor.model.suede.SuedeBootsModel
import de.fuballer.mcendgame.client.component.item.custom.armor.model.suede.SuedeChestplateModel
import de.fuballer.mcendgame.client.component.item.custom.armor.model.suede.SuedeHelmetModel
import de.fuballer.mcendgame.client.component.item.custom.armor.model.suede.SuedeLeggingsModel
import de.fuballer.mcendgame.client.component.item.custom.armor.model.voidweaver.VoidweaverModel
import de.fuballer.mcendgame.client.component.item.custom.armor.model.wither_rose.WitherRoseBootsModel
import de.fuballer.mcendgame.client.component.item.custom.armor.model.wither_rose.WitherRoseChestplateModel
import de.fuballer.mcendgame.client.component.item.custom.armor.model.wither_rose.WitherRoseHelmetModel
import de.fuballer.mcendgame.client.component.item.custom.armor.model.wither_rose.WitherRoseLeggingsModel
import de.fuballer.mcendgame.client.component.item.custom.armor.transformer.EndermanArmorTransformer
import de.fuballer.mcendgame.client.component.item.custom.armor.transformer.EntityArmorTransformer
import de.fuballer.mcendgame.client.component.item.custom.armor.transformer.HuskArmorTransformer
import de.fuballer.mcendgame.client.component.item.custom.armor.transformer.WitherSkeletonArmorTransformer
import de.fuballer.mcendgame.client.util.BipedEntityRenderStateMixinExtension.getHiddenArmor
import de.fuballer.mcendgame.client.util.EntityRenderStateMixinExtension.isGhostly
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
import net.minecraft.client.render.entity.state.BipedEntityRenderState
import net.minecraft.client.render.item.ItemRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.component.type.DyedColorComponent
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

class CustomHumanoidArmorFeatureRenderer<S : BipedEntityRenderState, M : BipedEntityModel<S>>(
    featureContext: FeatureRendererContext<S, M>,
    ctx: EntityRendererFactory.Context,
) : FeatureRenderer<S, M>(featureContext) {

    private val armorTransformer: Map<EntityType<out Entity>, EntityArmorTransformer> = mapOf(
        EntityType.HUSK to HuskArmorTransformer(),
        EntityType.WITHER_SKELETON to WitherSkeletonArmorTransformer(),
        EntityType.ENDERMAN to EndermanArmorTransformer(),
    )

    private val texturedArmorModels: MutableMap<Item, TexturedArmorModel<BipedEntityModel<S>>> = mutableMapOf()

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
        texturedArmorModels[CustomArmorItems.SUEDE_HELMET] = TexturedArmorModel(
            SuedeHelmetModel(ctx.getPart(SuedeHelmetModel.MODEL_LAYER)),
            colorAbleTexture = IdentifierUtil.default("textures/entity/equipment/custom_humanoid/suede_color_able.png"),
            defaultColor = 10511680,
        )
        texturedArmorModels[CustomArmorItems.SUEDE_CHESTPLATE] = TexturedArmorModel(
            SuedeChestplateModel(ctx.getPart(SuedeChestplateModel.MODEL_LAYER)),
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/suede.png"),
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/suede_color_able.png"),
            defaultColor = 10511680,
        )
        texturedArmorModels[CustomArmorItems.SUEDE_LEGGINGS] = TexturedArmorModel(
            SuedeLeggingsModel(ctx.getPart(SuedeLeggingsModel.MODEL_LAYER)),
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/suede.png"),
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/suede_color_able.png"),
            defaultColor = 10511680,
        )
        texturedArmorModels[CustomArmorItems.SUEDE_BOOTS] = TexturedArmorModel(
            SuedeBootsModel(ctx.getPart(SuedeBootsModel.MODEL_LAYER)),
            colorAbleTexture = IdentifierUtil.default("textures/entity/equipment/custom_humanoid/suede_color_able.png"),
            defaultColor = 10511680,
        )
        texturedArmorModels[CustomArmorItems.STONEWARD] = TexturedArmorModel(
            StonewardModel(ctx.getPart(StonewardModel.MODEL_LAYER)),
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/stoneward.png"),
        )
        texturedArmorModels[CustomArmorItems.MOONSHADOW] = TexturedArmorModel(
            MoonshadowModel(ctx.getPart(MoonshadowModel.MODEL_LAYER)),
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/moonshadow.png"),
        )
        texturedArmorModels[CustomArmorItems.GEISTERGALOSCHEN] = TexturedArmorModel(
            GeistergaloschenModel(ctx.getPart(GeistergaloschenModel.MODEL_LAYER)),
            translucentTexture = IdentifierUtil.default("textures/entity/equipment/custom_humanoid/geistergaloschen.png"),
        )
        texturedArmorModels[CustomArmorItems.VOIDWEAVER] = TexturedArmorModel(
            VoidweaverModel(ctx.getPart(VoidweaverModel.MODEL_LAYER)),
            IdentifierUtil.default("textures/entity/equipment/custom_humanoid/voidweaver.png"),
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
        val hiddenArmor = bipedEntityRenderState.getHiddenArmor()

        if (!hiddenArmor.contains(EquipmentSlot.HEAD)) {
            renderArmor(
                bipedEntityRenderState,
                matrixStack,
                vertexConsumerProvider,
                bipedEntityRenderState.equippedHeadStack,
                light,
                EquipmentSlot.HEAD,
            )
        }
        if (!hiddenArmor.contains(EquipmentSlot.CHEST)) {
            renderArmor(
                bipedEntityRenderState,
                matrixStack,
                vertexConsumerProvider,
                bipedEntityRenderState.equippedChestStack,
                light,
                EquipmentSlot.CHEST,
            )
        }
        if (!hiddenArmor.contains(EquipmentSlot.LEGS)) {
            renderArmor(
                bipedEntityRenderState,
                matrixStack,
                vertexConsumerProvider,
                bipedEntityRenderState.equippedLegsStack,
                light,
                EquipmentSlot.LEGS,
            )
        }
        if (!hiddenArmor.contains(EquipmentSlot.FEET)) {
            renderArmor(
                bipedEntityRenderState,
                matrixStack,
                vertexConsumerProvider,
                bipedEntityRenderState.equippedFeetStack,
                light,
                EquipmentSlot.FEET,
            )
        }
    }

    private fun renderArmor(
        bipedEntityRenderState: S,
        matrices: MatrixStack,
        vertexConsumerProvider: VertexConsumerProvider,
        itemStack: ItemStack,
        light: Int,
        slot: EquipmentSlot,
    ) {
        val item = itemStack.item
        val texturedArmorModel = texturedArmorModels[item] ?: return

        val model = texturedArmorModel.model
        contextModel.copyTransforms(model)

        model.setAngles(bipedEntityRenderState)

        matrices.push()
        armorTransformer[bipedEntityRenderState.entityType]?.transform(slot, matrices, model)

        if (texturedArmorModel.texture != null) {
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

        if (texturedArmorModel.colorAbleTexture != null) {
            renderModel(
                bipedEntityRenderState,
                model,
                texturedArmorModel.colorAbleTexture,
                matrices,
                vertexConsumerProvider,
                light,
                itemStack.hasGlint(),
                DyedColorComponent.getColor(itemStack, texturedArmorModel.defaultColor),
            )
        }

        if (texturedArmorModel.translucentTexture != null) {
            renderModel(
                bipedEntityRenderState,
                model,
                texturedArmorModel.translucentTexture,
                matrices,
                vertexConsumerProvider,
                light,
                itemStack.hasGlint(),
                translucent = true,
            )
        }

        matrices.pop()
    }

    private fun renderModel(
        bipedEntityRenderState: S,
        model: Model,
        texture: Identifier,
        matrices: MatrixStack,
        vertexConsumerProvider: VertexConsumerProvider,
        light: Int,
        glint: Boolean,
        color: Int = -1,
        translucent: Boolean = false,
    ) {
        var renderLayer = if (translucent || bipedEntityRenderState.isGhostly()) RenderLayer.getEntityTranslucent(texture) else RenderLayer.getArmorCutoutNoCull(texture)

        var vertexConsumer = ItemRenderer.getArmorGlintConsumer(
            vertexConsumerProvider,
            renderLayer,
            glint
        )

        if (model is CustomVertexConsumer) {
            vertexConsumer = model.getVertexConsumer(bipedEntityRenderState, vertexConsumerProvider, vertexConsumer)
        }

        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, color)
    }
}