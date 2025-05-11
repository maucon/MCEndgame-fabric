package de.fuballer.mcendgame.main.component.item.custom.armor.forest_queen

import de.fuballer.mcendgame.main.client.ClientInstances
import net.minecraft.item.Item
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.animatable.client.GeoRenderProvider
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animatable.manager.AnimatableManager
import software.bernie.geckolib.util.GeckoLibUtil
import java.util.function.Consumer

class ForestQueenChestplate(
    settings: Settings,
) : Item(settings), GeoItem {
    private val cache = GeckoLibUtil.createInstanceCache(this)
    override fun getAnimatableInstanceCache(): AnimatableInstanceCache = cache

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
    }

    override fun createGeoRenderer(consumer: Consumer<GeoRenderProvider>) {
        ClientInstances.armorRendererProvider?.createRenderer(consumer, this)
    }
}