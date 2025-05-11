package de.fuballer.mcendgame.client.component.item.custom.armor.forest_queen

import de.fuballer.mcendgame.main.component.item.custom.armor.forest_queen.ForestQueenChestplate
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.base.GeoRenderState

class ForestQueenModel {
    class Chestplate : GeoModel<ForestQueenChestplate>() {
        companion object {
            val MODEL_IDENTIFIER = IdentifierUtil.default("geckolib/models/armor/forest_queen_chestplate.geo.json")
            val TEXTURE_IDENTIFIER = IdentifierUtil.default("textures/armor/forest_queen.png")
            val ANIMATION_IDENTIFIER = IdentifierUtil.default("geckolib/animations/armor/forest_queen_chestplate.json")
        }

        override fun getModelResource(renderState: GeoRenderState) = MODEL_IDENTIFIER

        override fun getTextureResource(renderState: GeoRenderState) = TEXTURE_IDENTIFIER

        override fun getAnimationResource(item: ForestQueenChestplate) = ANIMATION_IDENTIFIER
    }
}