package de.fuballer.mcendgame.components.dungeon.device

import de.fuballer.mcendgame.util.RegistryKeyUtil
import de.fuballer.mcendgame.util.RegistryUtil
import de.maucon.mauconframework.annotation.Injectable
import net.minecraft.item.Item

private val DUNGEON_DEVICE_KEY = RegistryKeyUtil.createItemKey("dungeon_device")

@Injectable
object DungeonDeviceBlockItem {
    val ITEM: Item =
        RegistryUtil.registerItem(
            Item(Item.Settings().registryKey(DUNGEON_DEVICE_KEY)),
            DUNGEON_DEVICE_KEY
        )
}