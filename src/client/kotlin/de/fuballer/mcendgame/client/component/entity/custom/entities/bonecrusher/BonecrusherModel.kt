package de.fuballer.mcendgame.client.component.entity.custom.entities.bonecrusher

import de.fuballer.mcendgame.main.component.entity.custom.entities.bonecrusher.BonecrusherEntity
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import software.bernie.geckolib.animatable.processing.AnimationState
import software.bernie.geckolib.constant.DataTickets
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.base.GeoRenderState
import kotlin.math.PI

class BonecrusherModel : GeoModel<BonecrusherEntity>() {
    companion object {
        val MODEL_IDENTIFIER = IdentifierUtil.default("geckolib/models/entity/bonecrusher.geo.json")
        val TEXTURE_IDENTIFIER = IdentifierUtil.default("textures/entity/bonecrusher/bonecrusher.png")
        val ANIMATION_IDENTIFIER = IdentifierUtil.default("geckolib/animations/entity/bonecrusher.animation.json")
    }

    override fun getModelResource(renderState: GeoRenderState) = MODEL_IDENTIFIER

    override fun getTextureResource(renderState: GeoRenderState) = TEXTURE_IDENTIFIER

    override fun getAnimationResource(entity: BonecrusherEntity) = ANIMATION_IDENTIFIER

    override fun setCustomAnimations(animationState: AnimationState<BonecrusherEntity>) {
        animationProcessor.getBone("head")?.let {
            var headPitch = animationState.getData(DataTickets.ENTITY_PITCH)
            if (headPitch != null) {
                headPitch = Math.clamp(headPitch, -35F, 35F)
                it.rotX = -headPitch * PI.toFloat() / 180F
            }
            var headYaw = animationState.getData(DataTickets.ENTITY_YAW)
            if (headYaw != null) {
                headYaw = Math.clamp(headYaw, -45F, 45F)
                it.rotY = -headYaw * PI.toFloat() / 180F
            }
        }
    }
}