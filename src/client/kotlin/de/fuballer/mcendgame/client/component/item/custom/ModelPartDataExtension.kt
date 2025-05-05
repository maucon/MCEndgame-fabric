package de.fuballer.mcendgame.client.component.item.custom

import net.minecraft.client.model.ModelPartBuilder
import net.minecraft.client.model.ModelPartData
import net.minecraft.client.model.ModelTransform

object ModelPartDataExtension {
    fun ModelPartData.createEmptyChild(name: String): ModelPartData {
        return addChild(name, ModelPartBuilder.create(), ModelTransform.NONE)
    }
}