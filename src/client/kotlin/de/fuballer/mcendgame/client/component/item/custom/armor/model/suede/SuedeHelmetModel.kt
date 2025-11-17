package de.fuballer.mcendgame.client.component.item.custom.armor.model.suede

import de.fuballer.mcendgame.client.component.item.custom.ModelPartDataExtension.createEmptyChild
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.render.entity.state.BipedEntityRenderState

class SuedeHelmetModel<S : BipedEntityRenderState>(
    root: ModelPart,
) : BipedEntityModel<S>(root) {
    companion object {
        val MODEL_LAYER = EntityModelLayer(IdentifierUtil.default("suede_helmet"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root

            val body = modelPartData.createEmptyChild(EntityModelPartNames.BODY)
            val left_leg = modelPartData.createEmptyChild(EntityModelPartNames.LEFT_LEG)
            val right_leg = modelPartData.createEmptyChild(EntityModelPartNames.RIGHT_LEG)
            val left_arm = modelPartData.createEmptyChild(EntityModelPartNames.LEFT_ARM)
            val right_arm = modelPartData.createEmptyChild(EntityModelPartNames.RIGHT_ARM)

            val head = modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create(), ModelTransform.origin(0.0f, 0.0f, 0.0f))
            val hat = head.createEmptyChild(EntityModelPartNames.HAT)

            val helmet = head.addChild(
                "helmet", ModelPartBuilder.create().uv(15, 67).cuboid(-4.5f, -8.5f, -4.5f, 9.0f, 5.0f, 9.0f, Dilation(0.0f))
                    .uv(15, 71).cuboid(-1.75f, -7.0f, 4.5f, 3.0f, 3.0f, 1.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )

            val scarf_band_long = helmet.addChild(
                "scarf_band_long",
                ModelPartBuilder.create().uv(43, 69).cuboid(-1.0f, 0.0f, 0.0f, 2.0f, 6.0f, 0.0f, Dilation(0.0f)),
                ModelTransform.of(-0.4f, -4.1f, 5.1f, 0.0f, -0.1309f, 0.0436f)
            )

            val scarf_band_short = helmet.addChild(
                "scarf_band_short",
                ModelPartBuilder.create().uv(48, 71).cuboid(-0.5f, 0.0f, 0.0f, 1.0f, 4.0f, 0.0f, Dilation(0.0f)),
                ModelTransform.of(0.3f, -4.1f, 4.9f, 0.0f, 0.1309f, -0.1309f)
            )
            return TexturedModelData.of(modelData, 128, 128)
        }
    }

    override fun setAngles(renderState: S) {}
}