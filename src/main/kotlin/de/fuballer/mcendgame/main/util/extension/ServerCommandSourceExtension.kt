package de.fuballer.mcendgame.main.util.extension

import net.minecraft.server.command.ServerCommandSource

object ServerCommandSourceExtension {
    fun ServerCommandSource.isOperator() = this.hasPermissionLevel(2)
}