package de.fuballer.mcendgame.components.entity.custom.entities.arachne

import net.minecraft.client.render.entity.state.LivingEntityRenderState
import net.minecraft.entity.AnimationState

class ArachneRenderState : LivingEntityRenderState() {
    val idleAnimationState: AnimationState = AnimationState()
    val walkAnimationState: AnimationState = AnimationState()
    val walkBWAnimationState: AnimationState = AnimationState()

    val spitAnimationState: AnimationState = AnimationState()

    var isSaddled: Boolean = false
    var moveSpeed: Float = 0F
}