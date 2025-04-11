package de.fuballer.client.mcendgame.components.item.custom.armor

import de.fuballer.client.mcendgame.components.item.custom.armor.boots.druids_boots.DruidsBootsModel
import de.fuballer.client.mcendgame.components.item.custom.armor.chestplate.bound_abyss.BoundAbyssModel
import de.fuballer.client.mcendgame.components.item.custom.armor.chestplate.druids_chestplate.DruidsChestplateModel
import de.fuballer.client.mcendgame.components.item.custom.armor.helmet.druids_helmet.DruidsHelmetModel
import de.fuballer.client.mcendgame.components.item.custom.armor.helmet.iceborne.IceborneModel
import de.fuballer.client.mcendgame.components.item.custom.armor.leggings.druids_leggings.DruidsLeggingsModel
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry

@Injectable
object ArmorModelRegisterer {
    @Initializer
    fun register() {
        EntityModelLayerRegistry.registerModelLayer(
            IceborneModel.MODEL_LAYER,
            IceborneModel.Companion::getTexturedModelData
        )
        EntityModelLayerRegistry.registerModelLayer(
            BoundAbyssModel.MODEL_LAYER,
            BoundAbyssModel.Companion::getTexturedModelData
        )
        EntityModelLayerRegistry.registerModelLayer(
            DruidsHelmetModel.MODEL_LAYER,
            DruidsHelmetModel.Companion::getTexturedModelData
        )
        EntityModelLayerRegistry.registerModelLayer(
            DruidsChestplateModel.MODEL_LAYER,
            DruidsChestplateModel.Companion::getTexturedModelData
        )
        EntityModelLayerRegistry.registerModelLayer(
            DruidsLeggingsModel.MODEL_LAYER,
            DruidsLeggingsModel.Companion::getTexturedModelData
        )
        EntityModelLayerRegistry.registerModelLayer(
            DruidsBootsModel.MODEL_LAYER,
            DruidsBootsModel.Companion::getTexturedModelData
        )
    }
}