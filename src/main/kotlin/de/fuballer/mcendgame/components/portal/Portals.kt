package de.fuballer.mcendgame.components.portal

import de.fuballer.mcendgame.components.portal.types.DefaultPortalEntity
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.minecraft.entity.passive.AbstractHorseEntity.createLivingAttributes

@Injectable
object Portals {
    val DEFAULT = PortalRegistry.register("default", ::DefaultPortalEntity)

    @Initializer
    fun reg() {
        FabricDefaultAttributeRegistry.register(DEFAULT, createLivingAttributes())
    }
}