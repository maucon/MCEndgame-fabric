package de.fuballer.client.mcendgame.components.item.custom.armor.boots.druids_boots

import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.render.entity.state.BipedEntityRenderState

class DruidsBootsModel<S : BipedEntityRenderState>(
    root: ModelPart
) : BipedEntityModel<S>(root) {
    companion object {
        val MODEL_LAYER = EntityModelLayer(IdentifierUtil.default("druids_boots"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root

            val head = modelPartData.addChild(EntityModelPartNames.HEAD)
            val hat = head.addChild(EntityModelPartNames.HAT)
            val body = modelPartData.addChild(EntityModelPartNames.BODY)
            val left_arm = modelPartData.addChild(EntityModelPartNames.LEFT_ARM)
            val right_arm = modelPartData.addChild(EntityModelPartNames.RIGHT_ARM)

            val left_leg =
                modelPartData.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create(), ModelTransform.origin(2.0f, 12.0f, 0.0f))

            val left_boot = left_leg.addChild(
                "left_boot",
                ModelPartBuilder.create().uv(22, 19).cuboid(-2.5f, 9.35f, -2.5f, 5.0f, 3.0f, 5.0f, Dilation(0.25f))
                    .uv(24, 10).cuboid(-2.0f, 6.0f, -2.0f, 4.0f, 5.0f, 4.0f, Dilation(0.55f)),
                ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )

            val left_boot_top = left_boot.addChild(
                "left_boot_top",
                ModelPartBuilder.create().uv(22, 0).cuboid(-2.5f, -5.25f, 0.25f, 5.0f, 5.0f, 5.0f, Dilation(0.25f)),
                ModelTransform.of(0.0f, 6.5f, -2.6f, 0.0873f, 0.0f, 0.0f)
            )

            val right_leg =
                modelPartData.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create(), ModelTransform.origin(-2.0f, 12.0f, 0.0f))

            val right_boot = right_leg.addChild(
                "right_boot",
                ModelPartBuilder.create().uv(0, 19).cuboid(-2.5f, 9.35f, -2.5f, 5.0f, 3.0f, 5.0f, Dilation(0.25f))
                    .uv(2, 10).cuboid(-2.0f, 6.0f, -2.0f, 4.0f, 5.0f, 4.0f, Dilation(0.55f)),
                ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )

            val right_boot_top = right_boot.addChild(
                "right_boot_top",
                ModelPartBuilder.create().uv(0, 0).cuboid(-2.5f, -5.25f, 0.25f, 5.0f, 5.0f, 5.0f, Dilation(0.25f)),
                ModelTransform.of(0.0f, 6.5f, -2.6f, 0.0873f, 0.0f, 0.0f)
            )
            return TexturedModelData.of(modelData, 128, 128)
        }
    }
}