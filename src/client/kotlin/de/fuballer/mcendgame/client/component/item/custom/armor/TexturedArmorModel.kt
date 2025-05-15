package de.fuballer.mcendgame.client.component.item.custom.armor

import net.minecraft.client.model.Model
import net.minecraft.util.Identifier

data class TexturedArmorModel<T : Model>(
    val model: T,
    val texture: Identifier,
    val translucentTexture: Identifier? = null,
)