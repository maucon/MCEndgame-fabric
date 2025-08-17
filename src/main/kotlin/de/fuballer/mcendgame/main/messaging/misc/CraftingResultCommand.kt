package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.item.ItemStack
import net.minecraft.recipe.input.CraftingRecipeInput

data class CraftingResultCommand(
    val input: CraftingRecipeInput,
    var result: ItemStack,
)