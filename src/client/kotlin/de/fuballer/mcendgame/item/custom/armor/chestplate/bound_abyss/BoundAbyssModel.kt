package de.fuballer.mcendgame.item.custom.armor.chestplate.bound_abyss

import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.render.entity.state.BipedEntityRenderState


class BoundAbyssModel<S : BipedEntityRenderState>(
    root: ModelPart
) : BipedEntityModel<S>(root) {
    companion object {
        val MODEL_LAYER = EntityModelLayer(IdentifierUtil.default("bound_abyss"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root

            val head = modelPartData.addChild(EntityModelPartNames.HEAD)
            val hat = head.addChild(EntityModelPartNames.HAT)
            val right_leg = modelPartData.addChild(EntityModelPartNames.RIGHT_LEG)
            val left_leg = modelPartData.addChild(EntityModelPartNames.LEFT_LEG)

            val body = modelPartData.addChild(
                EntityModelPartNames.BODY,
                ModelPartBuilder.create(),
                ModelTransform.pivot(0.0f, 0.0f, 0.0f)
            )

            val left_arm =
                modelPartData.addChild(
                    EntityModelPartNames.LEFT_ARM,
                    ModelPartBuilder.create(),
                    ModelTransform.pivot(5.0f, 2.0f, 0.0f)
                )

            val right_arm =
                modelPartData.addChild(
                    EntityModelPartNames.RIGHT_ARM,
                    ModelPartBuilder.create(),
                    ModelTransform.pivot(-5.0f, 2.0f, 0.0f)
                )

            val chestplateBody: ModelPartData = body.addChild(
                "chestplateBody",
                ModelPartBuilder.create().uv(18, 35).cuboid(-4.5f, -24.5f, -2.5f, 9.0f, 14.0f, 5.0f, Dilation(0.25f))
                    .uv(16, 22).cuboid(-5.0f, -24.85f, -3.0f, 10.0f, 7.0f, 6.0f, Dilation(0.0f)),
                ModelTransform.pivot(0.0f, 24.0f, 0.0f)
            )

            val belt = chestplateBody.addChild(
                "belt",
                ModelPartBuilder.create().uv(14, 54).cuboid(-5.5f, -2.0f, -3.5f, 11.0f, 3.0f, 7.0f, Dilation(-0.5f)),
                ModelTransform.of(0.0f, -15.25f, 0.0f, 0.0f, 0.0f, -0.0873f)
            )

            val chestplateArmLeft = left_arm.addChild(
                "chestplateArmLeft",
                ModelPartBuilder.create(),
                ModelTransform.pivot(-5.0f, 22.0f, 0.0f)
            )

            val shoulderPadLeft = chestplateArmLeft.addChild(
                "shoulderPadLeft",
                ModelPartBuilder.create().uv(44, 18).cuboid(-3.0f, -2.0f, -2.5f, 5.0f, 5.0f, 5.0f, Dilation(0.0f)),
                ModelTransform.of(7.0f, -23.0f, 0.0f, 0.0436f, 0.0436f, 0.1309f)
            )

            val topSpikeLeft = shoulderPadLeft.addChild(
                "topSpikeLeft",
                ModelPartBuilder.create().uv(48, 13).cuboid(-1.0f, -2.0f, -1.0f, 2.0f, 3.0f, 2.0f, Dilation(0.0f))
                    .uv(50, 10).cuboid(-0.5f, -4.0f, -0.5f, 1.0f, 2.0f, 1.0f, Dilation(0.0f)),
                ModelTransform.of(0.0f, -2.0f, 0.0f, 0.0f, 0.0f, 0.3927f)
            )

            val sideSpikeLeft = shoulderPadLeft.addChild(
                "sideSpikeLeft",
                ModelPartBuilder.create().uv(56, 14).cuboid(-1.0f, -2.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.0f))
                    .uv(58, 11).cuboid(-0.5f, -4.0f, -0.5f, 1.0f, 2.0f, 1.0f, Dilation(0.0f)),
                ModelTransform.of(2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.5708f)
            )

            val sleeveLeft = chestplateArmLeft.addChild(
                "sleeveLeft",
                ModelPartBuilder.create().uv(48, 30).cuboid(4.0f, -24.0f, -2.0f, 4.0f, 12.0f, 4.0f, Dilation(0.35f)),
                ModelTransform.pivot(0.0f, 0.0f, 0.0f)
            )

            val vambraceLeft = sleeveLeft.addChild(
                "vambraceLeft",
                ModelPartBuilder.create().uv(48, 46).cuboid(4.0f, -18.0f, -2.0f, 4.0f, 6.0f, 4.0f, Dilation(0.5f)),
                ModelTransform.pivot(0.0f, 0.0f, 0.0f)
            )

            val vambraceSpikeLeft = vambraceLeft.addChild(
                "vambraceSpikeLeft",
                ModelPartBuilder.create().uv(53, 59).cuboid(-1.25f, -3.25f, -1.0f, 1.0f, 3.0f, 2.0f, Dilation(0.25f))
                    .uv(54, 56).cuboid(-1.25f, -5.0f, -0.5f, 1.0f, 2.0f, 1.0f, Dilation(0.0f)),
                ModelTransform.of(8.5f, -13.75f, 0.0f, 0.0f, 0.0f, 0.3927f)
            )

            val chestplateArmRight = right_arm.addChild(
                "chestplateArmRight",
                ModelPartBuilder.create(),
                ModelTransform.pivot(5.0f, 22.0f, 0.0f)
            )

            val shoulderPadRight = chestplateArmRight.addChild(
                "shoulderPadRight",
                ModelPartBuilder.create().uv(0, 18).cuboid(-2.0f, -2.0f, -2.5f, 5.0f, 5.0f, 5.0f, Dilation(0.0f)),
                ModelTransform.of(-7.0f, -23.0f, 0.0f, 0.0436f, -0.0436f, -0.1309f)
            )

            val topSpikeRight = shoulderPadRight.addChild(
                "topSpikeRight",
                ModelPartBuilder.create().uv(8, 13).cuboid(-1.0f, -2.0f, -1.0f, 2.0f, 3.0f, 2.0f, Dilation(0.0f))
                    .uv(10, 10).mirrored().cuboid(-0.5f, -4.0f, -0.5f, 1.0f, 2.0f, 1.0f, Dilation(0.0f))
                    .mirrored(false),
                ModelTransform.of(0.0f, -2.0f, 0.0f, 0.0f, 0.0f, -0.3927f)
            )

            val sideSpikeRight = shoulderPadRight.addChild(
                "sideSpikeRight",
                ModelPartBuilder.create().uv(0, 14).cuboid(-1.0f, -2.0f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.0f))
                    .uv(2, 11).cuboid(-0.5f, -4.0f, -0.5f, 1.0f, 2.0f, 1.0f, Dilation(0.0f)),
                ModelTransform.of(-2.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.5708f)
            )

            val sleeveRight = chestplateArmRight.addChild(
                "sleeveRight",
                ModelPartBuilder.create().uv(0, 30).cuboid(-8.0f, -24.0f, -2.0f, 4.0f, 12.0f, 4.0f, Dilation(0.35f)),
                ModelTransform.pivot(0.0f, 0.0f, 0.0f)
            )

            val vambraceRight = sleeveRight.addChild(
                "vambraceRight",
                ModelPartBuilder.create().uv(0, 46).cuboid(-8.0f, -18.0f, -2.0f, 4.0f, 6.0f, 4.0f, Dilation(0.5f)),
                ModelTransform.pivot(0.0f, 0.0f, 0.0f)
            )

            val vambraceSpikeRight = vambraceRight.addChild(
                "vambraceSpikeRight",
                ModelPartBuilder.create().uv(5, 59).cuboid(0.25f, -3.25f, -1.0f, 1.0f, 3.0f, 2.0f, Dilation(0.25f))
                    .uv(6, 56).cuboid(0.25f, -5.0f, -0.5f, 1.0f, 2.0f, 1.0f, Dilation(0.0f)),
                ModelTransform.of(-8.5f, -13.75f, 0.0f, 0.0f, 0.0f, -0.3927f)
            )
            return TexturedModelData.of(modelData, 64, 64)
            return TexturedModelData.of(modelData, 64, 64)
        }
    }
}