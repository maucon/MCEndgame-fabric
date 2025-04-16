package de.fuballer.client.mcendgame.components.entity.custom.entities.portal.type.test

import de.fuballer.client.mcendgame.components.entity.custom.entities.portal.PortalRenderState
import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer

class TestPortalEntityModel(
    modelPart: ModelPart,
) : EntityModel<PortalRenderState>(modelPart) {
    private val portal: ModelPart = root.getChild("portal")

    companion object {
        val PORTAL = EntityModelLayer(IdentifierUtil.default("test_portal"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root
            val portal = modelPartData.addChild(
                "portal",
                ModelPartBuilder.create().uv(0, 0).cuboid(-4.0f, -8.0f, 0.0f, 8.0f, 16.0f, 0.0f, Dilation(0.0f)),
                ModelTransform.pivot(0.0f, 4.0f, 0.0f)
            )
            return TexturedModelData.of(modelData, 32, 32)
        }
    }
}