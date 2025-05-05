package de.fuballer.mcendgame.main.functional.item_tag

import de.fuballer.mcendgame.main.util.minecraft.CodecUtil

enum class ItemTag {
    CORRUPTED;

    companion object {
        val CODEC = CodecUtil.ofEnum(ItemTag::class.java)
    }
}