package de.fuballer.mcendgame.client.component.item.custom.armor.model.stoneward

import de.fuballer.mcendgame.client.component.item.custom.ModelPartDataExtension.createEmptyChild
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.render.entity.state.BipedEntityRenderState

class StonewardModel<S : BipedEntityRenderState>(
    root: ModelPart
) : BipedEntityModel<S>(root) {
    companion object {
        val MODEL_LAYER = EntityModelLayer(IdentifierUtil.default("stoneward"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root

            val head = modelPartData.createEmptyChild(EntityModelPartNames.HEAD)
            val hat = head.createEmptyChild(EntityModelPartNames.HAT)
            val left_arm = modelPartData.createEmptyChild(EntityModelPartNames.LEFT_ARM)
            val right_arm = modelPartData.createEmptyChild(EntityModelPartNames.RIGHT_ARM)

            val body = modelPartData.createEmptyChild(EntityModelPartNames.BODY)

            val leggings_waist =
                body.addChild("leggings_waist", ModelPartBuilder.create().uv(13, 0).cuboid(-4.0F, 8.0F, -2.0F, 8.0F, 4.0F, 4.0F, Dilation(0.55F)), ModelTransform.origin(0.0F, 0.0F, 0.0F))

            val left_leg = modelPartData.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create(), ModelTransform.origin(2.0f, 12.0f, 0.0f))

            val left_leggings = left_leg.addChild(
                "left_leggings", ModelPartBuilder.create().uv(30, 47).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, Dilation(0.5F))
                    .uv(28, 28).cuboid(-2.6F, -0.5F, -2.5F, 5.0F, 13.0F, 5.0F, Dilation(0.23F))
                    .uv(26, 9).cuboid(-2.9F, -5.5F, -3.0F, 6.0F, 12.0F, 6.0F, Dilation(0.1F)), ModelTransform.origin(0.0F, 0.0F, 0.0F)
            )

            val right_leg = modelPartData.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create(), ModelTransform.origin(-2.0f, 12.0f, 0.0f))

            val right_leggings = right_leg.addChild(
                "right_leggings", ModelPartBuilder.create().uv(4, 47).cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, Dilation(0.5F))
                    .uv(2, 28).cuboid(-2.4F, -0.5F, -2.5F, 5.0F, 13.0F, 5.0F, Dilation(0.23F))
                    .uv(0, 9).cuboid(-3.1F, -5.5F, -3.0F, 6.0F, 12.0F, 6.0F, Dilation(0.1F)), ModelTransform.origin(0.0F, 0.0F, 0.0F)
            )

            return TexturedModelData.of(modelData, 64, 64)
        }
    }

    override fun setAngles(renderState: S) {}
}