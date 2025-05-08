package de.fuballer.mcendgame.client.component.item.custom.armor.boots.wither_rose_boots

import de.fuballer.mcendgame.main.component.item.custom.armor.witherrose.WitherRoseBoots
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.base.GeoRenderState

class WitherRoseBootsModel : GeoModel<WitherRoseBoots>() {
    companion object {
        val MODEL_IDENTIFIER = IdentifierUtil.default("geckolib/models/armor/wither_rose_boots.geo.json")
        val TEXTURE_IDENTIFIER = IdentifierUtil.default("textures/armor/wither_rose.png")
        val ANIMATION_IDENTIFIER = IdentifierUtil.default("geckolib/animations/armor/wither_rose_boots.json")
    }

    override fun getModelResource(renderState: GeoRenderState) = MODEL_IDENTIFIER

    override fun getTextureResource(renderState: GeoRenderState) = TEXTURE_IDENTIFIER

    override fun getAnimationResource(item: WitherRoseBoots) = ANIMATION_IDENTIFIER
}