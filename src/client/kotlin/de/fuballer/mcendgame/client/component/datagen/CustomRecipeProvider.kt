package de.fuballer.mcendgame.client.component.datagen

import de.fuballer.mcendgame.main.component.block.CustomBlocks
import de.fuballer.mcendgame.main.component.tags.CustomTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.recipe.RecipeExporter
import net.minecraft.data.recipe.RecipeGenerator
import net.minecraft.item.Items
import net.minecraft.recipe.book.RecipeCategory
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.ItemTags
import java.util.concurrent.CompletableFuture

class CustomRecipeProvider(
    dataOutput: FabricDataOutput,
    registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>,
) : FabricRecipeProvider(dataOutput, registryLookup) {
    override fun getRecipeGenerator(
        registryLookup: RegistryWrapper.WrapperLookup,
        exporter: RecipeExporter,
    ) = object : RecipeGenerator(registryLookup, exporter) {
        override fun generate() {
            createShaped(RecipeCategory.MISC, CustomBlocks.DUNGEON_DEVICE.asItem())
                .pattern("ono")
                .pattern("nsn")
                .pattern("ono")
                .input('o', Items.OBSIDIAN)
                .input('n', Items.NETHERITE_INGOT)
                .input('s', Items.NETHER_STAR)
                .criterion(hasItem(Items.NETHER_STAR), conditionsFromItem(Items.NETHER_STAR))
                .offerTo(exporter)

            createShaped(RecipeCategory.MISC, CustomBlocks.CRYSTAL_FORGE.asItem())
                .pattern("c")
                .pattern("w")
                .pattern("a")
                .input('c', CustomTags.CRYSTAL)
                .input('w', ItemTags.WOOL_CARPETS)
                .input('a', ItemTags.ANVIL)
                .criterion("has_crystal", conditionsFromTag(CustomTags.CRYSTAL))
                .offerTo(exporter)
        }
    }

    override fun getName() = "MCEndgameRecipeProvider"
}