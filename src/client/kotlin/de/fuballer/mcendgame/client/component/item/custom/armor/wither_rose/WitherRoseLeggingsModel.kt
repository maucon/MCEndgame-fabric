package de.fuballer.mcendgame.client.component.item.custom.armor.wither_rose

import de.fuballer.mcendgame.client.component.item.custom.ModelPartDataExtension.createEmptyChild
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.render.entity.state.BipedEntityRenderState

class WitherRoseLeggingsModel<S : BipedEntityRenderState>(
    root: ModelPart,
) : BipedEntityModel<S>(root) {
    companion object {
        val MODEL_LAYER = EntityModelLayer(IdentifierUtil.default("wither_rose_leggings"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root

            val head = modelPartData.createEmptyChild(EntityModelPartNames.HEAD)
            val hat = head.createEmptyChild(EntityModelPartNames.HAT)
            val left_arm = modelPartData.createEmptyChild(EntityModelPartNames.LEFT_ARM)
            val right_arm = modelPartData.createEmptyChild(EntityModelPartNames.RIGHT_ARM)

            val body = modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create(), ModelTransform.origin(0.0f, 0.0f, 0.0f))

            val leggings_waist =
                body.addChild("leggings_waist", ModelPartBuilder.create().uv(9, 92).cuboid(-4.0f, 8.0f, -2.0f, 8.0f, 4.0f, 4.0f, Dilation(0.05f)), ModelTransform.origin(0.0f, 0.0f, 0.0f))

            val skirt = leggings_waist.addChild("skirt", ModelPartBuilder.create(), ModelTransform.origin(0.0f, 0.25f, 0.0f))

            val skirtBackRight = skirt.addChild(
                "skirtBackRight",
                ModelPartBuilder.create().uv(0, 79).cuboid(0.0f, 0.0f, 0.0f, 4.0f, 12.0f, 0.0f, Dilation(0.0f)),
                ModelTransform.of(-4.25f, 8.0f, 3.1f, 0.0872f, -0.0038f, 0.0435f)
            )

            val skirtBackLeft = skirt.addChild(
                "skirtBackLeft",
                ModelPartBuilder.create().uv(43, 79).cuboid(-4.0f, 0.0f, 0.0f, 4.0f, 12.0f, 0.0f, Dilation(0.0f)),
                ModelTransform.of(4.25f, 8.0f, 3.1f, 0.0872f, 0.0038f, -0.0435f)
            )

            val skirtLeft = skirt.addChild(
                "skirtLeft",
                ModelPartBuilder.create().uv(34, 75).cuboid(0.0f, 0.0f, 0.0f, 0.0f, 12.0f, 4.0f, Dilation(0.0f)),
                ModelTransform.of(5.1f, 8.0f, -2.0f, 0.0436f, 0.0f, -0.0436f)
            )

            val skirtRight = skirt.addChild(
                "skirtRight",
                ModelPartBuilder.create().uv(9, 75).cuboid(0.0f, 0.0f, 0.0f, 0.0f, 12.0f, 4.0f, Dilation(0.0f)),
                ModelTransform.of(-5.1f, 8.0f, -2.0f, 0.0436f, 0.0f, 0.0436f)
            )

            val skirtFrontRight = skirt.addChild(
                "skirtFrontRight",
                ModelPartBuilder.create().uv(18, 79).cuboid(0.0f, 0.0f, 0.0f, 2.0f, 12.0f, 0.0f, Dilation(0.0f)),
                ModelTransform.of(-4.25f, 8.0f, -3.1f, -0.0436f, 0.0019f, 0.0436f)
            )

            val skirtFrontLeft = skirt.addChild(
                "skirtFrontLeft",
                ModelPartBuilder.create().uv(23, 86).cuboid(-5.0f, 0.0f, 0.0f, 5.0f, 5.0f, 0.0f, Dilation(0.0f)),
                ModelTransform.of(4.25f, 8.0f, -3.1f, -0.0436f, -0.0019f, -0.0436f)
            )

            val beltLeft = skirt.addChild(
                "beltLeft",
                ModelPartBuilder.create().uv(25, 66).cuboid(-6.75f, -0.25f, -3.45f, 7.0f, 3.0f, 7.0f, Dilation(-0.25f)),
                ModelTransform.of(5.25f, 7.25f, 0.0f, 0.0f, 0.0f, -0.0873f)
            )

            val beltRight = skirt.addChild(
                "beltRight",
                ModelPartBuilder.create().uv(0, 68).cuboid(-0.25f, -0.25f, -3.5f, 5.0f, 3.0f, 7.0f, Dilation(-0.25f)),
                ModelTransform.of(-5.25f, 7.25f, 0.0f, 0.0f, 0.0f, 0.1309f)
            )

            val beltFront = skirt.addChild(
                "beltFront",
                ModelPartBuilder.create().uv(24, 76).cuboid(-1.5f, -0.5f, -0.5f, 3.0f, 8.0f, 1.0f, Dilation(-0.5f)),
                ModelTransform.of(-1.0f, 7.6f, -3.35f, -0.0436f, 0.0019f, 0.0436f)
            )

            val left_leg = modelPartData.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create(), ModelTransform.origin(2.0f, 12.0f, 0.0f))

            val left_leggings = left_leg.addChild(
                "left_leggings",
                ModelPartBuilder.create().uv(22, 101).cuboid(-2.0f, 0.0f, -2.0f, 4.0f, 10.0f, 4.0f, Dilation(0.05f)),
                ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )

            val right_leg = modelPartData.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create(), ModelTransform.origin(-2.0f, 12.0f, 0.0f))

            val right_leggings = right_leg.addChild(
                "right_leggings",
                ModelPartBuilder.create().uv(4, 101).cuboid(-2.0f, 0.0f, -2.0f, 4.0f, 10.0f, 4.0f, Dilation(0.05f)),
                ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )
            return TexturedModelData.of(modelData, 128, 128)
        }
    }

    override fun setAngles(bipedEntityRenderState: S) {}
}