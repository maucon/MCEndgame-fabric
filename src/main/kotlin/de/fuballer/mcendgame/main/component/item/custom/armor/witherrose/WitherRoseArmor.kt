package de.fuballer.mcendgame.main.component.item.custom.armor.witherrose

import de.fuballer.mcendgame.main.client.ClientInstances
import net.minecraft.item.Item
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.animatable.client.GeoRenderProvider
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animatable.manager.AnimatableManager
import software.bernie.geckolib.util.GeckoLibUtil
import java.util.function.Consumer

class WitherRoseHelmet(
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

class WitherRoseChestplate(
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

class WitherRoseLeggings(
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

class WitherRoseBoots(
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
