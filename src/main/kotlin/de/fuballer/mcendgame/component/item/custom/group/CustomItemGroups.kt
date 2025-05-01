package de.fuballer.mcendgame.component.item.custom.group

import de.fuballer.mcendgame.component.block.CustomBlocks
import de.fuballer.mcendgame.component.dungeon.device.DungeonDevice
import de.fuballer.mcendgame.component.item.custom.armor.CustomArmorItems
import de.fuballer.mcendgame.component.item.custom.tool.CustomToolItems
import de.fuballer.mcendgame.util.minecraft.IdentifierUtil
import de.fuballer.mcendgame.util.minecraft.RegistryUtil
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
            itemGroup.add(CustomArmorItems.ICEBORNE)
            itemGroup.add(CustomArmorItems.BOUND_ABYSS)
            itemGroup.add(CustomArmorItems.DRUIDS_HELMET)
            itemGroup.add(CustomArmorItems.DRUIDS_CHESTPLATE)
            itemGroup.add(CustomArmorItems.DRUIDS_LEGGINGS)
            itemGroup.add(CustomArmorItems.DRUIDS_BOOTS)
            itemGroup.add(CustomArmorItems.EMBERCHANT)
            itemGroup.add(CustomArmorItems.LAMIAS_GIFT)
        }
        ItemGroupEvents.modifyEntriesEvent(CUSTOM_TOOLS_KEY).register { itemGroup ->
            itemGroup.add(CustomToolItems.BLOODHARVEST)
            itemGroup.add(CustomToolItems.TWINFIRE)
        }
        ItemGroupEvents.modifyEntriesEvent(CUSTOM_BLOCKS_KEY).register { itemGroup ->
            itemGroup.add(DungeonDevice.BLOCK)
            itemGroup.add(CustomBlocks.DECAYING_COBWEB)
        }
    }
}