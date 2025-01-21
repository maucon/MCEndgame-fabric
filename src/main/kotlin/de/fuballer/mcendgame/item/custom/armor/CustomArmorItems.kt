package de.fuballer.mcendgame.item.custom.armor

import de.fuballer.mcendgame.MCEndgame
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

class CustomArmorItems {
    fun register(item: Item, registryKey: RegistryKey<Item>) =
        Registry.register(Registries.ITEM, registryKey.value, item)

    val ICEBORNE_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MCEndgame.MOD_ID, "iceborne"))
    val ICEBORNE = register(Item(Item.Settings().registryKey(ICEBORNE_KEY)), ICEBORNE_KEY)
}