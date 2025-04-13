package de.fuballer.client.mcendgame.components.portal

import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer

class DefaultPortalModel(
    modelPart: ModelPart,
) : EntityModel<DefaultPortalRenderState>(modelPart) {
    val webshot = root.getChild("webshot")

    companion object {
        val DEFAULT_PORTAL = EntityModelLayer(IdentifierUtil.default("webshot"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root
            val webshot = modelPartData.addChild(
                "webshot",
                ModelPartBuilder.create().uv(0, 5).cuboid(-2.0f, -2.0f, -1.0f, 4.0f, 4.0f, 2.0f, Dilation(0.0f))
                    .uv(2, 0).cuboid(-1.5f, -1.5f, -2.0f, 3.0f, 3.0f, 1.0f, Dilation(0.0f))
                    .uv(1, 12).cuboid(-1.5f, -1.5f, 1.0f, 3.0f, 3.0f, 2.0f, Dilation(0.0f))
                    .uv(2, 18).cuboid(-1.0f, -1.0f, 3.0f, 2.0f, 2.0f, 2.0f, Dilation(0.0f))
                    .uv(3, 23).cuboid(-0.5f, -0.5f, 5.0f, 1.0f, 1.0f, 2.0f, Dilation(0.0f)),
                ModelTransform.NONE
            )
            return TexturedModelData.of(modelData, 32, 32)
        }
    }
}