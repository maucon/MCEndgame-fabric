package de.fuballer.mcendgame.main.component.tags

import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey

@Injectable
object CustomTags {
    val DUNGEON_DISABLED: TagKey<Item> = createItemTag("dungeon_disabled")
    val DIAMOND_GEAR: TagKey<Item> = createItemTag("diamond_gear")
    val NETHERITE_GEAR: TagKey<Item> = createItemTag("netherite_gear")
    val BOW: TagKey<Item> = createItemTag("bow")
    val DUNGEON_DROP_DISABLED: TagKey<Item> = createItemTag("dungeon_drop_disabled")
    val CRYSTAL: TagKey<Item> = createItemTag("crystal")

    val DUNGEON_BREAKABLE: TagKey<Block> = createBlockTag("dungeon_breakable")

    private fun createItemTag(id: String) = TagKey.of(RegistryKeys.ITEM, IdentifierUtil.default(id))
    private fun createBlockTag(id: String) = TagKey.of(RegistryKeys.BLOCK, IdentifierUtil.default(id))
}