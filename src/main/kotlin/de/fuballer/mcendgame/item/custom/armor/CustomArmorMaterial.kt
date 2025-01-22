package de.fuballer.mcendgame.item.custom.armor

import net.minecraft.item.equipment.ArmorMaterial
import net.minecraft.item.equipment.EquipmentAsset
import net.minecraft.registry.RegistryKey

interface CustomArmorMaterial {
    val BASE_DURABILITY: Int
    val REGISTRY_KEY: RegistryKey<EquipmentAsset>

    val INSTANCE: ArmorMaterial
}