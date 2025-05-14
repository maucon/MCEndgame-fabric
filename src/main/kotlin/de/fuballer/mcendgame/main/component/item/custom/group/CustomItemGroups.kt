package de.fuballer.mcendgame.main.component.item.custom.group

import de.fuballer.mcendgame.main.component.block.CustomBlocks
import de.fuballer.mcendgame.main.component.dungeon.device.DungeonDevice
import de.fuballer.mcendgame.main.component.item.custom.armor.CustomArmorItems
import de.fuballer.mcendgame.main.component.item.custom.tool.CustomToolItems
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import de.fuballer.mcendgame.main.util.minecraft.RegistryUtil
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text

@Injectable
object CustomItemGroups {
    val CUSTOM_ARMOR_KEY = RegistryKey.of(Registries.ITEM_GROUP.key, IdentifierUtil.default("armor"))
    val CUSTOM_ARMOR = RegistryUtil.registerItemGroup(
        CUSTOM_ARMOR_KEY, FabricItemGroup.builder()
            .icon { ItemStack(CustomArmorItems.ICEBORNE) }
            .displayName(Text.translatable("item_group.mcendgame.armor"))
    )

    val CUSTOM_TOOLS_KEY = RegistryKey.of(Registries.ITEM_GROUP.key, IdentifierUtil.default("tools"))
    val CUSTOM_TOOLS = RegistryUtil.registerItemGroup(
        CUSTOM_TOOLS_KEY, FabricItemGroup.builder()
            .icon { ItemStack(CustomToolItems.BLOODHARVEST) }
            .displayName(Text.translatable("item_group.mcendgame.tools"))
    )

    val CUSTOM_BLOCKS_KEY = RegistryKey.of(Registries.ITEM_GROUP.key, IdentifierUtil.default("blocks"))
    val CUSTOM_BLOCKS = RegistryUtil.registerItemGroup(
        CUSTOM_BLOCKS_KEY, FabricItemGroup.builder()
            .icon { ItemStack(DungeonDevice.BLOCK) }
            .displayName(Text.translatable("item_group.mcendgame.blocks"))
    )

    @Initializer
    fun init() {
        ItemGroupEvents.modifyEntriesEvent(CUSTOM_ARMOR_KEY).register { itemGroup ->
            itemGroup.add(CustomArmorItems.ICEBORNE.defaultStack)
            itemGroup.add(CustomArmorItems.BOUND_ABYSS.defaultStack)
            itemGroup.add(CustomArmorItems.DRUIDS_HELMET.defaultStack)
            itemGroup.add(CustomArmorItems.DRUIDS_CHESTPLATE.defaultStack)
            itemGroup.add(CustomArmorItems.DRUIDS_LEGGINGS.defaultStack)
            itemGroup.add(CustomArmorItems.DRUIDS_BOOTS.defaultStack)
            itemGroup.add(CustomArmorItems.EMBERCHANT.defaultStack)
            itemGroup.add(CustomArmorItems.LAMIAS_GIFT.defaultStack)
            itemGroup.add(CustomArmorItems.WITHER_ROSE_HELMET.defaultStack)
            itemGroup.add(CustomArmorItems.WITHER_ROSE_CHESTPLATE.defaultStack)
            itemGroup.add(CustomArmorItems.WITHER_ROSE_LEGGINGS.defaultStack)
            itemGroup.add(CustomArmorItems.WITHER_ROSE_BOOTS.defaultStack)
        }
        ItemGroupEvents.modifyEntriesEvent(CUSTOM_TOOLS_KEY).register { itemGroup ->
            itemGroup.add(CustomToolItems.BLOODHARVEST.defaultStack)
            itemGroup.add(CustomToolItems.TWINFIRE.defaultStack)
        }
        ItemGroupEvents.modifyEntriesEvent(CUSTOM_BLOCKS_KEY).register { itemGroup ->
            itemGroup.add(DungeonDevice.BLOCK)
            itemGroup.add(CustomBlocks.DECAYING_COBWEB)
        }
    }
}