package de.fuballer.mcendgame.main.component.item.custom.armor.item.lamias_gift

import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.HideBipedBoneArmor
import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.HideOtherArmorArmor
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.Item

class LamiasGift(
    settings: Settings,
) : Item(settings), HideBipedBoneArmor, HideOtherArmorArmor {
    override val hiddenBones = listOf(
        HideBipedBoneArmor.BipedBone.LEGS,
    )
    override val hiddenArmor = listOf(
        EquipmentSlot.FEET,
    )
}