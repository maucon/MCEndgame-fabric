package de.fuballer.client.mcendgame.components.entity.custom.entities.portal.type

import de.fuballer.client.mcendgame.components.entity.custom.entities.portal.PortalRenderState
import de.fuballer.client.mcendgame.components.entity.custom.entities.portal.type.default_.DefaultPortalRenderType
import de.fuballer.client.mcendgame.components.entity.custom.entities.portal.type.test.TestPortalRenderType
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.util.Identifier

interface PortalRenderType {
    fun getId(): String
    fun getTexture(): Identifier
    fun getShadowRadius(): Float
    fun getModel(context: EntityRendererFactory.Context): EntityModel<PortalRenderState>

    companion object {
        private val PORTAL_TYPES = mapOf(
            TestPortalRenderType.ID to TestPortalRenderType()
        )

        fun getType(typeName: String): PortalRenderType {
            return PORTAL_TYPES[typeName]
                ?: DefaultPortalRenderType()
        }
    }
}