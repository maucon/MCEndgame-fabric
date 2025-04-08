package de.fuballer.mcendgame.components.entity.custom.feature

import de.fuballer.mcendgame.components.entity.custom.feature.webbed.WebbedModel
import de.maucon.mauconframework.annotation.Initialize
import de.maucon.mauconframework.annotation.Injectable
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry

@Injectable
object FeatureModelRegisterer {
    @Initialize
    fun register() {
        EntityModelLayerRegistry.registerModelLayer(
            WebbedModel.WEBBED_LAYER,
            WebbedModel.Companion::getTexturedModelData
        )
    }
}