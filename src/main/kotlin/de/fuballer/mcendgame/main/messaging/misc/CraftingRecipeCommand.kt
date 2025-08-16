package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.inventory.RecipeInputInventory
import net.minecraft.item.ItemStack

data class CraftingRecipeCommand(
    val craftingInventory: RecipeInputInventory,
    var result: ItemStack,
)