package de.fuballer.client.mcendgame.components.item.custom.armor.leggings.druids_leggings

import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.render.entity.state.BipedEntityRenderState
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class DruidsLeggingsModel<S : BipedEntityRenderState>(
    root: ModelPart
) : BipedEntityModel<S>(root) {
    private val battleSkirtBack: ModelPart
    private val battleSkirtFront: ModelPart

    init {
        val leggingsWaist = this.body.getChild("leggings_waist")
        val battleSkirt = leggingsWaist.getChild("battle_skirt")
        battleSkirtBack = battleSkirt.getChild("battle_skirt_back")
        battleSkirtFront = battleSkirt.getChild("battle_skirt_front")
    }

    companion object {
        val MODEL_LAYER = EntityModelLayer(IdentifierUtil.default("druids_leggings"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root

            val head = modelPartData.addChild(EntityModelPartNames.HEAD)
            val hat = head.addChild(EntityModelPartNames.HAT)
            val left_arm = modelPartData.addChild(EntityModelPartNames.LEFT_ARM)
            val right_arm = modelPartData.addChild(EntityModelPartNames.RIGHT_ARM)

            val body = modelPartData.addChild(EntityModelPartNames.BODY)

            val leggings_waist = body.addChild(
                "leggings_waist",
                ModelPartBuilder.create().uv(5, 40).cuboid(-5.0f, 7.0f, -3.0f, 10.0f, 7.0f, 6.0f, Dilation(0.05f))
                    .uv(9, 33).cuboid(-4.0f, 9.0f, -2.0f, 8.0f, 3.0f, 4.0f, Dilation(0.5f))
                    .uv(9, 27).cuboid(-4.0f, 6.75f, -2.0f, 8.0f, 2.0f, 4.0f, Dilation(0.26f)),
                ModelTransform.pivot(0.0f, 0.0f, 0.0f)
            )

            val battle_skirt = leggings_waist.addChild(
                "battle_skirt",
                ModelPartBuilder.create(),
                ModelTransform.pivot(0.0f, 0.0f, 0.0f)
            )

            val battle_skirt_back = battle_skirt.addChild(
                "battle_skirt_back",
                ModelPartBuilder.create().uv(49, 0).cuboid(-4.0f, 0.0f, 0.0f, 8.0f, 14.0f, 0.0f, Dilation(0.0f)),
                ModelTransform.of(0.0f, 9.5f, 2.75f, 0.0873f, 0.0f, 0.0f)
            )

            val battle_skirt_left = battle_skirt.addChild(
                "battle_skirt_left",
                ModelPartBuilder.create().uv(43, 2).cuboid(-3.0f, -1.0218f, -0.0005f, 3.0f, 5.0f, 0.0f, Dilation(0.0f)),
                ModelTransform.of(4.75f, 10.5f, 2.25f, 0.0f, -1.5708f, -0.0436f)
            )

            val battle_skirt_right = battle_skirt.addChild(
                "battle_skirt_right",
                ModelPartBuilder.create().uv(65, 2).cuboid(0.0f, -1.0218f, -0.0005f, 3.0f, 5.0f, 0.0f, Dilation(0.0f)),
                ModelTransform.of(-4.75f, 10.5f, 2.25f, 0.0f, 1.5708f, 0.0436f)
            )

            val battle_skirt_front = battle_skirt.addChild(
                "battle_skirt_front",
                ModelPartBuilder.create().uv(53, 14).cuboid(-2.0f, 0.0f, 0.0f, 4.0f, 6.0f, 0.0f, Dilation(0.0f))
                    .uv(54, 20).cuboid(-1.5f, 6.0f, 0.0f, 3.0f, 6.0f, 0.0f, Dilation(0.0f)),
                ModelTransform.of(0.0f, 10.0f, -3.0f, -0.0436f, 0.0f, 0.0f)
            )

            val left_leg =
                modelPartData.addChild(
                    EntityModelPartNames.LEFT_LEG,
                    ModelPartBuilder.create(),
                    ModelTransform.pivot(2.0f, 12.0f, 0.0f)
                )

            val left_leggings = left_leg.addChild(
                "left_leggings",
                ModelPartBuilder.create().uv(24, 53).cuboid(-2.1f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, Dilation(0.5f)),
                ModelTransform.pivot(0.0f, 0.0f, 0.0f)
            )

            val right_leg =
                modelPartData.addChild(
                    EntityModelPartNames.RIGHT_LEG,
                    ModelPartBuilder.create(),
                    ModelTransform.pivot(-2.0f, 12.0f, 0.0f)
                )

            val right_leggings = right_leg.addChild(
                "right_leggings",
                ModelPartBuilder.create().uv(2, 53).cuboid(-1.9f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, Dilation(0.5f)),
                ModelTransform.pivot(0.0f, 0.0f, 0.0f)
            )
            return TexturedModelData.of(modelData, 128, 128)
        }
    }

    override fun setAngles(renderState: S) {
        resetNotCopiedTransforms()
        setBattleSkirtAngles(renderState)
    }

    private fun resetNotCopiedTransforms() {
        battleSkirtBack.resetTransform()
        battleSkirtFront.resetTransform()
    }

    private fun setBattleSkirtAngles(renderState: S) {
        val minPitchFront = max(0F, abs(min(leftLeg.pitch, rightLeg.pitch)))
        battleSkirtFront.pitch -= minPitchFront
        val minPitchBack = max(0F, max(leftLeg.pitch, rightLeg.pitch))
        battleSkirtBack.pitch += minPitchBack
    }
}