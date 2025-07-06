package de.fuballer.mcendgame.main.component.item.custom

import de.fuballer.mcendgame.main.component.item.custom.armor.materials.CustomArmorMaterial
import de.fuballer.mcendgame.main.util.minecraft.RegistryUtil
import net.minecraft.item.Item
import net.minecraft.item.equipment.EquipmentType

object UniqueItemRegistry {
    val NAME_MAP = mutableMapOf<String, Item>()

    fun registerArmorItem(factory: (Item.Settings) -> Item, material: CustomArmorMaterial, type: EquipmentType, name: String): Item {
        val item = RegistryUtil.registerArmorItem(factory, material, type, name)
        NAME_MAP[name] = item
        return item
    }

    fun registerToolItem(factory: (Item.Settings) -> Item, settings: Item.Settings, name: String): Item {
        val item = RegistryUtil.registerItem(factory, settings, name)
        NAME_MAP[name] = item
        return item
    }
}