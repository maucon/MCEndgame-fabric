package de.fuballer.mcendgame.client.component.entity.custom.entities.swamp_golem

import net.minecraft.client.render.entity.state.LivingEntityRenderState
import net.minecraft.entity.AnimationState
import software.bernie.geckolib.constant.dataticket.DataTicket

class SwampGolemRenderState : LivingEntityRenderState() {
    val slamAnimationState: AnimationState = AnimationState()
    val idleAnimationState: AnimationState = AnimationState()
    val walkAnimationState: AnimationState = AnimationState()

    override fun getDataMap(): Map<DataTicket<*>, Any> = mapOf()
}