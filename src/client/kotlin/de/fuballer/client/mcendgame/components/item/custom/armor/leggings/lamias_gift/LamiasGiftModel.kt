package de.fuballer.client.mcendgame.components.item.custom.armor.leggings.lamias_gift


import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.render.entity.state.BipedEntityRenderState
import net.minecraft.client.render.entity.state.PlayerEntityRenderState
import kotlin.math.sin


class LamiasGiftModel<S : BipedEntityRenderState>(
    root: ModelPart
) : BipedEntityModel<S>(root) {
    private val leggings = body.getChild("leggings")
    private val tailZero: ModelPart = leggings.getChild("nagaTailZero")
    private val tailOne: ModelPart = tailZero.getChild("nagaTailOne")
    private val tailTwo: ModelPart = tailOne.getChild("nagaTailTwo")
    private val tailThree: ModelPart = tailTwo.getChild("nagaTailThree")
    private val tailFour: ModelPart = tailThree.getChild("nagaTailFour")
    private val tailFive: ModelPart = tailFour.getChild("nagaTailFive")

    companion object {
        val MODEL_LAYER = EntityModelLayer(IdentifierUtil.default("lamias_gift"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root

            val head = modelPartData.addChild(EntityModelPartNames.HEAD)
            val hat = head.addChild(EntityModelPartNames.HAT)
            val body = modelPartData.addChild(EntityModelPartNames.BODY)
            val right_arm = modelPartData.addChild(EntityModelPartNames.RIGHT_ARM)
            val left_arm = modelPartData.addChild(EntityModelPartNames.LEFT_ARM)
            val right_leg = modelPartData.addChild(EntityModelPartNames.RIGHT_LEG)
            val left_leg = modelPartData.addChild(EntityModelPartNames.LEFT_LEG)

            val leggings = body.addChild("leggings", ModelPartBuilder.create(), ModelTransform.pivot(0.0f, 24.0f, 0.0f))

            val nagaTailZero =
                leggings.addChild("nagaTailZero", ModelPartBuilder.create().uv(2, 1).cuboid(-4.5f, 0.0f, -3.0f, 9.0f, 10.0f, 5.0f, Dilation(0.0f)), ModelTransform.pivot(0.0f, -15.75f, 0.5f))

            val nagaTailOne = nagaTailZero.addChild(
                "nagaTailOne",
                ModelPartBuilder.create().uv(3, 16).cuboid(-4.0f, -1.2145f, -2.6213f, 8.0f, 8.0f, 5.0f, Dilation(0.0f)),
                ModelTransform.of(0.0f, 9.0f, -0.25f, 0.7854f, 0.0f, 0.0f)
            )

            val nagaTailTwo = nagaTailOne.addChild(
                "nagaTailTwo",
                ModelPartBuilder.create().uv(6, 30).cuboid(-3.0f, -0.7951f, -2.2981f, 6.0f, 7.0f, 4.0f, Dilation(0.0f)),
                ModelTransform.of(0.0f, 5.7855f, -0.3713f, 0.7854f, 0.0f, 0.0f)
            )

            val nagaTailThree = nagaTailTwo.addChild(
                "nagaTailThree",
                ModelPartBuilder.create().uv(9, 42).cuboid(-2.0f, -0.5f, -1.5006f, 4.0f, 5.0f, 3.0f, Dilation(0.0f)),
                ModelTransform.pivot(0.0f, 6.205f, -0.7981f)
            )

            val nagaTailFour = nagaTailThree.addChild(
                "nagaTailFour",
                ModelPartBuilder.create().uv(11, 51).cuboid(-1.5f, -0.5f, -1.0056f, 3.0f, 5.0f, 2.0f, Dilation(0.0f)),
                ModelTransform.pivot(0.0f, 4.5f, -0.5f)
            )

            val nagaTailFive = nagaTailFour.addChild(
                "nagaTailFive",
                ModelPartBuilder.create().uv(12, 58).cuboid(-1.0f, -0.5f, -0.6806f, 2.0f, 4.0f, 2.0f, Dilation(-0.25f)),
                ModelTransform.pivot(0.0f, 4.5f, -0.5f)
            )
            return TexturedModelData.of(modelData, 32, 64)
        }
    }

    override fun setAngles(renderState: S) {
        resetNotCopiedTransforms()
        setTailAngles(renderState)
    }

    private fun resetNotCopiedTransforms() {
        tailZero.resetTransform()
        tailOne.resetTransform()
        tailTwo.resetTransform()
        tailThree.resetTransform()
        tailFour.resetTransform()
        tailFive.resetTransform()
    }

    private fun setTailAngles(renderState: S) {
        val animSpeed = 7F
        val moveSpeed = renderState.limbAmplitudeMultiplier // 0.0 - 1.0

        var tailOneYawFactor = 0.5F

        (renderState as? PlayerEntityRenderState)?.let { playerState ->
            if (playerState.isSwimming || playerState.isGliding) {
                tailOne.pitch -= 0.8F
                tailTwo.pitch -= 0.8F
                tailOneYawFactor = 0.15F
            } else if (playerState.isInSneakingPose) {
                tailTwo.pitch -= 0.45F
            }

            if (playerState.hasVehicle) {
                tailZero.yScale *= 0.5F
                tailOne.zScale *= 2F
                tailOne.pitch += 0.8F

                tailTwo.pitch += 0.4F
                tailThree.pitch += 0.1F
            }
        }

        val tailZeroRoll = sin(renderState.age / animSpeed) * moveSpeed * 0.1F
        tailZero.roll += tailZeroRoll

        tailOne.roll -= tailZeroRoll
        tailOne.yaw -= sin((renderState.age - 7) / animSpeed) * moveSpeed * tailOneYawFactor

        tailTwo.roll += sin((renderState.age - 14) / animSpeed) * moveSpeed * 0.5F
        tailThree.roll += sin((renderState.age - 21) / animSpeed) * moveSpeed * 0.5F
        tailFour.roll += sin((renderState.age - 28) / animSpeed) * moveSpeed * 0.5F
        tailFive.roll += sin((renderState.age - 35) / animSpeed) * moveSpeed * 0.5F
    }
}