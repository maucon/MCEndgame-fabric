package de.fuballer.mcendgame.client.component.item.custom.armor.model.wither_rose

import de.fuballer.mcendgame.client.component.item.custom.ModelPartDataExtension.createEmptyChild
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.render.entity.state.BipedEntityRenderState

class WitherRoseHelmetModel<S : BipedEntityRenderState>(
    root: ModelPart,
) : BipedEntityModel<S>(root) {
    companion object {
        val MODEL_LAYER = EntityModelLayer(IdentifierUtil.default("wither_rose_helmet"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root

            val body = modelPartData.createEmptyChild(EntityModelPartNames.BODY)
            val left_arm = modelPartData.createEmptyChild(EntityModelPartNames.LEFT_ARM)
            val right_arm = modelPartData.createEmptyChild(EntityModelPartNames.RIGHT_ARM)
            val left_leg = modelPartData.createEmptyChild(EntityModelPartNames.LEFT_LEG)
            val right_leg = modelPartData.createEmptyChild(EntityModelPartNames.RIGHT_LEG)

            val head = modelPartData.addChild("head", ModelPartBuilder.create(), ModelTransform.origin(0.0f, 0.0f, 0.0f))
            val hat = head.createEmptyChild(EntityModelPartNames.HAT)

            val helmet =
                head.addChild("helmet", ModelPartBuilder.create().uv(29, 46).cuboid(-4.5f, -8.5f, -4.5f, 9.0f, 10.0f, 9.0f, Dilation(0.25f)), ModelTransform.origin(0.0f, 0.0f, 0.0f))
            return TexturedModelData.of(modelData, 128, 128)
        }
    }

    override fun setAngles(renderState: S) {}
}