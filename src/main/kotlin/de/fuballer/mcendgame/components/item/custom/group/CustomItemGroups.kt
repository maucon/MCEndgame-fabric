package de.fuballer.mcendgame.components.item.custom.group

import de.fuballer.mcendgame.components.block.CustomBlocks
import de.fuballer.mcendgame.components.item.custom.armor.CustomArmorItems
import de.fuballer.mcendgame.util.IdentifierUtil
import de.fuballer.mcendgame.util.RegistryUtil
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
    val CUSTOM_ARMOR_KEY = RegistryKey.of(Registries.ITEM_GROUP.key, IdentifierUtil.default("custom_armor"))
    val CUSTOM_ARMOR = RegistryUtil.registerItemGroup(
        CUSTOM_ARMOR_KEY, FabricItemGroup.builder()
            .icon({ ItemStack(CustomArmorItems.ICEBORNE) })
            .displayName(Text.translatable("itemGroup.custom_armor"))
    )

    val CUSTOM_BLOCKS_KEY = RegistryKey.of(Registries.ITEM_GROUP.key, IdentifierUtil.default("custom_blocks"))
    val CUSTOM_BLOCKS = RegistryUtil.registerItemGroup(
        CUSTOM_BLOCKS_KEY, FabricItemGroup.builder()
            .icon({ ItemStack(CustomBlocks.DUNGEON_DEVICE) })
            .displayName(Text.translatable("itemGroup.custom_blocks"))
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
        }
        ItemGroupEvents.modifyEntriesEvent(CUSTOM_BLOCKS_KEY).register { itemGroup ->
            itemGroup.add(CustomBlocks.DUNGEON_DEVICE)
            itemGroup.add(CustomBlocks.DECAYING_COBWEB)
        }
    }
}