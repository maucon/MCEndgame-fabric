package de.fuballer.mcendgame.main.component.item.custom.armor.item.bound_abyss

import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.custom_attribute.types.VanillaAttributeTypes
import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.UniqueAttributesItem
import net.minecraft.item.Item

class BoundAbyss(
    settings: Settings,
) : Item(settings), UniqueAttributesItem {
    override fun getCustomAttributes() = listOf(
        RollableCustomAttribute(VanillaAttributeTypes.MAX_HEALTH, 1, DoubleBounds(4.0, 5.0)),
        RollableCustomAttribute(CustomAttributeTypes.INCREASED_DAMAGE_WHILE_LOW_LIFE, 1, DoubleBounds(.15, .25)),
    )
}