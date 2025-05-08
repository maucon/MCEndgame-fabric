package de.fuballer.mcendgame.main.client.armor

import net.minecraft.item.Item
import software.bernie.geckolib.animatable.client.GeoRenderProvider
import java.util.function.Consumer

abstract class ArmorRendererProvider {
    abstract fun createRenderer(consumer: Consumer<GeoRenderProvider>, item: Item)
}