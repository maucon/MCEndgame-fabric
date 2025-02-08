package de.fuballer.mcendgame.components.entity.custom.swamp_golem

import net.minecraft.client.render.entity.state.BipedEntityRenderState

class SwampGolemRenderState : BipedEntityRenderState() {
    var isSlamming = false
    var slamProgress = 0.0
}