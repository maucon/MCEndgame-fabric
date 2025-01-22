package de.fuballer.mcendgame.item.custom.armor

import net.minecraft.item.equipment.ArmorMaterial
import net.minecraft.item.equipment.EquipmentAsset
import net.minecraft.registry.RegistryKey

interface CustomArmorMaterial {
    val baseDurability: Int
    val registryKey: RegistryKey<EquipmentAsset>

    val instance: ArmorMaterial
}