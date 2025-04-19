package de.fuballer.client.mcendgame.components.item.custom.armor.helmet.iceborne

import de.fuballer.client.mcendgame.components.item.custom.ModelPartDataExtension.createEmptyChild
import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.render.entity.state.BipedEntityRenderState

class IceborneModel<S : BipedEntityRenderState>(
    root: ModelPart
) : BipedEntityModel<S>(root) {
    companion object {
        val MODEL_LAYER = EntityModelLayer(IdentifierUtil.default("iceborne"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root

            val head = modelPartData.createEmptyChild(EntityModelPartNames.HEAD)
            val hat = head.createEmptyChild(EntityModelPartNames.HAT)
            val body = modelPartData.createEmptyChild(EntityModelPartNames.BODY)
            val right_arm = modelPartData.createEmptyChild(EntityModelPartNames.RIGHT_ARM)
            val left_arm = modelPartData.createEmptyChild(EntityModelPartNames.LEFT_ARM)
            val right_leg = modelPartData.createEmptyChild(EntityModelPartNames.RIGHT_LEG)
            val left_leg = modelPartData.createEmptyChild(EntityModelPartNames.LEFT_LEG)

            val helmet = head.addChild(
                "helmet",
                ModelPartBuilder.create().uv(16, 48).cuboid(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, Dilation(1.0f))
                    .uv(18, 31).cuboid(-1.0f, -9.75f, -6.0f, 2.0f, 4.0f, 12.0f, Dilation(0.0f))
                    .uv(22, 20).cuboid(-1.0f, -11.2f, -4.0f, 2.0f, 2.0f, 8.0f, Dilation(-0.25f))
                    .uv(29, 15).cuboid(-1.0f, -6.25f, -5.9f, 2.0f, 3.0f, 1.0f, Dilation(-0.1f)),
                ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )

            val left_horn = helmet.addChild(
                "left_horn",
                ModelPartBuilder.create().uv(44, 0).cuboid(-2.0f, -3.0f, -3.0f, 4.0f, 6.0f, 6.0f, Dilation(0.0f)),
                ModelTransform.of(4.5f, -6.25f, 1.0f, 0.0f, 0.0f, -0.1309f)
            )

            val left_horn_0 = left_horn.addChild(
                "left_horn_0",
                ModelPartBuilder.create().uv(46, 13).cuboid(-1.25f, -2.0f, -2.0f, 4.0f, 4.0f, 4.0f, Dilation(0.0f)),
                ModelTransform.of(2.0f, 0.0f, 0.0f, 0.0f, 0.0873f, -0.0873f)
            )

            val left_horn_1 = left_horn_0.addChild(
                "left_horn_1",
                ModelPartBuilder.create().uv(47, 22).cuboid(-1.5f, -1.5f, -1.5f, 4.0f, 3.0f, 3.0f, Dilation(0.0f)),
                ModelTransform.of(2.75f, -0.2f, -0.1f, -0.0669f, 0.3431f, -0.5344f)
            )

            val left_horn_2 = left_horn_1.addChild(
                "left_horn_2",
                ModelPartBuilder.create().uv(47, 29).cuboid(-1.05f, -1.25f, -1.5f, 4.0f, 3.0f, 3.0f, Dilation(-0.125f)),
                ModelTransform.of(2.25f, -0.5f, -0.25f, 0.0f, 0.2618f, -0.6109f)
            )

            val left_horn_3 = left_horn_2.addChild(
                "left_horn_3",
                ModelPartBuilder.create().uv(49, 36).cuboid(-0.6f, -1.05f, -1.1f, 3.0f, 2.0f, 2.0f, Dilation(0.1f)),
                ModelTransform.of(2.8f, 0.25f, 0.0f, -0.0832f, -0.1882f, -0.4456f)
            )

            val left_horn_4 = left_horn_3.addChild(
                "left_horn_4",
                ModelPartBuilder.create().uv(49, 41).cuboid(-1.0f, -1.1f, -1.0f, 3.0f, 2.0f, 2.0f, Dilation(-0.15f)),
                ModelTransform.of(2.4f, 0.0f, -0.1f, 0.0f, 0.0f, -0.3491f)
            )

            val left_horn_5 = left_horn_4.addChild(
                "left_horn_5",
                ModelPartBuilder.create().uv(49, 46).cuboid(-1.0f, -0.9f, -1.0f, 3.0f, 2.0f, 2.0f, Dilation(-0.35f)),
                ModelTransform.of(1.8f, -0.2f, 0.0f, -0.0873f, -0.0873f, 0.1745f)
            )

            val left_horn_6 = left_horn_5.addChild(
                "left_horn_6",
                ModelPartBuilder.create().uv(51, 51).cuboid(-0.2f, -0.4f, -0.5f, 2.0f, 1.0f, 1.0f, Dilation(0.0f)),
                ModelTransform.of(1.6f, 0.1f, 0.0f, -0.3054f, -0.1745f, 0.2618f)
            )

            val right_horn = helmet.addChild(
                "right_horn",
                ModelPartBuilder.create().uv(0, 0).cuboid(-2.0f, -3.0f, -3.0f, 4.0f, 6.0f, 6.0f, Dilation(0.0f)),
                ModelTransform.of(-4.5f, -6.25f, 1.0f, 0.0f, 0.0f, 0.1309f)
            )

            val right_horn_0 = right_horn.addChild(
                "right_horn_0",
                ModelPartBuilder.create().uv(2, 13).cuboid(-2.75f, -2.0f, -2.0f, 4.0f, 4.0f, 4.0f, Dilation(0.0f)),
                ModelTransform.of(-2.0f, 0.0f, 0.0f, 0.0f, -0.0873f, 0.0873f)
            )

            val right_horn_1 = right_horn_0.addChild(
                "right_horn_1",
                ModelPartBuilder.create().uv(3, 22).cuboid(-2.5f, -1.5f, -1.5f, 4.0f, 3.0f, 3.0f, Dilation(0.0f)),
                ModelTransform.of(-2.75f, -0.2f, -0.1f, -0.0669f, -0.3431f, 0.5344f)
            )

            val right_horn_2 = right_horn_1.addChild(
                "right_horn_2",
                ModelPartBuilder.create().uv(3, 29).cuboid(-2.95f, -1.25f, -1.5f, 4.0f, 3.0f, 3.0f, Dilation(-0.125f)),
                ModelTransform.of(-2.25f, -0.5f, -0.25f, 0.0f, -0.2618f, 0.6109f)
            )

            val right_horn_3 = right_horn_2.addChild(
                "right_horn_3",
                ModelPartBuilder.create().uv(5, 36).cuboid(-2.4f, -1.05f, -1.1f, 3.0f, 2.0f, 2.0f, Dilation(0.1f)),
                ModelTransform.of(-2.8f, 0.25f, 0.0f, -0.0832f, 0.1882f, 0.4456f)
            )

            val right_horn_4 = right_horn_3.addChild(
                "right_horn_4",
                ModelPartBuilder.create().uv(5, 41).cuboid(-2.0f, -1.1f, -1.0f, 3.0f, 2.0f, 2.0f, Dilation(-0.15f)),
                ModelTransform.of(-2.4f, 0.0f, -0.1f, 0.0f, 0.0f, 0.3491f)
            )

            val right_horn_5 = right_horn_4.addChild(
                "right_horn_5",
                ModelPartBuilder.create().uv(5, 46).cuboid(-2.0f, -0.9f, -1.0f, 3.0f, 2.0f, 2.0f, Dilation(-0.35f)),
                ModelTransform.of(-1.8f, -0.2f, 0.0f, -0.0873f, 0.0873f, -0.1745f)
            )

            val right_horn_6 = right_horn_5.addChild(
                "right_horn_6",
                ModelPartBuilder.create().uv(7, 51).cuboid(-1.8f, -0.4f, -0.5f, 2.0f, 1.0f, 1.0f, Dilation(0.0f)),
                ModelTransform.of(-1.6f, 0.1f, 0.0f, -0.3054f, 0.1745f, -0.2618f)
            )
            return TexturedModelData.of(modelData, 64, 64)
        }
    }
}