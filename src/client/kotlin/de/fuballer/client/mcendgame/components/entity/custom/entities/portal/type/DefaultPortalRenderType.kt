package de.fuballer.client.mcendgame.components.entity.custom.entities.portal.type

import de.fuballer.client.mcendgame.components.entity.custom.entities.portal.PortalModel
import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.util.Identifier

class DefaultPortalRenderType : PortalRenderType {
    companion object {
        const val ID = "default"
    }

    override fun getId() = ID

    override fun getTexture(): Identifier {
        return IdentifierUtil.default("textures/entity/webshot/webshot.png")
    }

    override fun getShadowRadius(): Float {
        return 1.0f
    }

    override fun getModel(context: EntityRendererFactory.Context): PortalModel {
        return PortalModel(context.getPart(PortalModel.DEFAULT_PORTAL))
    }
}