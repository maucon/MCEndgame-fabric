package de.fuballer.mcendgame.components.item.custom.armor.helmet.iceborne

import de.fuballer.mcendgame.components.item.custom.armor.CustomArmorMaterial
import de.fuballer.mcendgame.util.RegistryKeyUtil
import net.minecraft.item.equipment.ArmorMaterial
import net.minecraft.item.equipment.EquipmentAsset
import net.minecraft.item.equipment.EquipmentType
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.tag.ItemTags
import net.minecraft.sound.SoundEvents

object IceborneArmorMaterial : CustomArmorMaterial {
    override val baseDurability = 15
    override val registryKey: RegistryKey<EquipmentAsset> = RegistryKeyUtil.createEquipmentAssetKey("iceborne")

    override val instance = ArmorMaterial(
        baseDurability,
        mapOf(
            EquipmentType.HELMET to 3,
        ),
        15,
        SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
        3.0f,
        0.1f,
        ItemTags.REPAIRS_NETHERITE_ARMOR,
        registryKey
    )
}