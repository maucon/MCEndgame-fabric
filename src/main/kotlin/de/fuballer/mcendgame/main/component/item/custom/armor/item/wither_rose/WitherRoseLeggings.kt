package de.fuballer.mcendgame.main.component.item.custom.armor.item.wither_rose

import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.types.VanillaAttributeTypes
import de.fuballer.mcendgame.main.component.item.custom.UniqueAttributesItem
import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.HidePlayerModelPartArmor
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.entity.player.PlayerModelPart

class WitherRoseLeggings(
    settings: Settings,
) : UniqueAttributesItem(settings), HidePlayerModelPartArmor {
    override val hiddenPlayerModelParts = listOf(
        PlayerModelPart.LEFT_PANTS_LEG,
        PlayerModelPart.RIGHT_PANTS_LEG
    )

    override fun getCustomAttributes() = listOf(
        RollableCustomAttribute(VanillaAttributeTypes.ARMOR, 0, DoubleBounds(1.0, 2.0))
    )

    override fun getAttributeModifierSlot() = AttributeModifierSlot.LEGS
}