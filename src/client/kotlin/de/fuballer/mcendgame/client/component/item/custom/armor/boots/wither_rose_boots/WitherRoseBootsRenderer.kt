package de.fuballer.mcendgame.client.component.item.custom.armor.boots.wither_rose_boots

import de.fuballer.mcendgame.main.component.item.custom.armor.witherrose.WitherRoseBoots
import net.minecraft.client.render.entity.state.BipedEntityRenderState
import software.bernie.geckolib.renderer.GeoArmorRenderer
import software.bernie.geckolib.renderer.base.GeoRenderState

class WitherRoseBootsRenderer<R> :
    GeoArmorRenderer<WitherRoseBoots, R>(WitherRoseBootsModel()) where  R : BipedEntityRenderState, R : GeoRenderState {
}