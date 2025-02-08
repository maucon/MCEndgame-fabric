package de.fuballer.mcendgame.components.entity.custom.swamp_golem

import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer


class SwampGolemEntityModel(
    modelPart: ModelPart,
) : BipedEntityModel<SwampGolemRenderState>(modelPart) {
    private var lowerBody: ModelPart = body.getChild("lower_body")
    private var upperBody: ModelPart = lowerBody.getChild("upper_body")
    private var upperLeftArm: ModelPart = leftArm.getChild("upper_left_arm")
    private var lowerLeftArm: ModelPart = upperLeftArm.getChild("lower_left_arm")
    private var upperRightArm: ModelPart = rightArm.getChild("upper_right_arm")
    private var lowerRightArm: ModelPart = upperRightArm.getChild("lower_right_arm")
    private var upperLeftLeg: ModelPart = leftLeg.getChild("upper_left_leg")
    private var lowerLeftLeg: ModelPart = upperLeftLeg.getChild("lower_left_leg")
    private var upperRightLeg: ModelPart = rightLeg.getChild("upper_right_leg")
    private var lowerRightLeg: ModelPart = upperRightLeg.getChild("lower_right_leg")

    companion object {
        val SWAMP_GOLEM = EntityModelLayer(IdentifierUtil.default("swamp_golem"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root
            val head = modelPartData.addChild(
                "head",
                ModelPartBuilder.create().uv(46, 18).cuboid(-4.0f, -5.5f, -4.0f, 8.0f, 8.0f, 8.0f, Dilation(0.0f))
                    .uv(46, 0).cuboid(-4.0f, -5.5f, -4.0f, 8.0f, 10.0f, 8.0f, Dilation(0.25f)),
                ModelTransform.of(0.0f, -1.857f, -1.9999f, 0.0873f, 0.0f, 0.0f)
            )

            val hat = head.addChild(
                "hat",
                ModelPartBuilder.create(),
                ModelTransform.of(0.0f, 0.0f, 0.0f, 0.0873f, 0.0f, 0.0f)
            )

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

            val left_arm = modelPartData.addChild(
                "left_arm",
                ModelPartBuilder.create(),
                ModelTransform.of(7.5f, -0.4933f, 0.6947f, 0.3491f, 0.0f, 0.0f)
            )

            val upper_left_arm = left_arm.addChild(
                "upper_left_arm",
                ModelPartBuilder.create().uv(83, 52).cuboid(0.0f, -2.25f, -2.5f, 4.0f, 8.0f, 5.0f, Dilation(0.0f))
                    .uv(83, 38).cuboid(0.0f, -2.25f, -2.5f, 4.0f, 9.0f, 5.0f, Dilation(0.25f)),
                ModelTransform.of(-1.25f, 0.297f, -0.0656f, -0.0869f, -0.0076f, -0.0869f)
            )

            val lower_left_arm = upper_left_arm.addChild(
                "lower_left_arm",
                ModelPartBuilder.create().uv(85, 65).cuboid(0.0f, 0.0f, -4.0f, 3.0f, 10.0f, 4.0f, Dilation(0.0f)),
                ModelTransform.of(0.25f, 5.75f, 2.5f, -0.7854f, 0.0f, 0.0f)
            )

            val right_arm = modelPartData.addChild(
                "right_arm",
                ModelPartBuilder.create(),
                ModelTransform.of(-7.5f, -0.4933f, 0.6947f, 0.3491f, 0.0f, 0.0f)
            )

            val upper_right_arm = right_arm.addChild(
                "upper_right_arm",
                ModelPartBuilder.create().uv(23, 52).cuboid(-4.0f, -2.25f, -2.5f, 4.0f, 8.0f, 5.0f, Dilation(0.0f))
                    .uv(23, 38).cuboid(-4.0f, -2.25f, -2.5f, 4.0f, 9.0f, 5.0f, Dilation(0.25f)),
                ModelTransform.of(1.25f, 0.297f, -0.0656f, -0.0869f, 0.0076f, 0.0869f)
            )

            val lower_right_arm = upper_right_arm.addChild(
                "lower_right_arm",
                ModelPartBuilder.create().uv(25, 65).cuboid(-3.0f, 0.0f, -4.0f, 3.0f, 10.0f, 4.0f, Dilation(0.0f)),
                ModelTransform.of(-0.25f, 5.75f, 2.5f, -0.7854f, 0.0f, 0.0f)
            )

            val left_leg =
                modelPartData.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.pivot(3.5f, 11.0f, 2.75f))

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

            val right_leg = modelPartData.addChild(
                "right_leg",
                ModelPartBuilder.create(),
                ModelTransform.of(-3.5f, 11.0f, 2.75f, 0.1745f, 0.0f, 0.0f)
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
        animatedSlam(renderState)
    }

    private fun animatedSlam(
        renderState: SwampGolemRenderState,
    ) {
        if (!renderState.isSlamming) return
        val slamProgress = renderState.slamProgress

        if (slamProgress <= 0.5) {
            animateSlamBuildUp(slamProgress * 2)
        } else if (slamProgress <= 0.75) {
            animateSlamStrike((slamProgress - 0.5) * 4)
        } else {
            animateSlamReset((slamProgress - 0.75) * 4)
        }
    }

    private fun animateSlamBuildUp(
        progress: Double
    ) {
        upperLeftArm.pitch = upperLeftArm.defaultTransform.pitch
        upperLeftArm.pitch -= (progress * 2.2).toFloat()
    }

    private fun animateSlamStrike(
        progress: Double
    ) {
        upperLeftArm.pitch = upperLeftArm.defaultTransform.pitch
        upperLeftArm.pitch -= ((1 - progress) * 2.2).toFloat()
    }

    private fun animateSlamReset(
        progress: Double
    ) {
    }
}