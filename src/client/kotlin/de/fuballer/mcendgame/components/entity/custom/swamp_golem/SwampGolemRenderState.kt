package de.fuballer.mcendgame.components.entity.custom.swamp_golem

import net.minecraft.client.render.entity.state.LivingEntityRenderState
import net.minecraft.entity.AnimationState

class SwampGolemRenderState : LivingEntityRenderState() {
    val slamAnimationState: AnimationState = AnimationState()
    val idleAnimationState: AnimationState = AnimationState()
}