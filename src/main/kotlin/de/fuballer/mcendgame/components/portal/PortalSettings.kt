package de.fuballer.mcendgame.components.portal

import net.minecraft.text.Text
import net.minecraft.util.Formatting

object PortalSettings {
    val TELEPORTATION_FAILED_MESSAGE: Text =
        Text.translatable("error.mcendgame.teleport.failed").formatted(Formatting.RED).formatted(Formatting.BOLD)

    const val DEFAULT_HITBOX_HEIGHT = 2.0f
    const val DEFAULT_HITBOX_WIDTH = 0.75f
}