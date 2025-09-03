package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.recipe.input.SmithingRecipeInput

data class CanSmithCommand(
    val input: SmithingRecipeInput,
    var canSmith: Boolean = true,
)