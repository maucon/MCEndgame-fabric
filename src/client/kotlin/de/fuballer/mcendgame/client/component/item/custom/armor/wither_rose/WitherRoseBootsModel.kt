package de.fuballer.mcendgame.client.component.item.custom.armor.wither_rose

import de.fuballer.mcendgame.client.component.item.custom.ModelPartDataExtension.createEmptyChild
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.render.entity.state.BipedEntityRenderState

class WitherRoseBootsModel<S : BipedEntityRenderState>(
    root: ModelPart,
) : BipedEntityModel<S>(root) {
    companion object {
        val MODEL_LAYER = EntityModelLayer(IdentifierUtil.default("wither_rose_boots"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root

            val head = modelPartData.createEmptyChild(EntityModelPartNames.HEAD)
            val hat = head.createEmptyChild(EntityModelPartNames.HAT)
            val body = modelPartData.createEmptyChild(EntityModelPartNames.BODY)
            val left_arm = modelPartData.createEmptyChild(EntityModelPartNames.LEFT_ARM)
            val right_arm = modelPartData.createEmptyChild(EntityModelPartNames.RIGHT_ARM)

            val left_leg = modelPartData.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create(), ModelTransform.origin(2.0f, 12.0f, 0.0f))

            val left_boot = left_leg.addChild(
                "left_boot",
                ModelPartBuilder.create().uv(22, 116).cuboid(-2.5f, 5.5f, -2.6f, 5.0f, 7.0f, 5.0f, Dilation(0.25f)),
                ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )

            val right_leg = modelPartData.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create(), ModelTransform.origin(-2.0f, 12.0f, 0.0f))

            val right_boot = right_leg.addChild(
                "right_boot",
                ModelPartBuilder.create().uv(0, 116).cuboid(-2.5f, 5.5f, -2.5f, 5.0f, 7.0f, 5.0f, Dilation(0.25f)),
                ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )
            return TexturedModelData.of(modelData, 128, 128)
        }
    }

    override fun setAngles(bipedEntityRenderState: S) {}
}