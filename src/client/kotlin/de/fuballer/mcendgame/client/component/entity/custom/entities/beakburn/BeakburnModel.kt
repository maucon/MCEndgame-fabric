package de.fuballer.mcendgame.client.component.entity.custom.entities.beakburn

import de.fuballer.mcendgame.main.component.entity.custom.entities.beakburn.BeakburnEntity
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.base.GeoRenderState

class BeakburnModel : GeoModel<BeakburnEntity>() {
    companion object {
        val MODEL_IDENTIFIER = IdentifierUtil.default("entity/beakburn")
        val TEXTURE_IDENTIFIER = IdentifierUtil.default("textures/entity/beakburn/beakburn.png")
        val ANIMATION_IDENTIFIER = IdentifierUtil.default("entity/beakburn")
    }

    override fun getModelResource(renderState: GeoRenderState) = MODEL_IDENTIFIER

    override fun getTextureResource(renderState: GeoRenderState) = TEXTURE_IDENTIFIER

    override fun getAnimationResource(entity: BeakburnEntity) = ANIMATION_IDENTIFIER
}