package de.fuballer.mcendgame.main.component.item.custom.totem

import de.fuballer.mcendgame.main.component.dungeon.loot.drop.ItemColor
import net.minecraft.text.MutableText
import net.minecraft.text.Text

private const val TRANSLATABLE_BASE_KEY = "item.mcendgame.totem.type."

enum class TotemType(
    private val id: String,
    val color: ItemColor,
) {
    BASIC("basic", ItemColor.TOTEM_BASIC),
    EFFECT("effect", ItemColor.TOTEM_EFFECT),
    ABILITY("ability", ItemColor.TOTEM_ABILITY);

    fun getLore(): MutableText = Text.translatable("$TRANSLATABLE_BASE_KEY$id")
}