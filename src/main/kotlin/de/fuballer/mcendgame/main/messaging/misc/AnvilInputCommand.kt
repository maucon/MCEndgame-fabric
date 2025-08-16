package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.item.ItemStack

data class AnvilInputCommand(
    val stack0: ItemStack,
    val stack1: ItemStack,
    var disable: Boolean = false,
)