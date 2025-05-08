package de.fuballer.mcendgame.client.component.item.custom.armor

import de.fuballer.mcendgame.client.component.item.custom.armor.wither_rose.WitherRoseRenderer
import de.fuballer.mcendgame.client.helper.BipedEntityGeoRenderState
import de.fuballer.mcendgame.main.client.ClientInstances
import de.fuballer.mcendgame.main.client.armor.ArmorRendererProvider
import de.fuballer.mcendgame.main.component.item.custom.armor.CustomArmorItems
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.minecraft.client.render.entity.equipment.EquipmentModel.LayerType
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.state.BipedEntityRenderState
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import software.bernie.geckolib.animatable.client.GeoRenderProvider
import software.bernie.geckolib.renderer.GeoArmorRenderer
import java.util.function.Consumer

@Injectable
object ArmorRendererRegisterer {
    val itemRendererMap = mapOf<Item, () -> GeoArmorRenderer<*, BipedEntityGeoRenderState>>(
        CustomArmorItems.WITHER_ROSE_HELMET to { WitherRoseRenderer.Helmet() },
        CustomArmorItems.WITHER_ROSE_CHESTPLATE to { WitherRoseRenderer.Chestplate() },
        CustomArmorItems.WITHER_ROSE_LEGGINGS to { WitherRoseRenderer.Leggings() },
        CustomArmorItems.WITHER_ROSE_BOOTS to { WitherRoseRenderer.Boots() },
    )

    @Initializer
    fun on() {
        ClientInstances.armorRendererProvider = object : ArmorRendererProvider() {
            override fun createRenderer(consumer: Consumer<GeoRenderProvider>, item: Item) {
                consumer.accept(object : GeoRenderProvider {
                    private var renderer: GeoArmorRenderer<*, *>? = null

                    override fun <S : BipedEntityRenderState?> getGeoArmorRenderer(
                        renderState: S?,
                        itemStack: ItemStack,
                        equipmentSlot: EquipmentSlot,
                        type: LayerType,
                        original: BipedEntityModel<S>?
                    ): GeoArmorRenderer<*, *>? {
                        if (renderer != null) return renderer
                        renderer = itemRendererMap[item]?.invoke()
                        return renderer
                    }
                })
            }
        }
    }
}