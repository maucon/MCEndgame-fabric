package de.fuballer.client.mcendgame.components.entity.custom.entities.portal.type.default_

import de.fuballer.client.mcendgame.components.entity.custom.entities.portal.PortalRenderState
import de.fuballer.client.mcendgame.components.entity.custom.entities.portal.type.PortalRenderType
import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.util.Identifier

class DefaultPortalRenderType : PortalRenderType {
    companion object {
        const val ID = "default"
    }

    override fun getId() = ID

    override fun getTexture(): Identifier {
        return IdentifierUtil.default("textures/entity/portal/portal.png")
    }

    override fun getShadowRadius(): Float {
        return 0.45f
    }

    override fun getModel(context: EntityRendererFactory.Context): EntityModel<PortalRenderState> {
        return DefaultPortalEntityModel(context.getPart(DefaultPortalEntityModel.PORTAL))
    }
}