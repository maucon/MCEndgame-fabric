package de.fuballer.mcendgame.util

import net.minecraft.component.ComponentType
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey

object RegistryUtil {
    fun registerItem(item: Item, registryKey: RegistryKey<Item>): Item =
        Registry.register(Registries.ITEM, registryKey.value, item)

    fun <T> registerDataComponentType(componentType: ComponentType<T>, registryKey: RegistryKey<ComponentType<*>>): ComponentType<T> =
        Registry.register(Registries.DATA_COMPONENT_TYPE, registryKey, componentType)
}