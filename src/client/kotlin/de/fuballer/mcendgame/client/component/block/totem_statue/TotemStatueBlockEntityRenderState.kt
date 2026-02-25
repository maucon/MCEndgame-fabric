package de.fuballer.mcendgame.client.component.block.totem_statue

import net.minecraft.client.render.block.entity.state.BlockEntityRenderState

class TotemStatueBlockEntityRenderState : BlockEntityRenderState() {
    var rotation: Int = 0
    var activeTicks: Int = -1
}