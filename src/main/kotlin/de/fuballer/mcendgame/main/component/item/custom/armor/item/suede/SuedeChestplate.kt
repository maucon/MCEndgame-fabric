package de.fuballer.mcendgame.main.component.item.custom.armor.item.suede

import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.HidePlayerModelPartArmor
import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.UniqueAttributesItem
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.entity.player.PlayerModelPart

class SuedeChestplate(
    settings: Settings,
) : UniqueAttributesItem(settings), HidePlayerModelPartArmor {
    override fun getCustomAttributes() = listOf<RollableCustomAttribute>(
    )

    override fun getAttributeModifierSlot() = AttributeModifierSlot.CHEST

    override val hiddenPlayerModelParts = listOf(
        PlayerModelPart.LEFT_SLEEVE,
        PlayerModelPart.RIGHT_SLEEVE,
    )
}