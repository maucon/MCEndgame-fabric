package de.fuballer.mcendgame.client.component.entity.custom.entities.bonecrusher

import de.fuballer.mcendgame.main.component.entity.custom.entities.bonecrusher.BonecrusherEntity
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.base.GeoRenderState

class BonecrusherModel : GeoModel<BonecrusherEntity>() {
    companion object {
        val MODEL_IDENTIFIER = IdentifierUtil.default("entity/bonecrusher")
        val TEXTURE_IDENTIFIER = IdentifierUtil.default("textures/entity/bonecrusher/bonecrusher.png")
        val ANIMATION_IDENTIFIER = IdentifierUtil.default("entity/bonecrusher")
    }

    override fun getModelResource(renderState: GeoRenderState) = MODEL_IDENTIFIER

    override fun getTextureResource(renderState: GeoRenderState) = TEXTURE_IDENTIFIER

    override fun getAnimationResource(entity: BonecrusherEntity) = ANIMATION_IDENTIFIER
}