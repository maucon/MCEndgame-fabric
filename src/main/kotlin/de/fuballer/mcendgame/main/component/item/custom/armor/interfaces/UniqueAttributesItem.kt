package de.fuballer.mcendgame.main.component.item.custom.armor.interfaces

import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute

interface UniqueAttributesItem {
    fun getCustomAttributes(): List<RollableCustomAttribute>
}