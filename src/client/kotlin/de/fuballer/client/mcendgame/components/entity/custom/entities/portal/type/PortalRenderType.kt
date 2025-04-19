package de.fuballer.client.mcendgame.components.entity.custom.entities.portal.type

import de.fuballer.client.mcendgame.components.entity.custom.entities.portal.PortalRenderState
import de.fuballer.client.mcendgame.components.entity.custom.entities.portal.PortalRenderer
import de.fuballer.client.mcendgame.components.entity.custom.entities.portal.type.default_.DefaultPortalRenderType
import de.fuballer.client.mcendgame.components.entity.custom.entities.portal.type.legacy.LegacyPortalRenderType
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.util.Identifier

interface PortalRenderType {
    fun getId(): String
    fun getTexture(age: Float): Identifier
    fun getShadowRadius(): Float
    fun getModel(context: EntityRendererFactory.Context): EntityModel<PortalRenderState>
    fun getRenderLayer(renderer: PortalRenderer, state: PortalRenderState, showBody: Boolean, translucent: Boolean, showOutline: Boolean): RenderLayer? = null

    companion object {
        private val PORTAL_TYPES = mapOf(
            LegacyPortalRenderType.ID to LegacyPortalRenderType()
        )

        fun getType(typeName: String): PortalRenderType {
            return PORTAL_TYPES[typeName]
                ?: DefaultPortalRenderType()
        }
    }
}