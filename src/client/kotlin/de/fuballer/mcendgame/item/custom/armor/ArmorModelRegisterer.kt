package de.fuballer.mcendgame.item.custom.armor

import de.fuballer.mcendgame.item.custom.armor.helmet.iceborne.IceborneModel
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry

object ArmorModelRegisterer {
    fun register() {
        EntityModelLayerRegistry.registerModelLayer(
            IceborneModel.MODEL_LAYER,
            IceborneModel.Companion::getTexturedModelData
        )
    }
}