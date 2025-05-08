package de.fuballer.mcendgame.main.component.item.custom.armor.witherrose

import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.animatable.client.GeoRenderProvider
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animatable.manager.AnimatableManager
import software.bernie.geckolib.renderer.GeoArmorRenderer
import software.bernie.geckolib.util.GeckoLibUtil
import java.util.function.Consumer

class WitherRoseBoots(
    settings: Item.Settings,
) : Item(settings), GeoItem {
    private val cache = GeckoLibUtil.createInstanceCache(this)
    override fun getAnimatableInstanceCache(): AnimatableInstanceCache = cache

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
    }

    override fun createGeoRenderer(consumer: Consumer<GeoRenderProvider>) {
        consumer.accept(object : GeoRenderProvider {
            private var renderer: WitherRoseBootsRenderer<*>? = null

            override fun <S : net.minecraft.client.render.entity.state.BipedEntityRenderState?> getGeoArmorRenderer(
                renderState: S?,
                itemStack: ItemStack?,
                equipmentSlot: EquipmentSlot?,
                type: net.minecraft.client.render.entity.equipment.EquipmentModel.LayerType?,
                original: net.minecraft.client.render.entity.model.BipedEntityModel<S>?
            ): GeoArmorRenderer<*, *> {
                if(renderer == null) {
                    renderer = WitherRoseBootsRenderer()
                }
                return renderer
            }
        })
    }
}