package de.fuballer.mcendgame.components.item.custom.armor

import net.minecraft.client.render.entity.state.EntityRenderState

interface Animated {
    fun animate(renderState: EntityRenderState)
}