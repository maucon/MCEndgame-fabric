package de.fuballer.client.mcendgame.component.item.custom.armor.helmet.druids_helmet

import de.fuballer.client.mcendgame.component.item.custom.ModelPartDataExtension.createEmptyChild
import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.render.entity.state.BipedEntityRenderState

class DruidsHelmetModel<S : BipedEntityRenderState>(
    root: ModelPart
) : BipedEntityModel<S>(root) {
    companion object {
        val MODEL_LAYER = EntityModelLayer(IdentifierUtil.default("druids_helmet"), "main")

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
                ModelPartBuilder.create().uv(38, 73).cuboid(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, Dilation(0.6f)),
                ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )

            val crystal = helmet.addChild(
                "crystal",
                ModelPartBuilder.create().uv(36, 76).cuboid(-3.0f, -3.0f, -2.0f, 3.0f, 3.0f, 2.0f, Dilation(0.0f)),
                ModelTransform.of(0.0f, -5.5f, -3.0f, 0.0f, 0.0f, 0.7854f)
            )

            val helmet_horn_left_base = helmet.addChild(
                "helmet_horn_left_base",
                ModelPartBuilder.create().uv(70, 83).cuboid(0.0f, -3.0f, -1.5f, 1.0f, 3.0f, 3.0f, Dilation(0.0f)),
                ModelTransform.of(4.5f, -4.75f, 2.0f, 0.2182f, 0.0f, -0.0873f)
            )

            val helmet_horn_left_0 = helmet_horn_left_base.addChild(
                "helmet_horn_left_0",
                ModelPartBuilder.create().uv(78, 85).cuboid(0.0f, -2.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.0f)),
                ModelTransform.of(1.0f, -0.5f, 0.0f, 0.0f, 0.0f, -0.4363f)
            )

            val helmet_horn_left_1 = helmet_horn_left_0.addChild(
                "helmet_horn_left_1",
                ModelPartBuilder.create().uv(86, 85).cuboid(-0.25f, -1.75f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(-0.25f)),
                ModelTransform.of(2.0f, -0.25f, 0.0f, 0.0f, 0.0f, -0.4363f)
            )

            val helmet_horn_left_2 = helmet_horn_left_1.addChild(
                "helmet_horn_left_2",
                ModelPartBuilder.create().uv(94, 85).cuboid(-0.5f, -1.5f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(-0.5f)),
                ModelTransform.of(1.5f, -0.25f, 0.0f, 0.0f, 0.0f, -0.4363f)
            )

            val helmet_horn_left_3 = helmet_horn_left_2.addChild(
                "helmet_horn_left_3",
                ModelPartBuilder.create().uv(102, 87).cuboid(-0.5f, -0.75f, -0.5f, 2.0f, 1.0f, 1.0f, Dilation(-0.25f)),
                ModelTransform.of(0.5f, -0.15f, 0.0f, 0.0f, 0.0f, -0.1309f)
            )

            val helmet_horn_right_base = helmet.addChild(
                "helmet_horn_right_base",
                ModelPartBuilder.create().uv(30, 83).cuboid(-1.0f, -3.0f, -1.5f, 1.0f, 3.0f, 3.0f, Dilation(0.0f)),
                ModelTransform.of(-4.5f, -4.75f, 2.0f, 0.2182f, 0.0f, 0.0873f)
            )

            val helmet_horn_right_0 = helmet_horn_right_base.addChild(
                "helmet_horn_right_0",
                ModelPartBuilder.create().uv(22, 85).cuboid(-2.0f, -2.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.0f)),
                ModelTransform.of(-1.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.4363f)
            )

            val helmet_horn_right_1 = helmet_horn_right_0.addChild(
                "helmet_horn_right_1",
                ModelPartBuilder.create().uv(14, 85).cuboid(-1.75f, -1.75f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(-0.25f)),
                ModelTransform.of(-2.0f, -0.25f, 0.0f, 0.0f, 0.0f, 0.4363f)
            )

            val helmet_horn_right_2 = helmet_horn_right_1.addChild(
                "helmet_horn_right_2",
                ModelPartBuilder.create().uv(6, 85).cuboid(-1.5f, -1.5f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(-0.5f)),
                ModelTransform.of(-1.5f, -0.25f, 0.0f, 0.0f, 0.0f, 0.4363f)
            )

            val helmet_horn_right_3 = helmet_horn_right_2.addChild(
                "helmet_horn_right_3",
                ModelPartBuilder.create().uv(0, 87).cuboid(-1.5f, -0.75f, -0.5f, 2.0f, 1.0f, 1.0f, Dilation(-0.25f)),
                ModelTransform.of(-0.5f, -0.15f, 0.0f, 0.0f, 0.0f, 0.1309f)
            )
            return TexturedModelData.of(modelData, 128, 128)
        }
    }

    override fun setAngles(bipedEntityRenderState: S) {}
}