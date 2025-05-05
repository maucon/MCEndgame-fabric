package de.fuballer.mcendgame.client.component.entity.custom.entities.portal.type.legacy

import de.fuballer.mcendgame.client.component.entity.custom.entities.portal.PortalRenderState
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer

class LegacyPortalEntityModel(
    modelPart: ModelPart,
) : EntityModel<PortalRenderState>(modelPart) {
    private val portal: ModelPart = root.getChild("portal")

    companion object {
        val PORTAL = EntityModelLayer(IdentifierUtil.default("legacy_portal"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root
            val portal = modelPartData.addChild(
                "portal",
                ModelPartBuilder.create().uv(0, 0).cuboid(-8.0f, -16.0f, 0.0f, 16.0f, 32.0f, 0.0f, Dilation(0.0f)),
                ModelTransform.origin(0.0f, 8.0f, 0.0f)
            )
            return TexturedModelData.of(modelData, 32, 32)
        }
    }
}