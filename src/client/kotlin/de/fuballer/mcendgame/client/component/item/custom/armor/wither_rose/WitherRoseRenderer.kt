package de.fuballer.mcendgame.client.component.item.custom.armor.wither_rose

import de.fuballer.mcendgame.main.component.item.custom.armor.witherrose.WitherRoseBoots
import de.fuballer.mcendgame.main.component.item.custom.armor.witherrose.WitherRoseChestplate
import de.fuballer.mcendgame.main.component.item.custom.armor.witherrose.WitherRoseHelmet
import de.fuballer.mcendgame.main.component.item.custom.armor.witherrose.WitherRoseLeggings
import net.minecraft.client.render.entity.state.BipedEntityRenderState
import net.minecraft.entity.EquipmentSlot
import software.bernie.geckolib.renderer.GeoArmorRenderer
import software.bernie.geckolib.renderer.base.GeoRenderState

class WitherRoseRenderer {
    class Helmet<R> : GeoArmorRenderer<WitherRoseHelmet, R>(WitherRoseModel.Helmet()) where  R : BipedEntityRenderState, R : GeoRenderState
    class Chestplate<R> : GeoArmorRenderer<WitherRoseChestplate, R>(WitherRoseModel.Chestplate()) where  R : BipedEntityRenderState, R : GeoRenderState
    class Leggings<R> : GeoArmorRenderer<WitherRoseLeggings, R>(WitherRoseModel.Leggings()) where  R : BipedEntityRenderState, R : GeoRenderState {
        override fun applyBoneVisibilityBySlot(slot: EquipmentSlot) {
            super.applyBoneVisibilityBySlot(slot)
            if (slot != EquipmentSlot.LEGS) return
            setBonesVisible(rightLeg.visible, bodyBone)
        }
    }

    class Boots<R> : GeoArmorRenderer<WitherRoseBoots, R>(WitherRoseModel.Boots()) where  R : BipedEntityRenderState, R : GeoRenderState
}