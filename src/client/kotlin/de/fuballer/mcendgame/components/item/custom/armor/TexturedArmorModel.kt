package de.fuballer.mcendgame.components.item.custom.armor

import net.minecraft.client.model.Model
import net.minecraft.util.Identifier

data class TexturedArmorModel<T : Model>(
    val TEXTURE: Identifier,
    val MODEL: T,
)