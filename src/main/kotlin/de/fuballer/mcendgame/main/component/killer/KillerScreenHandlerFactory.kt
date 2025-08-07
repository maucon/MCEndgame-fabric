package de.fuballer.mcendgame.main.component.killer

import de.fuballer.mcendgame.main.component.killer.networking.KillerEntityPayload
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.ScreenHandler
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text

class KillerScreenHandlerFactory(
    val payload: KillerEntityPayload,
    val title: Text,
    val handlerFactory: (Int, PlayerInventory, PlayerEntity) -> ScreenHandler,
) : ExtendedScreenHandlerFactory<KillerEntityPayload> {
    override fun getScreenOpeningData(player: ServerPlayerEntity) = payload

    override fun getDisplayName() = title

    override fun createMenu(
        syncId: Int,
        playerInventory: PlayerInventory,
        player: PlayerEntity,
    ) = handlerFactory(syncId, playerInventory, player)
}