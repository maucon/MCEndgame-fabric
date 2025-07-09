package de.fuballer.mcendgame.main.component.item.custom.armor.item.suede

import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.IntBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.custom_attribute.types.VanillaAttributeTypes
import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.HidePlayerModelPartArmor
import de.fuballer.mcendgame.main.component.item.custom.UniqueAttributesItem
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.entity.player.PlayerModelPart

class SuedeChestplate(
    settings: Settings,
) : UniqueAttributesItem(settings), HidePlayerModelPartArmor {
    override fun getCustomAttributes() = listOf(
        RollableCustomAttribute(VanillaAttributeTypes.INCREASED_SCALE, 0, DoubleBounds(-0.12, -0.06)),
        RollableCustomAttribute(CustomAttributeTypes.MAGIC_FIND, 0, IntBounds(3, 5)),
        RollableCustomAttribute(VanillaAttributeTypes.MAX_HEALTH, 0, DoubleBounds(-6.0, -3.0)),
        RollableCustomAttribute(CustomAttributeTypes.DODGE_PER_MAX_HEART_BELOW_TEN, 0, DoubleBounds(0.04, 0.06)),
    )

    override fun getAttributeModifierSlot() = AttributeModifierSlot.CHEST

    override val hiddenPlayerModelParts = listOf(
        PlayerModelPart.LEFT_SLEEVE,
        PlayerModelPart.RIGHT_SLEEVE,
    )
}