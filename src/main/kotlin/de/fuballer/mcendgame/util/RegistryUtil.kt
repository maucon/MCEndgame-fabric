package de.fuballer.mcendgame.util

import de.fuballer.mcendgame.item.custom.armor.CustomArmorMaterial
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.component.ComponentType
import net.minecraft.item.ArmorItem
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.item.equipment.EquipmentType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry

object RegistryUtil {
    fun registerItem(factory: (Item.Settings) -> Item, settings: Item.Settings, name: String): Item =
        Items.register(RegistryKeyUtil.createItemKey(name), factory, settings)

    fun registerBlock(factory: (AbstractBlock.Settings) -> Block, settings: AbstractBlock.Settings, name: String): Block =
        Blocks.register(RegistryKeyUtil.createBlockKey(name), factory, settings)
            .also { Items.register(it) }

    fun <T> registerDataComponentType(componentType: ComponentType<T>, name: String): ComponentType<T> =
        Registry.register(Registries.DATA_COMPONENT_TYPE, RegistryKeyUtil.createDataComponentTypeKey(name), componentType)

    fun registerArmorItem(material: CustomArmorMaterial, type: EquipmentType, name: String) =
        registerItem(
            { ArmorItem(material.instance, type, it) },
            Item.Settings().maxDamage(material.baseDurability),
            name
        )
}