package de.fuballer.mcendgame.components.entity.custom.entities.webshot

import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer


class WebshotEntityModel(
    modelPart: ModelPart,
) : EntityModel<WebshotRenderState>(modelPart) {
    val webshot = root.getChild("webshot")

    companion object {
        val WEBSHOT = EntityModelLayer(IdentifierUtil.default("webshot"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root
            val webshot = modelPartData.addChild(
                "webshot",
                ModelPartBuilder.create().uv(0, 5).cuboid(-2.5f, -2.0f, -0.5f, 4.0f, 4.0f, 2.0f, Dilation(0.0f))
                    .uv(2, 0).cuboid(-2.0f, -1.5f, -1.5f, 3.0f, 3.0f, 1.0f, Dilation(0.0f))
                    .uv(1, 12).cuboid(-2.0f, -1.5f, 1.5f, 3.0f, 3.0f, 2.0f, Dilation(0.0f))
                    .uv(2, 18).cuboid(-1.5f, -1.0f, 3.5f, 2.0f, 2.0f, 2.0f, Dilation(0.0f))
                    .uv(3, 23).cuboid(-1.0f, -0.5f, 5.5f, 1.0f, 1.0f, 2.0f, Dilation(0.0f)),
                ModelTransform.pivot(0.0f, 21.5f, -2.5f)
            )
            return TexturedModelData.of(modelData, 32, 32)
        }
    }

    override fun setAngles(
        renderState: WebshotRenderState,
    ) {
        super.setAngles(renderState)
    }
}