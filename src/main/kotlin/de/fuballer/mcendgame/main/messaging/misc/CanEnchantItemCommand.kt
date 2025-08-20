package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.item.ItemStack

data class CanEnchantItemCommand(
    val itemStack: ItemStack,
    var canEnchant: Boolean = true,
)