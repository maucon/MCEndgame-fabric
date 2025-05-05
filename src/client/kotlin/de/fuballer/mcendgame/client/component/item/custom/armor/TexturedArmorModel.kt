package de.fuballer.mcendgame.client.component.item.custom.armor

import net.minecraft.client.model.Model
import net.minecraft.util.Identifier

data class TexturedArmorModel<T : Model>(
    val texture: Identifier,
    val model: T,
)