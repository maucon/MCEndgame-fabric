package de.fuballer.mcendgame.main.component.custom_attribute.sign_based_keyword

import net.minecraft.text.Text

private const val LANGUAGE_KEY_PREFIX = "attribute.mcendgame."

enum class SignBasedKeyword(
    val positive: Text,
    val negative: Text,
) {
    INCREASED(Text.translatable("${LANGUAGE_KEY_PREFIX}increased"), Text.translatable("${LANGUAGE_KEY_PREFIX}reduced")),
    MORE(Text.translatable("${LANGUAGE_KEY_PREFIX}more"), Text.translatable("${LANGUAGE_KEY_PREFIX}less"));
}