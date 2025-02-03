package de.fuballer.mcendgame.components.dungeon.generation

import net.minecraft.block.Block
import net.minecraft.block.Blocks

object DungeonGenerationSettings {
    val START_POS_MARKER: Block = Blocks.GREEN_WOOL
    val MONSTER_MARKER: Block = Blocks.WHITE_WOOL
    val BOSS_MARKER: Block = Blocks.DRAGON_HEAD
    val DOOR_MARKER: Block = Blocks.BLACK_WOOL
    val MISC_MARKER: Block = Blocks.YELLOW_WOOL

    val MARKER_BLOCKS = listOf(START_POS_MARKER, MONSTER_MARKER, BOSS_MARKER, DOOR_MARKER, MISC_MARKER)
}