package de.fuballer.mcendgame.functional.item_tag

import de.fuballer.mcendgame.util.minecraft.CodecUtil

enum class ItemTag {
    CORRUPTED;

    companion object {
        val CODEC = CodecUtil.ofEnum(ItemTag::class.java)
    }
}