package de.fuballer.mcendgame.main.component.item.custom.armor.item.wither_rose

import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.custom_attribute.types.VanillaAttributeTypes
import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.UniqueAttributesItem
import net.minecraft.component.type.AttributeModifierSlot

class WitherRoseChestplate(
    settings: Settings,
) : UniqueAttributesItem(settings) {
    override fun getCustomAttributes() = listOf(
        RollableCustomAttribute(VanillaAttributeTypes.INCREASED_SCALE, 0, DoubleBounds(0.05, 0.15)),
        RollableCustomAttribute(CustomAttributeTypes.ARMOR_WHILE_WITHERED, 0, DoubleBounds(3.0, 5.0)),
        RollableCustomAttribute(CustomAttributeTypes.INCREASED_ATTACK_DAMAGE_WHILE_WITHERED, 0, DoubleBounds(0.1, 0.2)),
        RollableCustomAttribute(CustomAttributeTypes.INCREASED_DAMAGE_WHILE_WITHERED, 0, DoubleBounds(0.05, 0.15)),
        RollableCustomAttribute(CustomAttributeTypes.WITHER_DAMAGE_IMMUNITY, 0),
    )

    override fun getAttributeModifierSlot() = AttributeModifierSlot.CHEST
}