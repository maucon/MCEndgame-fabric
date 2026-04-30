package de.fuballer.mcendgame.main.component.block.blocks.crystalforge

import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Colors

object CrystalForgeSettings {
    const val CONTAINER_BASE_KEY = "container.mcendgame.crystal_forge."
    const val FORGE_ERROR_KEY = "${CONTAINER_BASE_KEY}forge_error."

    fun getForgeErrorText(id: String): MutableText = Text.translatable("$FORGE_ERROR_KEY$id").withColor(Colors.RED)
}