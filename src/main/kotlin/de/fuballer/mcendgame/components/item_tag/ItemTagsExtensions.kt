package de.fuballer.mcendgame.components.item_tag

import net.minecraft.item.ItemStack

object ItemTagsExtensions {
    fun ItemStack.addItemTag(itemTag: ItemTag) {
        val itemTags = get(ItemTagConfig.DATA_COMPONENT_TYPE) ?: mutableSetOf()
        itemTags.add(itemTag)

        set(ItemTagConfig.DATA_COMPONENT_TYPE, itemTags)
    }

    fun ItemStack.removeItemTag(itemTag: ItemTag) {
        val itemTags = get(ItemTagConfig.DATA_COMPONENT_TYPE) ?: return
        itemTags.remove(itemTag)

        set(ItemTagConfig.DATA_COMPONENT_TYPE, itemTags)
    }

    fun ItemStack.hasItemTag(itemTag: ItemTag): Boolean {
        val itemTags = get(ItemTagConfig.DATA_COMPONENT_TYPE) ?: return false
        return itemTags.contains(itemTag)
    }
}