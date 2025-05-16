package de.fuballer.mcendgame.main.component.item.custom.armor.item.lamias_gift

import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.HideBipedBoneArmor
import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.HideOtherArmorArmor
import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.UniqueAttributesItem
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.entity.EquipmentSlot

class LamiasGift(
    settings: Settings,
) : UniqueAttributesItem(settings), HideBipedBoneArmor, HideOtherArmorArmor {
    override val hiddenBones = listOf(
        HideBipedBoneArmor.BipedBone.LEGS,
    )
    override val hiddenArmor = listOf(
        EquipmentSlot.FEET,
    )

    override fun getCustomAttributes() = listOf(
        RollableCustomAttribute(CustomAttributeTypes.INCREASED_MOVEMENT_SPEED_WHILE_POISONED, 0, DoubleBounds(.15, .3)),
        RollableCustomAttribute(CustomAttributeTypes.POISON_DAMAGE_IMMUNITY, 0),
    )

    override fun getAttributeModifierSlot() = AttributeModifierSlot.LEGS
}