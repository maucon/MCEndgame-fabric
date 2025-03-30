package de.fuballer.mcendgame.components.entity.custom.entities.webshot

import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.model.ModelData
import net.minecraft.client.model.ModelPart
import net.minecraft.client.model.TexturedModelData
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer

class WebshotEntityModel(
    modelPart: ModelPart,
) : EntityModel<WebshotRenderState>(modelPart) {
    companion object {
        val WEBSHOT = EntityModelLayer(IdentifierUtil.default("webshot"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            return TexturedModelData.of(modelData, 256, 256)
        }
    }

    override fun setAngles(
        renderState: WebshotRenderState,
    ) {
        super.setAngles(renderState)
    }
}