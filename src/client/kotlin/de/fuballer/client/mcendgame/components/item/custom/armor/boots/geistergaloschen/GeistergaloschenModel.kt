package de.fuballer.client.mcendgame.components.item.custom.armor.boots.geistergaloschen

import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.render.entity.state.BipedEntityRenderState

class GeistergaloschenModel<S : BipedEntityRenderState>(
    root: ModelPart
) : BipedEntityModel<S>(root) {
    companion object {
        val MODEL_LAYER = EntityModelLayer(IdentifierUtil.default("geistergaloschen"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root

            val head = modelPartData.addChild(EntityModelPartNames.HEAD)
            val hat = head.addChild(EntityModelPartNames.HAT)
            val body = modelPartData.addChild(EntityModelPartNames.BODY)
            val right_arm = modelPartData.addChild(EntityModelPartNames.RIGHT_ARM)
            val left_arm = modelPartData.addChild(EntityModelPartNames.LEFT_ARM)

            val right_leg = modelPartData.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.pivot(-2.0f, 12.0f, 0.0f))

            val right_boot = right_leg.addChild(
                "right_boot", ModelPartBuilder.create().uv(0, 8).cuboid(-2.4f, 6.5f, -2.5f, 5.0f, 6.0f, 5.0f, Dilation(0.1f))
                    .uv(0, 0).cuboid(-2.4f, 6.5f, -2.5f, 5.0f, 2.0f, 5.0f, Dilation(0.3f)), ModelTransform.pivot(0.0f, 0.0f, 0.0f)
            )

            val left_leg = modelPartData.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.pivot(2.0f, 12.0f, 0.0f))

            val left_boot = left_leg.addChild(
                "left_boot", ModelPartBuilder.create().uv(21, 8).mirrored().cuboid(-2.6f, 6.5f, -2.5f, 5.0f, 6.0f, 5.0f, Dilation(0.1f)).mirrored(false)
                    .uv(21, 0).mirrored().cuboid(-2.6f, 6.5f, -2.5f, 5.0f, 2.0f, 5.0f, Dilation(0.3f)).mirrored(false), ModelTransform.pivot(0.0f, 0.0f, 0.0f)
            )
            return TexturedModelData.of(modelData, 64, 32)
        }
    }
}