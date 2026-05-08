package de.fuballer.mcendgame.main.component.item.custom

import de.fuballer.mcendgame.main.component.item.custom.armor.materials.CustomArmorMaterial
import de.fuballer.mcendgame.main.util.minecraft.RegistryUtil
import net.minecraft.item.Item
import net.minecraft.item.equipment.EquipmentType

object UniqueItemRegistry {
    val ENTRIES: Map<String, Item>
        get() = _entries

    private val _entries = mutableMapOf<String, Item>()

    fun registerArmorItem(factory: (Item.Settings) -> Item, material: CustomArmorMaterial, type: EquipmentType, name: String): Item {
        val item = RegistryUtil.registerArmorItem(factory, material, type, name)
        _entries[name] = item
        return item
    }

    fun registerToolItem(factory: (Item.Settings) -> Item, settings: Item.Settings, name: String): Item {
        val item = RegistryUtil.registerItem(factory, settings, name)
        _entries[name] = item
        return item
    }

    fun registerMiscItem(factory: (Item.Settings) -> Item, settings: Item.Settings, name: String) = registerToolItem(factory, settings, name)
}