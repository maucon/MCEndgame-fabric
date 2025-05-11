package de.fuballer.mcendgame.client.component.item.custom.armor.wither_rose

import de.fuballer.mcendgame.client.component.item.custom.ModelPartDataExtension.createEmptyChild
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.render.entity.state.BipedEntityRenderState

class WitherRoseChestplateModel<S : BipedEntityRenderState>(
    root: ModelPart,
) : BipedEntityModel<S>(root) {
    companion object {
        val MODEL_LAYER = EntityModelLayer(IdentifierUtil.default("wither_rose_chestplate"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root

            val head = modelPartData.createEmptyChild(EntityModelPartNames.HEAD)
            val hat = head.createEmptyChild(EntityModelPartNames.HAT)
            val left_leg = modelPartData.createEmptyChild(EntityModelPartNames.LEFT_LEG)
            val right_leg = modelPartData.createEmptyChild(EntityModelPartNames.RIGHT_LEG)

            val body = modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create(), ModelTransform.origin(0.0f, 0.0f, 0.0f))

            val chestplate = body.addChild(
                "chestplate", ModelPartBuilder.create().uv(0, 52).cuboid(-4.5f, 5.5f, -2.5f, 9.0f, 5.0f, 5.0f, Dilation(0.3f))
                    .uv(0, 39).cuboid(-5.0f, -0.5f, -3.25f, 10.0f, 6.0f, 6.0f, Dilation(0.3f)), ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )

            val left_arm = modelPartData.addChild(EntityModelPartNames.LEFT_ARM, ModelPartBuilder.create(), ModelTransform.origin(5.0f, 2.0f, 0.0f))

            val chestplate_left_arm = left_arm.addChild(
                "chestplate_left_arm", ModelPartBuilder.create().uv(33, 17).cuboid(-1.0f, -2.0f, -2.0f, 4.0f, 5.0f, 4.0f, Dilation(0.5f))
                    .uv(33, 27).cuboid(-1.0f, 3.25f, -2.0f, 4.0f, 7.0f, 4.0f, Dilation(0.1f)), ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )

            val vambraceLeft = chestplate_left_arm.addChild(
                "vambraceLeft",
                ModelPartBuilder.create().uv(50, 28).cuboid(1.0f, 4.25f, -3.0f, 2.0f, 5.0f, 5.0f, Dilation(0.0f)),
                ModelTransform.origin(0.5f, 0.0f, 0.5f)
            )

            val pauldronLeft = chestplate_left_arm.addChild(
                "pauldronLeft",
                ModelPartBuilder.create().uv(33, 6).cuboid(-3.5f, 0.5f, -2.5f, 3.0f, 5.0f, 5.0f, Dilation(0.5f)),
                ModelTransform.of(3.75f, -3.5f, 0.0f, 0.0f, 0.0f, -0.0873f)
            )

            val pauldronLeftTop = pauldronLeft.addChild(
                "pauldronLeftTop",
                ModelPartBuilder.create().uv(33, 0).cuboid(-4.0f, 0.0f, -2.0f, 4.0f, 1.0f, 4.0f, Dilation(0.0f)),
                ModelTransform.of(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.1745f)
            )

            val right_arm = modelPartData.addChild(EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create(), ModelTransform.origin(-5.0f, 2.0f, 0.0f))

            val chestplate_right_arm = right_arm.addChild(
                "chestplate_right_arm", ModelPartBuilder.create().uv(15, 27).cuboid(-3.0f, 3.25f, -2.0f, 4.0f, 7.0f, 4.0f, Dilation(0.1f))
                    .uv(15, 17).cuboid(-3.0f, -2.0f, -2.0f, 4.0f, 5.0f, 4.0f, Dilation(0.5f)), ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )

            val vambraceRight = chestplate_right_arm.addChild(
                "vambraceRight",
                ModelPartBuilder.create().uv(0, 28).cuboid(-3.0f, 4.25f, -3.0f, 2.0f, 5.0f, 5.0f, Dilation(0.0f)),
                ModelTransform.origin(-0.5f, 0.0f, 0.5f)
            )

            val pauldronRight = chestplate_right_arm.addChild(
                "pauldronRight",
                ModelPartBuilder.create().uv(15, 6).cuboid(0.5f, 0.5f, -2.5f, 3.0f, 5.0f, 5.0f, Dilation(0.5f)),
                ModelTransform.of(-3.75f, -3.5f, 0.0f, 0.0f, 0.0f, 0.0873f)
            )

            val pauldronRightTop = pauldronRight.addChild(
                "pauldronRightTop",
                ModelPartBuilder.create().uv(15, 0).cuboid(0.0f, 0.0f, -2.0f, 4.0f, 1.0f, 4.0f, Dilation(0.0f)),
                ModelTransform.of(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.1745f)
            )
            return TexturedModelData.of(modelData, 128, 128)
        }
    }

    override fun setAngles(bipedEntityRenderState: S) {}
}