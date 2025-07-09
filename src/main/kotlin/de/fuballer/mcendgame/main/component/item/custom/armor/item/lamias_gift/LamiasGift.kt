package de.fuballer.mcendgame.main.component.item.custom.armor.item.lamias_gift

import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.IntBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.HideBipedBoneArmor
import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.HideOtherArmorArmor
import de.fuballer.mcendgame.main.component.item.custom.UniqueAttributesItem
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

    override fun getAttributeModifierSlot() = AttributeModifierSlot.LEGS

    override fun getCustomAttributes() = listOf(
        getMiscAttribute(),
        getDefensiveAttribute(),
        getOffensiveAttribute(),
        RollableCustomAttribute(CustomAttributeTypes.POISON_DAMAGE_IMMUNITY, 0),
    )

    private fun getOffensiveAttribute() = listOf(
        RollableCustomAttribute(CustomAttributeTypes.INCREASED_DAMAGE_WHILE_POISONED, 0, DoubleBounds(0.05, 0.1)),
        RollableCustomAttribute(CustomAttributeTypes.INCREASED_ATTACK_DAMAGE_WHILE_POISONED, 0, DoubleBounds(0.1, 0.2)),
        RollableCustomAttribute(CustomAttributeTypes.INCREASED_ELEMENTAL_DAMAGE_WHILE_POISONED, 0, DoubleBounds(0.1, 0.2)),
    ).random()

    private fun getDefensiveAttribute() = listOf(
        RollableCustomAttribute(CustomAttributeTypes.REDUCED_DAMAGE_TAKEN_WHILE_POISONED, 0, DoubleBounds(0.05, 0.15)),
        RollableCustomAttribute(CustomAttributeTypes.DODGE_WHILE_POISONED, 0, DoubleBounds(0.05, 0.15)),
    ).random()

    private fun getMiscAttribute() = listOf(
        RollableCustomAttribute(CustomAttributeTypes.INCREASED_MOVEMENT_SPEED_WHILE_POISONED, 0, DoubleBounds(.15, .3)),
        RollableCustomAttribute(CustomAttributeTypes.MAGIC_FIND_WHILE_POISONED, 0, IntBounds(2, 5)),
    ).random()
}