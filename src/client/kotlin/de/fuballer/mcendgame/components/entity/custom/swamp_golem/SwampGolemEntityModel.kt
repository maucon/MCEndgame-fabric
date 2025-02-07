package de.fuballer.mcendgame.components.entity.custom.swamp_golem

import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.state.BipedEntityRenderState
import net.minecraft.util.Arm
import net.minecraft.util.math.MathHelper


class SwampGolemEntityModel<S : BipedEntityRenderState>(
    modelPart: ModelPart,
) : BipedEntityModel<S>(modelPart) {
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
        renderState: S,
    ) {
        super.setAngles(renderState)
    }

    override fun animateArms(
        state: S,
        animationProgress: Float
    ) {
        val swing = state.handSwingProgress
        if (!(swing <= 0.0f)) {
            val arm = state.preferredArm
            val armModelPart = this.getArm(arm)
            body.yaw = MathHelper.sin(MathHelper.sqrt(swing) * (Math.PI * 2).toFloat()) * 0.2f
            if (arm == Arm.LEFT) {
                body.yaw *= -1.0f
            }

            val h = state.ageScale
            rightArm.pivotZ = MathHelper.sin(body.yaw) * 5.0f * h
            rightArm.pivotX = -MathHelper.cos(body.yaw) * 5.0f * h
            leftArm.pivotZ = -MathHelper.sin(body.yaw) * 5.0f * h
            leftArm.pivotX = MathHelper.cos(body.yaw) * 5.0f * h
            rightArm.yaw += body.yaw
            leftArm.yaw += body.yaw
            leftArm.pitch += body.yaw
            var g = 1.0f - swing
            g *= g
            g *= g
            g = 1.0f - g
            val i = MathHelper.sin(g * Math.PI.toFloat())
            val j = MathHelper.sin(swing * Math.PI.toFloat()) * -(head.pitch - 0.7f) * 0.75f
            armModelPart.pitch -= i * 1.2f + j
            armModelPart.yaw += body.yaw * 1.3f
            armModelPart.roll += MathHelper.sin(swing * Math.PI.toFloat()) * -0.1f
        }
    }
}