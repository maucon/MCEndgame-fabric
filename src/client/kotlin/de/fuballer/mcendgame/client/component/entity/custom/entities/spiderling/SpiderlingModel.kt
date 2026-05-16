package de.fuballer.mcendgame.client.component.entity.custom.entities.spiderling

import de.fuballer.mcendgame.main.component.entity.custom.entities.spiderling.SpiderlingEntity
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.base.GeoRenderState

class SpiderlingModel : GeoModel<SpiderlingEntity>() {
    companion object {
        val MODEL_IDENTIFIER = IdentifierUtil.default("entity/spiderling")
        val TEXTURE_IDENTIFIER = IdentifierUtil.default("textures/entity/spiderling/spiderling.png")
        val ANIMATION_IDENTIFIER = IdentifierUtil.default("entity/spiderling")
    }

    override fun getModelResource(renderState: GeoRenderState) = MODEL_IDENTIFIER

    override fun getTextureResource(renderState: GeoRenderState) = TEXTURE_IDENTIFIER

    override fun getAnimationResource(entity: SpiderlingEntity) = ANIMATION_IDENTIFIER
}