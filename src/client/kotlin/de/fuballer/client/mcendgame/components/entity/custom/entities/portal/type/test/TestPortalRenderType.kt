package de.fuballer.client.mcendgame.components.entity.custom.entities.portal.type.test

import de.fuballer.client.mcendgame.components.entity.custom.entities.portal.PortalRenderState
import de.fuballer.client.mcendgame.components.entity.custom.entities.portal.type.PortalRenderType
import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.util.Identifier

class TestPortalRenderType : PortalRenderType {
    companion object {
        const val ID = "test"
    }

    override fun getId() = ID

    override fun getTexture(): Identifier {
        return IdentifierUtil.default("textures/entity/swamp_golem/swamp_golem.png")
    }

    override fun getShadowRadius(): Float {
        return 0.2f
    }

    override fun getModel(context: EntityRendererFactory.Context): EntityModel<PortalRenderState> {
        return TestPortalEntityModel(context.getPart(TestPortalEntityModel.PORTAL))
    }
}