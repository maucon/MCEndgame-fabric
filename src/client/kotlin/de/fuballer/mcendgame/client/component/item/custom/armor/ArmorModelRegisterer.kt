package de.fuballer.mcendgame.client.component.item.custom.armor

import de.fuballer.mcendgame.client.component.item.custom.armor.bound_abyss.BoundAbyssModel
import de.fuballer.mcendgame.client.component.item.custom.armor.druids.DruidsBootsModel
import de.fuballer.mcendgame.client.component.item.custom.armor.druids.DruidsChestplateModel
import de.fuballer.mcendgame.client.component.item.custom.armor.druids.DruidsHelmetModel
import de.fuballer.mcendgame.client.component.item.custom.armor.druids.DruidsLeggingsModel
import de.fuballer.mcendgame.client.component.item.custom.armor.emberchant.EmberchantModel
import de.fuballer.mcendgame.client.component.item.custom.armor.iceborne.IceborneModel
import de.fuballer.mcendgame.client.component.item.custom.armor.lamias_gift.LamiasGiftModel
import de.fuballer.mcendgame.client.component.item.custom.armor.wither_rose.WitherRoseBootsModel
import de.fuballer.mcendgame.client.component.item.custom.armor.wither_rose.WitherRoseChestplateModel
import de.fuballer.mcendgame.client.component.item.custom.armor.wither_rose.WitherRoseHelmetModel
import de.fuballer.mcendgame.client.component.item.custom.armor.wither_rose.WitherRoseLeggingsModel
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
        EntityModelLayerRegistry.registerModelLayer(
            EmberchantModel.MODEL_LAYER,
            EmberchantModel.Companion::getTexturedModelData
        )
        EntityModelLayerRegistry.registerModelLayer(
            LamiasGiftModel.MODEL_LAYER,
            LamiasGiftModel.Companion::getTexturedModelData
        )
        EntityModelLayerRegistry.registerModelLayer(
            WitherRoseHelmetModel.MODEL_LAYER,
            WitherRoseHelmetModel.Companion::getTexturedModelData
        )
        EntityModelLayerRegistry.registerModelLayer(
            WitherRoseChestplateModel.MODEL_LAYER,
            WitherRoseChestplateModel.Companion::getTexturedModelData
        )
        EntityModelLayerRegistry.registerModelLayer(
            WitherRoseLeggingsModel.MODEL_LAYER,
            WitherRoseLeggingsModel.Companion::getTexturedModelData
        )
        EntityModelLayerRegistry.registerModelLayer(
            WitherRoseBootsModel.MODEL_LAYER,
            WitherRoseBootsModel.Companion::getTexturedModelData
        )
    }
}