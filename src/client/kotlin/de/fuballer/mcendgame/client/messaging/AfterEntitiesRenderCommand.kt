package de.fuballer.mcendgame.client.messaging

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext

data class AfterEntitiesRenderCommand(
    val context: WorldRenderContext,
)