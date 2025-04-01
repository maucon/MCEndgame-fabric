package de.fuballer.mcendgame.components.entity.custom

import de.fuballer.mcendgame.components.entity.custom.swamp_golem.SwampGolemEntityModel
import de.fuballer.mcendgame.components.entity.custom.swamp_golem.SwampGolemRenderer
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry

@Injectable
object EntityModelRegisterer {
    @Initializer
    fun register() {
        EntityModelLayerRegistry.registerModelLayer(
            SwampGolemEntityModel.SWAMP_GOLEM,
            SwampGolemEntityModel.Companion::getTexturedModelData
        )
        EntityRendererRegistry.register(CustomEntities.SWAMP_GOLEM, ::SwampGolemRenderer)
    }
}