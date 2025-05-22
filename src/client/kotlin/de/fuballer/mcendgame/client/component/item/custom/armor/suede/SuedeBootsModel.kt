package de.fuballer.mcendgame.client.component.item.custom.armor.suede

import de.fuballer.mcendgame.client.component.item.custom.ModelPartDataExtension.createEmptyChild
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.render.entity.state.BipedEntityRenderState

class SuedeBootsModel<S : BipedEntityRenderState>(
    root: ModelPart
) : BipedEntityModel<S>(root) {
    companion object {
        val MODEL_LAYER = EntityModelLayer(IdentifierUtil.default("suede_boots"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root

            val head = modelPartData.createEmptyChild(EntityModelPartNames.HEAD)
            val hat = head.createEmptyChild(EntityModelPartNames.HAT)
            val body = modelPartData.createEmptyChild(EntityModelPartNames.BODY)
            val left_arm = modelPartData.createEmptyChild(EntityModelPartNames.LEFT_ARM)
            val right_arm = modelPartData.createEmptyChild(EntityModelPartNames.RIGHT_ARM)

            val left_leg = modelPartData.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create(), ModelTransform.origin(2.0f, 12.0f, 0.0f))

            val left_boot =
                left_leg.addChild("left_boot", ModelPartBuilder.create().uv(34, 29).cuboid(-2.5f, 3.5f, -2.5f, 5.0f, 9.0f, 5.0f, Dilation(-0.25f)), ModelTransform.origin(0.0f, 0.0f, 0.0f))

            val right_leg = modelPartData.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create(), ModelTransform.origin(-2.0f, 12.0f, 0.0f))

            val right_boot =
                right_leg.addChild("right_boot", ModelPartBuilder.create().uv(12, 29).cuboid(1.5f, 3.5f, -2.5f, 5.0f, 9.0f, 5.0f, Dilation(-0.25f)), ModelTransform.origin(-4.0f, 0.0f, 0.0f))
            return TexturedModelData.of(modelData, 128, 128)
        }
    }

    override fun setAngles(bipedEntityRenderState: S) {}
}