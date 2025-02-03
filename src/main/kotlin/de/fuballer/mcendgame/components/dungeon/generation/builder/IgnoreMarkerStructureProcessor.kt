package de.fuballer.mcendgame.components.dungeon.generation.builder

import de.fuballer.mcendgame.components.dungeon.generation.DungeonGenerationSettings
import net.minecraft.structure.StructurePlacementData
import net.minecraft.structure.StructureTemplate
import net.minecraft.structure.processor.StructureProcessor
import net.minecraft.structure.processor.StructureProcessorType
import net.minecraft.util.math.BlockPos
import net.minecraft.world.WorldView

class IgnoreMarkerStructureProcessor : StructureProcessor() {
    override fun process(
        world: WorldView,
        pos: BlockPos,
        pivot: BlockPos,
        originalBlockInfo: StructureTemplate.StructureBlockInfo,
        currentBlockInfo: StructureTemplate.StructureBlockInfo,
        data: StructurePlacementData
    ): StructureTemplate.StructureBlockInfo? {
        return if (DungeonGenerationSettings.MARKER_BLOCKS.contains(originalBlockInfo.state.block)) {
            null
        } else {
            super.process(world, pos, pivot, originalBlockInfo, currentBlockInfo, data)
        }
    }

    override fun getType(): StructureProcessorType<*> = StructureProcessorType.BLOCK_IGNORE
}