package de.fuballer.mcendgame.components.item.equipment

import de.fuballer.mcendgame.components.custom_attributes.data.RollableCustomAttribute
import de.fuballer.mcendgame.components.item.equipment.armor.Boots
import de.fuballer.mcendgame.components.item.equipment.armor.Chestplate
import de.fuballer.mcendgame.components.item.equipment.armor.Helmet
import de.fuballer.mcendgame.components.item.equipment.armor.Leggings
import de.fuballer.mcendgame.components.item.equipment.enchantment.EquipmentEnchantment
import de.fuballer.mcendgame.components.item.equipment.tool.*
import de.fuballer.mcendgame.util.random.RandomOption
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.Item

interface Equipment {
    val item: Item
    val slot: EquipmentSlot

    val rollableEnchants: List<RandomOption<EquipmentEnchantment>>
    val rollableCustomAttribute: List<RandomOption<RollableCustomAttribute>>

    companion object {
        private val materialToEquipment = mutableMapOf<Item, Equipment>()
            .apply {
                putAll(Helmet.entries.associateBy { it.item })
                putAll(Chestplate.entries.associateBy { it.item })
                putAll(Leggings.entries.associateBy { it.item })
                putAll(Boots.entries.associateBy { it.item })
                putAll(Sword.entries.associateBy { it.item })
                putAll(Axe.entries.associateBy { it.item })
                putAll(Pickaxe.entries.associateBy { it.item })
                putAll(Shovel.entries.associateBy { it.item })
                putAll(Hoe.entries.associateBy { it.item })
                putAll(Miscellaneous.entries.associateBy { it.item })
            }

        fun fromItem(item: Item) = materialToEquipment[item]

        fun existsByMaterial(item: Item) = materialToEquipment.containsKey(item)
    }
}