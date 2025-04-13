package de.fuballer.client.mcendgame.components.entity.custom.entities.portal.type

import de.fuballer.client.mcendgame.components.entity.custom.entities.portal.PortalModel
import de.fuballer.mcendgame.components.portal.type.DefaultPortalType
import de.fuballer.mcendgame.components.portal.type.TestPortalType
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.util.Identifier

interface PortalRenderType {
    fun getId(): String
    fun getTexture(): Identifier
    fun getShadowRadius(): Float
    fun getModel(context: EntityRendererFactory.Context): PortalModel

    companion object {
        private val PORTAL_TYPES = mapOf(
            TestPortalRenderType.ID to TestPortalRenderType()
        )

        fun getType(typeName: String): PortalRenderType {
             return PORTAL_TYPES[typeName]
                ?:  DefaultPortalRenderType()
        }
    }
}