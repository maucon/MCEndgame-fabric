package de.fuballer.mcendgame.client.component.block

import de.fuballer.mcendgame.client.component.block.totem_statue.TotemStatueBlockEntityModel
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry

@Injectable
object BlockEntityModelRegisterer {
    @Initializer
    fun register() {
        EntityModelLayerRegistry.registerModelLayer(
            TotemStatueBlockEntityModel.MODEL_LAYER,
            TotemStatueBlockEntityModel.Companion::getTexturedModelData
        )
    }
}