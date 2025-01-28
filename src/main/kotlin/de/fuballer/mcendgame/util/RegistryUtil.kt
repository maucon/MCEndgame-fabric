package de.fuballer.mcendgame.util

import de.fuballer.mcendgame.components.item.custom.armor.CustomArmorMaterial
import net.minecraft.component.ComponentType
import net.minecraft.item.ArmorItem
import net.minecraft.item.Item
import net.minecraft.item.equipment.EquipmentType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey

object RegistryUtil {
    fun registerItem(item: Item, registryKey: RegistryKey<Item>): Item =
        Registry.register(Registries.ITEM, registryKey.value, item)

    fun <T> registerDataComponentType(
        componentType: ComponentType<T>,
        registryKey: RegistryKey<ComponentType<*>>
    ): ComponentType<T> =
        Registry.register(Registries.DATA_COMPONENT_TYPE, registryKey, componentType)

    fun registerArmorItem(material: CustomArmorMaterial, type: EquipmentType, registryKey: RegistryKey<Item>) =
        registerItem(
            ArmorItem(
                material.instance,
                type,
                Item.Settings().registryKey(registryKey)
                    .maxDamage(type.getMaxDamage(material.baseDurability))
            ), registryKey
        )
}