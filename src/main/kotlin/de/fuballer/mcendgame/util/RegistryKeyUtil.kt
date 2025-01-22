package de.fuballer.mcendgame.util

import net.minecraft.component.ComponentType
import net.minecraft.item.Item
import net.minecraft.item.equipment.EquipmentAsset
import net.minecraft.item.equipment.EquipmentAssetKeys
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys

object RegistryKeyUtil {
    fun createItemKey(name: String): RegistryKey<Item> =
        RegistryKey.of(RegistryKeys.ITEM, IdentifierUtil.default(name))

    fun createDataComponentTypeKey(name: String): RegistryKey<ComponentType<*>> =
        RegistryKey.of(RegistryKeys.DATA_COMPONENT_TYPE, IdentifierUtil.default(name))

    fun createEquipmentAssetKey(name: String): RegistryKey<EquipmentAsset> =
        RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, IdentifierUtil.default(name))
}