package de.fuballer.mcendgame.client.component.item.custom.armor.wither_rose

import de.fuballer.mcendgame.main.component.item.custom.armor.witherrose.WitherRoseBoots
import de.fuballer.mcendgame.main.component.item.custom.armor.witherrose.WitherRoseChestplate
import de.fuballer.mcendgame.main.component.item.custom.armor.witherrose.WitherRoseHelmet
import de.fuballer.mcendgame.main.component.item.custom.armor.witherrose.WitherRoseLeggings
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.base.GeoRenderState

class WitherRoseModel {
    class Helmet : GeoModel<WitherRoseHelmet>() {
        companion object {
            val MODEL_IDENTIFIER = IdentifierUtil.default("geckolib/models/armor/wither_rose_helmet.geo.json")
            val TEXTURE_IDENTIFIER = IdentifierUtil.default("textures/armor/wither_rose.png")
            val ANIMATION_IDENTIFIER = IdentifierUtil.default("geckolib/animations/armor/wither_rose_helmet.json")
        }

        override fun getModelResource(renderState: GeoRenderState) = MODEL_IDENTIFIER

        override fun getTextureResource(renderState: GeoRenderState) = TEXTURE_IDENTIFIER

        override fun getAnimationResource(item: WitherRoseHelmet) = ANIMATION_IDENTIFIER
    }

    class Chestplate : GeoModel<WitherRoseChestplate>() {
        companion object {
            val MODEL_IDENTIFIER = IdentifierUtil.default("geckolib/models/armor/wither_rose_chestplate.geo.json")
            val TEXTURE_IDENTIFIER = IdentifierUtil.default("textures/armor/wither_rose.png")
            val ANIMATION_IDENTIFIER = IdentifierUtil.default("geckolib/animations/armor/wither_rose_chestplate.json")
        }

        override fun getModelResource(renderState: GeoRenderState) = MODEL_IDENTIFIER

        override fun getTextureResource(renderState: GeoRenderState) = TEXTURE_IDENTIFIER

        override fun getAnimationResource(item: WitherRoseChestplate) = ANIMATION_IDENTIFIER
    }

    class Leggings : GeoModel<WitherRoseLeggings>() {
        companion object {
            val MODEL_IDENTIFIER = IdentifierUtil.default("geckolib/models/armor/wither_rose_leggings.geo.json")
            val TEXTURE_IDENTIFIER = IdentifierUtil.default("textures/armor/wither_rose.png")
            val ANIMATION_IDENTIFIER = IdentifierUtil.default("geckolib/animations/armor/wither_rose_leggings.json")
        }

        override fun getModelResource(renderState: GeoRenderState) = MODEL_IDENTIFIER

        override fun getTextureResource(renderState: GeoRenderState) = TEXTURE_IDENTIFIER

        override fun getAnimationResource(item: WitherRoseLeggings) = ANIMATION_IDENTIFIER
    }

    class Boots : GeoModel<WitherRoseBoots>() {
        companion object {
            val MODEL_IDENTIFIER = IdentifierUtil.default("geckolib/models/armor/wither_rose_boots.geo.json")
            val TEXTURE_IDENTIFIER = IdentifierUtil.default("textures/armor/wither_rose.png")
            val ANIMATION_IDENTIFIER = IdentifierUtil.default("geckolib/animations/armor/wither_rose_boots.json")
        }

        override fun getModelResource(renderState: GeoRenderState) = MODEL_IDENTIFIER

        override fun getTextureResource(renderState: GeoRenderState) = TEXTURE_IDENTIFIER

        override fun getAnimationResource(item: WitherRoseBoots) = ANIMATION_IDENTIFIER
    }
}