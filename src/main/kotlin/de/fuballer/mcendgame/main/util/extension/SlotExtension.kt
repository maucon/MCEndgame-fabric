package de.fuballer.mcendgame.main.util.extension

import net.minecraft.component.type.AttributeModifierSlot

object SlotExtension {
    fun AttributeModifierSlot.isOrIsChildOf(other: AttributeModifierSlot): Boolean {
        if (this == other) return true
        if (other == AttributeModifierSlot.ANY) return true

        if (other == AttributeModifierSlot.HAND &&
            (this == AttributeModifierSlot.MAINHAND
                    || this == AttributeModifierSlot.OFFHAND)
        ) return true

        if (other == AttributeModifierSlot.ARMOR &&
            (this == AttributeModifierSlot.HEAD
                    || this == AttributeModifierSlot.CHEST
                    || this == AttributeModifierSlot.LEGS
                    || this == AttributeModifierSlot.FEET)
        ) return true

        return false
    }
}