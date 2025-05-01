package de.fuballer.client.mcendgame.component.entity.custom.entities.elf_duelist

import de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist.ElfDuelistEntity
import de.fuballer.mcendgame.util.IdentifierUtil
import software.bernie.geckolib.animatable.processing.AnimationState
import software.bernie.geckolib.constant.DataTickets
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.base.GeoRenderState
import kotlin.math.PI

class ElfDuelistModel : GeoModel<ElfDuelistEntity>() {
    companion object {
        val MODEL_IDENTIFIER = IdentifierUtil.default("geckolib/models/entity/elf_duelist.geo.json")
        val TEXTURE_IDENTIFIER = IdentifierUtil.default("textures/entity/elf_duelist/elf_duelist.png")
        val ANIMATION_IDENTIFIER = IdentifierUtil.default("geckolib/animations/entity/elf_duelist.animation.json")
    }

    override fun getModelResource(renderState: GeoRenderState) = MODEL_IDENTIFIER

    override fun getTextureResource(renderState: GeoRenderState) = TEXTURE_IDENTIFIER

    override fun getAnimationResource(entity: ElfDuelistEntity) = ANIMATION_IDENTIFIER

    override fun setCustomAnimations(animationState: AnimationState<ElfDuelistEntity>) {
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