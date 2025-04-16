package de.fuballer.client.mcendgame.components.entity.custom.entities.portal

import de.fuballer.client.mcendgame.components.entity.custom.entities.portal.type.default_.DefaultPortalRenderType
import de.fuballer.client.mcendgame.components.entity.custom.entities.portal.type.PortalRenderType
import net.minecraft.client.render.entity.state.LivingEntityRenderState
import net.minecraft.entity.AnimationState

class PortalRenderState : LivingEntityRenderState() {
    var type: PortalRenderType = DefaultPortalRenderType()

    val openAnimationState = AnimationState()
    val idleAnimationState = AnimationState()
    val closeAnimationState = AnimationState()
}