package de.fuballer.client.mcendgame.components.entity.custom.feature.webbed

import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.entity.model.EntityModelLayer

class WebbedModel(
    modelPart: ModelPart,
) : Model(modelPart, RenderLayer::getEntityCutoutNoCull) {
    companion object {
        val WEBBED_LAYER = EntityModelLayer(IdentifierUtil.default("webbed"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root
            val webbed = modelPartData.addChild(
                "webbed",
                ModelPartBuilder.create().uv(0, 0).cuboid(-8.0f, -24.0f, -8.0f, 16.0f, 24.0f, 16.0f, Dilation(0.0f))
                    .uv(64, 0).cuboid(-8.0f, -24.0f, -8.0f, 16.0f, 24.0f, 16.0f, Dilation(0.5f)),
                ModelTransform.pivot(0.0f, 24.0f, 0.0f)
            )
            return TexturedModelData.of(modelData, 128, 64)
        }
    }
}