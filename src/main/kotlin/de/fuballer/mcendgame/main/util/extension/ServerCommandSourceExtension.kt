package de.fuballer.mcendgame.main.util.extension

import net.minecraft.command.DefaultPermissions
import net.minecraft.server.command.ServerCommandSource

object ServerCommandSourceExtension {
    fun ServerCommandSource.isModerator() = permissions.hasPermission(DefaultPermissions.MODERATORS)
}