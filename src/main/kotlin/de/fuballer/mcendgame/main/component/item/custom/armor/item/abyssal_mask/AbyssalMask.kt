package de.fuballer.mcendgame.main.component.item.custom.armor.item.abyssal_mask

import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.types.VanillaAttributeTypes
import de.fuballer.mcendgame.main.component.item.custom.UniqueAttributesItem
import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.HidePlayerModelPartArmor
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.entity.player.PlayerModelPart

class AbyssalMask(
    settings: Settings,
) : UniqueAttributesItem(settings), HidePlayerModelPartArmor {
    override fun getCustomAttributes() = listOf(
        RollableCustomAttribute(VanillaAttributeTypes.MAX_HEALTH, 0, DoubleBounds(3.0, 4.0)),
    )

    override fun getAttributeModifierSlot() = AttributeModifierSlot.HEAD

    override val hiddenPlayerModelParts = listOf(
        PlayerModelPart.HAT,
    )
}