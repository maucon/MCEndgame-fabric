package de.fuballer.client.mcendgame.components.portal

import net.minecraft.client.render.entity.state.LivingEntityRenderState
import net.minecraft.entity.AnimationState

class DefaultPortalRenderState : LivingEntityRenderState() {
    val startAnimationState = AnimationState()
    val idleAnimationState = AnimationState()
}