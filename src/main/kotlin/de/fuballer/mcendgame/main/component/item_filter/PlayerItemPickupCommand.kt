package de.fuballer.mcendgame.main.component.item_filter

import de.maucon.mauconframework.command.cancellable.CancellableCommand
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item

data class PlayerItemPickupCommand(
    val player: PlayerEntity,
    val item: Item,
) : CancellableCommand()