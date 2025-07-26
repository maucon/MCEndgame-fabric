package de.fuballer.mcendgame.main.util.extension

import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.entity.EquipmentSlot

object SlotExtension {
    fun EquipmentSlot.toAttributeModifierSlot() = when (this) {
        EquipmentSlot.MAINHAND -> AttributeModifierSlot.MAINHAND
        EquipmentSlot.OFFHAND -> AttributeModifierSlot.OFFHAND
        EquipmentSlot.FEET -> AttributeModifierSlot.FEET
        EquipmentSlot.LEGS -> AttributeModifierSlot.LEGS
        EquipmentSlot.CHEST -> AttributeModifierSlot.CHEST
        EquipmentSlot.HEAD -> AttributeModifierSlot.HEAD
        EquipmentSlot.BODY -> AttributeModifierSlot.BODY
        EquipmentSlot.SADDLE -> AttributeModifierSlot.SADDLE
    }

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