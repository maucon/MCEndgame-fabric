package de.fuballer.mcendgame.components.entity.custom.swamp_golem

import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer


class SwampGolemEntityModel(
    modelPart: ModelPart,
) : EntityModel<SwampGolemRenderState>(modelPart) {
    val body = root.getChild("body")
    val lowerBody = body.getChild("lower_body")
    val upperBody = lowerBody.getChild("upper_body")
    val leftArm = upperBody.getChild("left_arm")
    val upperLeftArm = leftArm.getChild("upper_left_arm")
    val lowerLeftArm = upperLeftArm.getChild("lower_left_arm")
    val rightArm = upperBody.getChild("right_arm")
    val upperRightArm = rightArm.getChild("upper_right_arm")
    val lowerRightArm = upperRightArm.getChild("lower_right_arm")
    val head = upperBody.getChild("head")
    val leftLeg = lowerBody.getChild("left_leg")
    val upperLeftLeg = leftLeg.getChild("upper_left_leg")
    val lowerLeftLeg = upperLeftLeg.getChild("lower_left_leg")
    val rightLeg = lowerBody.getChild("right_leg")
    val upperRightLeg = rightLeg.getChild("upper_right_leg")
    val lowerRightLeg = upperRightLeg.getChild("lower_right_leg")

    companion object {
        val SWAMP_GOLEM = EntityModelLayer(IdentifierUtil.default("swamp_golem"), "main")

        val SLAM_RAISE_TIME = 0.6F
        val SLAM_STRIKE_TIME = 0.15F
        val SLAM_RESET_TIME = 0.25F

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root
            val body =
                modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0f, 10.0f, 2.75f))

            val lower_body = body.addChild(
                "lower_body",
                ModelPartBuilder.create().uv(46, 65).cuboid(-5.5f, -8.0f, -2.5f, 11.0f, 8.0f, 5.0f, Dilation(0.0f)),
                ModelTransform.of(0.0f, 1.5f, 0.0f, 0.0436f, 0.0f, 0.0f)
            )

            val upper_body = lower_body.addChild(
                "upper_body",
                ModelPartBuilder.create().uv(42, 50).cuboid(-6.5f, -7.5f, -4.0f, 13.0f, 8.0f, 7.0f, Dilation(0.0f))
                    .uv(42, 34).cuboid(-6.5f, -7.5f, -4.0f, 13.0f, 9.0f, 7.0f, Dilation(0.25f)),
                ModelTransform.of(0.0f, -7.0f, 0.0f, 0.3054f, 0.0f, 0.0f)
            )

            val left_arm =
                upper_body.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.pivot(6.75f, -6.25f, 0.15f))

            val upper_left_arm = left_arm.addChild(
                "upper_left_arm",
                ModelPartBuilder.create().uv(83, 52)
                    .cuboid(-0.5198f, -0.9918f, -2.7492f, 4.0f, 8.0f, 5.0f, Dilation(0.0f))
                    .uv(83, 38).cuboid(-0.5198f, -0.9918f, -2.7492f, 4.0f, 9.0f, 5.0f, Dilation(0.25f)),
                ModelTransform.of(0.0f, 0.0f, 0.0f, -0.0869f, -0.0076f, -0.0869f)
            )

            val lower_left_arm = upper_left_arm.addChild(
                "lower_left_arm",
                ModelPartBuilder.create().uv(85, 65).cuboid(-1.5f, 0.0f, -4.0f, 3.0f, 10.0f, 4.0f, Dilation(0.0f)),
                ModelTransform.of(1.2302f, 7.0082f, 2.2508f, -0.7854f, 0.0f, 0.0f)
            )

            val right_arm =
                upper_body.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.pivot(-6.75f, -6.25f, 0.15f))

            val upper_right_arm = right_arm.addChild(
                "upper_right_arm",
                ModelPartBuilder.create().uv(23, 52)
                    .cuboid(-3.4802f, -0.9918f, -2.7492f, 4.0f, 8.0f, 5.0f, Dilation(0.0f))
                    .uv(23, 38).cuboid(-3.4802f, -0.9918f, -2.7492f, 4.0f, 9.0f, 5.0f, Dilation(0.25f)),
                ModelTransform.of(0.0f, 0.0f, 0.0f, -0.0869f, 0.0076f, 0.0869f)
            )

            val lower_right_arm = upper_right_arm.addChild(
                "lower_right_arm",
                ModelPartBuilder.create().uv(25, 65).cuboid(-1.5f, 0.0f, -4.0f, 3.0f, 10.0f, 4.0f, Dilation(0.0f)),
                ModelTransform.of(-1.2302f, 7.0082f, 2.2508f, -0.7854f, 0.0f, 0.0f)
            )

            val head = upper_body.addChild(
                "head",
                ModelPartBuilder.create().uv(46, 18).cuboid(-4.0f, -5.5f, -4.0f, 8.0f, 8.0f, 8.0f, Dilation(0.0f))
                    .uv(46, 0).cuboid(-4.0f, -5.5f, -4.0f, 8.0f, 10.0f, 8.0f, Dilation(0.25f)),
                ModelTransform.of(0.0f, -7.607f, -1.9999f, -0.3054f, 0.0f, 0.0f)
            )

            val left_leg = lower_body.addChild(
                "left_leg",
                ModelPartBuilder.create(),
                ModelTransform.of(3.5f, -0.5f, 0.0f, -0.0436f, 0.0f, 0.0f)
            )

            val upper_left_leg = left_leg.addChild(
                "upper_left_leg",
                ModelPartBuilder.create().uv(63, 93).cuboid(-2.25f, -1.75f, -3.25f, 5.0f, 9.0f, 5.0f, Dilation(0.0f))
                    .uv(63, 79).cuboid(-2.25f, -1.75f, -3.25f, 5.0f, 9.0f, 5.0f, Dilation(0.25f)),
                ModelTransform.of(-0.5f, 1.0f, 0.0f, -0.609f, -0.0617f, -0.0618f)
            )

            val lower_left_leg = upper_left_leg.addChild(
                "lower_left_leg",
                ModelPartBuilder.create().uv(66, 107).cuboid(-1.25f, -2.25f, -0.75f, 4.0f, 10.0f, 4.0f, Dilation(0.0f)),
                ModelTransform.of(-0.5f, 7.75f, -1.0f, 0.8727f, 0.0f, 0.0f)
            )

            val right_leg = lower_body.addChild(
                "right_leg",
                ModelPartBuilder.create(),
                ModelTransform.of(-3.5f, -0.5f, 0.0f, 0.1309f, 0.0f, 0.0f)
            )

            val upper_right_leg = right_leg.addChild(
                "upper_right_leg",
                ModelPartBuilder.create().uv(41, 93).cuboid(-2.75f, -1.75f, -3.25f, 5.0f, 9.0f, 5.0f, Dilation(0.0f))
                    .uv(41, 79).cuboid(-2.75f, -1.75f, -3.25f, 5.0f, 9.0f, 5.0f, Dilation(0.25f)),
                ModelTransform.of(0.5f, 1.0f, 0.0f, -0.7835f, 0.0617f, 0.0618f)
            )

            val lower_right_leg = upper_right_leg.addChild(
                "lower_right_leg",
                ModelPartBuilder.create().uv(43, 107).cuboid(-2.75f, -2.25f, -0.75f, 4.0f, 10.0f, 4.0f, Dilation(0.0f)),
                ModelTransform.of(0.5f, 7.75f, -1.0f, 0.8727f, 0.0f, 0.0f)
            )
            return TexturedModelData.of(modelData, 128, 128)
        }
    }

    override fun setAngles(
        renderState: SwampGolemRenderState,
    ) {
        super.setAngles(renderState)

        animate(renderState.slamAnimationState, SwampGolemAnimation.SLAM, renderState.age, 1F)
    }
}