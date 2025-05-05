package de.fuballer.mcendgame.client.component.entity.custom.entities.portal.type.legacy

import de.fuballer.mcendgame.client.component.entity.custom.entities.portal.PortalRenderState
import de.fuballer.mcendgame.client.component.entity.custom.entities.portal.type.PortalRenderType
import de.fuballer.mcendgame.util.minecraft.IdentifierUtil
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.util.Identifier

class LegacyPortalRenderType : PortalRenderType {
    companion object {
        const val ID = "legacy"

        private val TEXTURE = IdentifierUtil.default("textures/entity/portal/legacy/portal.png")
    }

    override fun getId() = ID

    override fun getTexture(age: Float): Identifier = TEXTURE

    override fun getShadowRadius(): Float {
        return 0.3f
    }

    override fun getModel(context: EntityRendererFactory.Context): EntityModel<de.fuballer.mcendgame.client.component.entity.custom.entities.portal.PortalRenderState> {
        return LegacyPortalEntityModel(context.getPart(LegacyPortalEntityModel.PORTAL))
    }
}