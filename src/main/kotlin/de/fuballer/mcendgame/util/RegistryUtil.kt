package de.fuballer.mcendgame.util

import de.fuballer.mcendgame.components.item.custom.armor.CustomArmorMaterial
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.component.ComponentType
import net.minecraft.item.ArmorItem
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.item.equipment.EquipmentType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.resource.featuretoggle.FeatureSet
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.math.BlockPos

object RegistryUtil {
    fun registerItem(factory: (Item.Settings) -> Item, settings: Item.Settings, name: String): Item =
        Items.register(RegistryKeyUtil.createItemKey(name), factory, settings)

    fun registerBlock(factory: (AbstractBlock.Settings) -> Block, settings: AbstractBlock.Settings, name: String): Block =
        Blocks.register(RegistryKeyUtil.createBlockKey(name), factory, settings)
            .also { Items.register(it) }

    fun registerBlockEntityType(factory: (BlockPos, BlockState) -> BlockEntity, block: Block, name: String): BlockEntityType<*> =
        Registry.register(Registries.BLOCK_ENTITY_TYPE, IdentifierUtil.default(name), FabricBlockEntityTypeBuilder.create(factory, block).build())

    fun <T> registerDataComponentType(componentType: ComponentType<T>, name: String): ComponentType<T> =
        Registry.register(Registries.DATA_COMPONENT_TYPE, RegistryKeyUtil.createDataComponentTypeKey(name), componentType)

    fun registerArmorItem(material: CustomArmorMaterial, type: EquipmentType, name: String) =
        registerItem(
            { ArmorItem(material.instance, type, it) },
            Item.Settings().maxDamage(material.baseDurability),
            name
        )

    fun <T : ScreenHandler> registerScreenHandler(name: String, factory: ScreenHandlerType.Factory<T>): ScreenHandlerType<T> =
        Registry.register(Registries.SCREEN_HANDLER, IdentifierUtil.default(name), ScreenHandlerType(factory, FeatureSet.empty()))

    fun <T : ScreenHandler> registerScreenHandler(name: String, screenHandlerType: ScreenHandlerType<T>): ScreenHandlerType<T> =
        Registry.register(Registries.SCREEN_HANDLER, IdentifierUtil.default(name), screenHandlerType)
}