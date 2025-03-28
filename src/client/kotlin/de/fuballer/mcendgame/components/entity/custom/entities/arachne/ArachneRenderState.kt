package de.fuballer.mcendgame.components.entity.custom.entities.arachne

import net.minecraft.client.render.entity.state.LivingEntityRenderState

class ArachneRenderState : LivingEntityRenderState() {
    var isMoving: Boolean = false
    var movingTicks: Float = 0F
}