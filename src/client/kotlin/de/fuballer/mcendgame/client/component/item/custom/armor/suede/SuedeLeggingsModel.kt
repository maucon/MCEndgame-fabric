package de.fuballer.mcendgame.client.component.item.custom.armor.suede

import de.fuballer.mcendgame.client.component.item.custom.ModelPartDataExtension.createEmptyChild
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.render.entity.state.BipedEntityRenderState


class SuedeLeggingsModel<S : BipedEntityRenderState>(
    root: ModelPart
) : BipedEntityModel<S>(root) {
    companion object {
        val MODEL_LAYER = EntityModelLayer(IdentifierUtil.default("suede_leggings"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root

            val head = modelPartData.createEmptyChild(EntityModelPartNames.HEAD)
            val hat = head.createEmptyChild(EntityModelPartNames.HAT)
            val left_arm = modelPartData.createEmptyChild(EntityModelPartNames.LEFT_ARM)
            val right_arm = modelPartData.createEmptyChild(EntityModelPartNames.RIGHT_ARM)

            val body = modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create(), ModelTransform.origin(0.0f, 0.0f, 0.0f))

            val leggings_waist = body.addChild("leggings_waist", ModelPartBuilder.create(), ModelTransform.origin(0.0f, 0.0f, 0.0f))

            val left_belt = leggings_waist.addChild(
                "left_belt",
                ModelPartBuilder.create().uv(34, 44).cuboid(-5.6f, -0.4f, -2.95f, 6.0f, 3.0f, 6.0f, Dilation(-0.4f)),
                ModelTransform.of(4.6f, 8.65f, 0.0f, 0.0f, 0.0f, -0.0873f)
            )

            val right_belt = leggings_waist.addChild(
                "right_belt",
                ModelPartBuilder.create().uv(8, 44).cuboid(-0.4f, 0.1f, -3.05f, 6.0f, 3.0f, 6.0f, Dilation(-0.4f)),
                ModelTransform.of(-4.6f, 8.15f, 0.0f, 0.0f, 0.0f, 0.0873f)
            )

            val left_leg = modelPartData.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create(), ModelTransform.origin(2.0f, 12.0f, 0.0f))

            val left_leggings = left_leg.addChild("left_leggings", ModelPartBuilder.create(), ModelTransform.origin(0.0f, 0.0f, 0.0f))

            val left_skirt2 = left_leggings.addChild(
                "left_skirt2",
                ModelPartBuilder.create().uv(36, 54).cuboid(-5.1f, 1.1f, -2.45f, 5.0f, 7.0f, 5.0f, Dilation(-0.1f)),
                ModelTransform.of(2.6f, -3.35f, 0.0f, 0.0f, 0.0f, -0.0436f)
            )

            val right_leg = modelPartData.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create(), ModelTransform.origin(-2.0f, 12.0f, 0.0f))

            val right_leggings = right_leg.addChild("right_leggings", ModelPartBuilder.create(), ModelTransform.origin(0.0f, 0.0f, 0.0f))

            val right_skirt = right_leggings.addChild(
                "right_skirt",
                ModelPartBuilder.create().uv(10, 54).cuboid(0.1f, 1.6f, -2.55f, 5.0f, 7.0f, 5.0f, Dilation(-0.1f)),
                ModelTransform.of(-2.6f, -3.85f, 0.0f, 0.0f, 0.0f, 0.0436f)
            )
            return TexturedModelData.of(modelData, 128, 128)
        }
    }

    override fun setAngles(bipedEntityRenderState: S) {}
}