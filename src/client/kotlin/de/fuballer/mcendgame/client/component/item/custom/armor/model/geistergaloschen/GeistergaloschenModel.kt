package de.fuballer.mcendgame.client.component.item.custom.armor.model.geistergaloschen

import de.fuballer.mcendgame.client.component.item.custom.ModelPartDataExtension.createEmptyChild
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.render.entity.state.BipedEntityRenderState

class GeistergaloschenModel<S : BipedEntityRenderState>(
    root: ModelPart
) : BipedEntityModel<S>(root) {
    companion object {
        val MODEL_LAYER = EntityModelLayer(IdentifierUtil.default("geistergaloschen"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root

            val head = modelPartData.createEmptyChild(EntityModelPartNames.HEAD)
            val hat = head.createEmptyChild(EntityModelPartNames.HAT)
            val left_arm = modelPartData.createEmptyChild(EntityModelPartNames.LEFT_ARM)
            val right_arm = modelPartData.createEmptyChild(EntityModelPartNames.RIGHT_ARM)
            val body = modelPartData.createEmptyChild(EntityModelPartNames.BODY)

            val left_leg = modelPartData.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create(), ModelTransform.origin(2.0f, 12.0f, 0.0f))

            val left_boot = left_leg.addChild(
                "left_boot", ModelPartBuilder.create().uv(21, 8).mirrored().cuboid(-2.6F, 6.5F, -2.5F, 5.0F, 6.0F, 5.0F, Dilation(0.1F)).mirrored(false)
                    .uv(21, 0).mirrored().cuboid(-2.6F, 6.5F, -2.5F, 5.0F, 2.0F, 5.0F, Dilation(0.3F)).mirrored(false), ModelTransform.origin(0.0F, 0.0F, 0.0F)
            )

            val right_leg = modelPartData.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create(), ModelTransform.origin(-2.0f, 12.0f, 0.0f))

            val right_boot = right_leg.addChild(
                "right_boot", ModelPartBuilder.create().uv(0, 8).cuboid(-2.4F, 6.5F, -2.5F, 5.0F, 6.0F, 5.0F, Dilation(0.1F))
                    .uv(0, 0).cuboid(-2.4F, 6.5F, -2.5F, 5.0F, 2.0F, 5.0F, Dilation(0.3F)), ModelTransform.origin(0.0F, 0.0F, 0.0F)
            )

            return TexturedModelData.of(modelData, 64, 32)
        }
    }

    override fun setAngles(renderState: S) {}
}