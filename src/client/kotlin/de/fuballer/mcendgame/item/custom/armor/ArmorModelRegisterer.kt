package de.fuballer.mcendgame.item.custom.armor

import de.fuballer.mcendgame.item.custom.armor.chestplate.bound_abyss.BoundAbyssModel
import de.fuballer.mcendgame.item.custom.armor.helmet.iceborne.IceborneModel
import de.maucon.mauconframework.annotation.Initialize
import de.maucon.mauconframework.annotation.Injectable
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry

@Injectable
object ArmorModelRegisterer {
    @Initialize
    fun register() {
        EntityModelLayerRegistry.registerModelLayer(
            IceborneModel.MODEL_LAYER,
            IceborneModel.Companion::getTexturedModelData
        )
        EntityModelLayerRegistry.registerModelLayer(
            BoundAbyssModel.MODEL_LAYER,
            BoundAbyssModel.Companion::getTexturedModelData
        )
    }
}