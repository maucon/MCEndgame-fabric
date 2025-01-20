package de.fuballer.mcendgame.util

import de.fuballer.mcendgame.MCEndgame
import net.minecraft.component.ComponentType
import net.minecraft.item.Item
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

object RegistryKeyUtil {
    fun createItemKey(name: String): RegistryKey<Item> =
        RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MCEndgame.MOD_ID, name))

    fun createDataComponentTypeKey(name: String): RegistryKey<ComponentType<*>> =
        RegistryKey.of(RegistryKeys.DATA_COMPONENT_TYPE, Identifier.of(MCEndgame.MOD_ID, name))
}