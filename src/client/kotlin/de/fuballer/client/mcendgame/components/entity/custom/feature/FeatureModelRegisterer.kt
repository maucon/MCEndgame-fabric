package de.fuballer.client.mcendgame.components.entity.custom.feature

import de.fuballer.client.mcendgame.components.entity.custom.feature.webbed.WebbedModel
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry

@Injectable
object FeatureModelRegisterer {
    @Initializer
    fun register() {
        EntityModelLayerRegistry.registerModelLayer(
            WebbedModel.WEBBED_LAYER,
            WebbedModel::getTexturedModelData
        )
    }
}