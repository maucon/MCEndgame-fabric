package de.fuballer.mcendgame.client.component.item.custom.armor.suede

import de.fuballer.mcendgame.client.component.item.custom.ModelPartDataExtension.createEmptyChild
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.render.entity.state.BipedEntityRenderState

class SuedeChestplateModel<S : BipedEntityRenderState>(
    root: ModelPart
) : BipedEntityModel<S>(root) {
    companion object {
        val MODEL_LAYER = EntityModelLayer(IdentifierUtil.default("suede_chestplate"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root

            val head = modelPartData.createEmptyChild(EntityModelPartNames.HEAD)
            val hat = head.createEmptyChild(EntityModelPartNames.HAT)
            val left_leg = modelPartData.createEmptyChild(EntityModelPartNames.LEFT_LEG)
            val right_leg = modelPartData.createEmptyChild(EntityModelPartNames.RIGHT_LEG)

            val body = modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create(), ModelTransform.origin(0.0f, 0.0f, 0.0f))

            val chestplate = body.addChild(
                "chestplate", ModelPartBuilder.create().uv(21, 20).cuboid(-4.0f, 7.0f, -2.0f, 8.0f, 4.0f, 4.0f, Dilation(0.25f))
                    .uv(19, 9).cuboid(-4.5f, 2.0f, -2.5f, 9.0f, 5.0f, 5.0f, Dilation(0.0f))
                    .uv(21, 0).cuboid(-3.5f, -0.25f, -2.25f, 7.0f, 3.0f, 5.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )

            val left_arm = modelPartData.addChild(EntityModelPartNames.LEFT_ARM, ModelPartBuilder.create(), ModelTransform.origin(5.0f, 2.0f, 0.0f))

            val chestplate_left_arm = left_arm.addChild(
                "chestplate_left_arm", ModelPartBuilder.create().uv(48, 8).cuboid(-1.0f, 0.0f, -2.0f, 4.0f, 7.0f, 4.0f, Dilation(0.1f))
                    .uv(46, 0).cuboid(-1.5f, 0.5f, -2.5f, 5.0f, 3.0f, 5.0f, Dilation(-0.25f)), ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )

            val right_arm = modelPartData.addChild(EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create(), ModelTransform.origin(-5.0f, 2.0f, 0.0f))

            val chestplate_right_arm = right_arm.addChild(
                "chestplate_right_arm", ModelPartBuilder.create().uv(2, 8).cuboid(-3.0f, 0.0f, -2.0f, 4.0f, 7.0f, 4.0f, Dilation(0.1f))
                    .uv(0, 0).cuboid(-3.5f, 0.5f, -2.5f, 5.0f, 3.0f, 5.0f, Dilation(-0.25f)), ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )
            return TexturedModelData.of(modelData, 128, 128)
        }
    }

    override fun setAngles(bipedEntityRenderState: S) {}
}