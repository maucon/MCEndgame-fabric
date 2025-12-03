package de.fuballer.mcendgame.client.component.item.custom.armor.model.gilded_tempest

import de.fuballer.mcendgame.client.component.item.custom.ModelPartDataExtension.createEmptyChild
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.render.entity.state.BipedEntityRenderState

class GildedTempestModel<S : BipedEntityRenderState>(
    root: ModelPart
) : BipedEntityModel<S>(root) {
    companion object {
        val MODEL_LAYER = EntityModelLayer(IdentifierUtil.default("gilded_tempest"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root

            val head = modelPartData.createEmptyChild(EntityModelPartNames.HEAD)
            val hat = head.createEmptyChild(EntityModelPartNames.HAT)
            val left_arm = modelPartData.createEmptyChild(EntityModelPartNames.LEFT_ARM)
            val right_arm = modelPartData.createEmptyChild(EntityModelPartNames.RIGHT_ARM)

            val body = modelPartData.createEmptyChild(EntityModelPartNames.BODY)

            val leggings_waist = body.addChild(
                "leggings_waist", ModelPartBuilder.create().uv(18, 0).cuboid(-4.5F, 6.5F, -2.5F, 9.0F, 5.0F, 5.0F, Dilation(0.0F))
                    .uv(17, 39).cuboid(-5.0F, 9.0F, -2.5F, 10.0F, 3.0F, 5.0F, Dilation(0.1F))
                    .uv(15, 10).cuboid(-5.5F, 7.5F, -3.0F, 11.0F, 5.0F, 6.0F, Dilation(-0.05F)), ModelTransform.origin(0.0F, 0.0F, 0.0F)
            )

            val left_leg = modelPartData.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create(), ModelTransform.origin(2.0F, 12.0F, 0.0F))

            val left_leggings = left_leg.addChild(
                "left_leggings", ModelPartBuilder.create().uv(43, 32).cuboid(-2.1F, -0.5F, -2.5F, 5.0F, 6.0F, 5.0F, Dilation(0.0F))
                    .uv(45, 52).cuboid(-2.0F, 2.5F, -2.0F, 4.0F, 8.0F, 4.0F, Dilation(0.05F))
                    .uv(44, 43).cuboid(-1.5F, 5.5F, -2.5F, 4.0F, 4.0F, 5.0F, Dilation(0.0F))
                    .uv(42, 22).cuboid(-1.5F, 0.5F, -3.0F, 5.0F, 4.0F, 6.0F, Dilation(-0.05F)), ModelTransform.origin(0.0F, 0.0F, 0.0F)
            )

            val right_leg = modelPartData.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create(), ModelTransform.origin(-2.0F, 12.0F, 0.0F))

            val right_leggings = right_leg.addChild(
                "right_leggings", ModelPartBuilder.create().uv(1, 32).cuboid(-2.9F, -0.5F, -2.5F, 5.0F, 6.0F, 5.0F, Dilation(0.0F))
                    .uv(3, 52).cuboid(-2.0F, 2.5F, -2.0F, 4.0F, 8.0F, 4.0F, Dilation(0.05F))
                    .uv(2, 43).cuboid(-2.5F, 5.5F, -2.5F, 4.0F, 4.0F, 5.0F, Dilation(0.0F))
                    .uv(0, 22).cuboid(-3.5F, 0.5F, -3.0F, 5.0F, 4.0F, 6.0F, Dilation(-0.05F)), ModelTransform.origin(0.0F, 0.0F, 0.0F)
            )
            return TexturedModelData.of(modelData, 64, 64)
        }
    }

    override fun setAngles(renderState: S) {}
}