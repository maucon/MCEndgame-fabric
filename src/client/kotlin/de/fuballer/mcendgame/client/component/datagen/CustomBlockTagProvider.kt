package de.fuballer.mcendgame.client.component.datagen

import de.fuballer.mcendgame.main.component.block.CustomBlocks
import de.fuballer.mcendgame.main.component.tags.CustomTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.block.Blocks
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.BlockTags
import java.util.concurrent.CompletableFuture

class CustomBlockTagProvider(
    dataOutput: FabricDataOutput,
    registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>,
) : FabricTagProvider.BlockTagProvider(dataOutput, registryLookup) {
    override fun configure(wrapperLookup: RegistryWrapper.WrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add(CustomBlocks.DUNGEON_DEVICE)
            .add(CustomBlocks.CRYSTAL_FORGE)
            .add(CustomBlocks.TOTEM_STATUE)

        getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT)
            .add(CustomBlocks.DECAYING_COBWEB)

        getOrCreateTagBuilder(CustomTags.DUNGEON_BREAKABLE)
            .forceAddTag(BlockTags.FLOWERS)
            .forceAddTag(BlockTags.CORALS)
            .forceAddTag(BlockTags.WALL_CORALS)
            .forceAddTag(BlockTags.SAPLINGS)
            .forceAddTag(BlockTags.CAVE_VINES)
            .add(Blocks.FIRE)
            .add(Blocks.SOUL_FIRE)
            .add(Blocks.COBWEB)
            .add(CustomBlocks.DECAYING_COBWEB)
            .add(Blocks.SHORT_GRASS)
            .add(Blocks.TALL_GRASS)
            .add(Blocks.SHORT_DRY_GRASS)
            .add(Blocks.TALL_DRY_GRASS)
            .add(Blocks.FERN)
            .add(Blocks.LARGE_FERN)
            .add(Blocks.SMALL_DRIPLEAF)
            .add(Blocks.BIG_DRIPLEAF)
            .add(Blocks.BIG_DRIPLEAF_STEM)
            .add(Blocks.WEEPING_VINES)
            .add(Blocks.WEEPING_VINES_PLANT)
            .add(Blocks.TWISTING_VINES)
            .add(Blocks.TWISTING_VINES_PLANT)
            .add(Blocks.SEAGRASS)
            .add(Blocks.TALL_SEAGRASS)
            .add(Blocks.KELP)
            .add(Blocks.KELP_PLANT)
            .add(Blocks.DEAD_TUBE_CORAL)
            .add(Blocks.DEAD_BRAIN_CORAL)
            .add(Blocks.DEAD_BUBBLE_CORAL)
            .add(Blocks.DEAD_FIRE_CORAL)
            .add(Blocks.DEAD_HORN_CORAL)
            .add(Blocks.DEAD_TUBE_CORAL_FAN)
            .add(Blocks.DEAD_BRAIN_CORAL_FAN)
            .add(Blocks.DEAD_BUBBLE_CORAL_FAN)
            .add(Blocks.DEAD_FIRE_CORAL_FAN)
            .add(Blocks.DEAD_HORN_CORAL_FAN)
            .add(Blocks.DEAD_TUBE_CORAL_WALL_FAN)
            .add(Blocks.DEAD_BRAIN_CORAL_WALL_FAN)
            .add(Blocks.DEAD_BUBBLE_CORAL_WALL_FAN)
            .add(Blocks.DEAD_FIRE_CORAL_WALL_FAN)
            .add(Blocks.DEAD_HORN_CORAL_WALL_FAN)
            .add(Blocks.SEA_PICKLE)
            .add(Blocks.SUGAR_CANE)
            .add(Blocks.SWEET_BERRY_BUSH)
            .add(Blocks.BROWN_MUSHROOM)
            .add(Blocks.RED_MUSHROOM)
            .add(Blocks.NETHER_WART)
            .add(Blocks.WARPED_FUNGUS)
            .add(Blocks.CRIMSON_FUNGUS)
            .add(Blocks.NETHER_SPROUTS)
            .add(Blocks.WARPED_ROOTS)
            .add(Blocks.CRIMSON_ROOTS)
            .add(Blocks.HANGING_ROOTS)
            .add(Blocks.LILY_PAD)
            .add(Blocks.BAMBOO_SAPLING)

        getOrCreateTagBuilder(CustomTags.PHASING_BLOCKING)
            .add(Blocks.BARRIER)
            .add(Blocks.BEDROCK)
    }
}