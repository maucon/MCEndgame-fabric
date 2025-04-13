package de.fuballer.client.mcendgame.components.portal

import de.fuballer.mcendgame.components.portal.Portals
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry

@Injectable
class PortalModelRegisterer {
    @Initializer
    fun register() {
        EntityRendererRegistry.register(Portals.DEFAULT, ::DefaultPortalRenderer)
    }
}