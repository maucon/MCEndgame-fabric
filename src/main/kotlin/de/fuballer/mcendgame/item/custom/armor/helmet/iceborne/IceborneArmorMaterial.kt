package de.fuballer.mcendgame.item.custom.armor.helmet.iceborne

import de.fuballer.mcendgame.MCEndgame
import de.fuballer.mcendgame.item.custom.armor.CustomArmorMaterial
import net.minecraft.item.equipment.ArmorMaterial
import net.minecraft.item.equipment.EquipmentAsset
import net.minecraft.item.equipment.EquipmentAssetKeys
import net.minecraft.item.equipment.EquipmentType
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.tag.ItemTags
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Identifier

object IceborneArmorMaterial : CustomArmorMaterial {
    override val BASE_DURABILITY = 15
    override val REGISTRY_KEY: RegistryKey<EquipmentAsset> =
        RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(MCEndgame.MOD_ID, "iceborne"))

    override val INSTANCE = ArmorMaterial(
        BASE_DURABILITY,
        mapOf(
            EquipmentType.HELMET to 3,
        ),
        15,
        SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
        3.0f,
        0.1f,
        ItemTags.REPAIRS_NETHERITE_ARMOR,
        REGISTRY_KEY
    )
}