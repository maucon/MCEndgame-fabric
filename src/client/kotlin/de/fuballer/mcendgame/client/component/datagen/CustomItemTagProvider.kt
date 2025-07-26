package de.fuballer.mcendgame.client.component.datagen

import de.fuballer.mcendgame.main.component.item.custom.armor.CustomArmorItems
import de.fuballer.mcendgame.main.component.item.custom.tool.CustomToolItems
import de.fuballer.mcendgame.main.component.tags.CustomTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.item.Items
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.ItemTags
import java.util.concurrent.CompletableFuture

class CustomItemTagProvider(
    dataOutput: FabricDataOutput,
    registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>,
) : FabricTagProvider.ItemTagProvider(dataOutput, registryLookup) {
    override fun configure(wrapperLookup: RegistryWrapper.WrapperLookup) {
        getOrCreateTagBuilder(ItemTags.SWORDS)
            .add(CustomToolItems.TWINFIRE)
            .add(CustomToolItems.BLOODHARVEST)

        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR)
            .add(CustomArmorItems.DRUIDS_HELMET)
            .add(CustomArmorItems.ICEBORNE)
            .add(CustomArmorItems.SUEDE_HELMET)
            .add(CustomArmorItems.WITHER_ROSE_HELMET)
            .add(CustomArmorItems.EMBERCHANT)

        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR)
            .add(CustomArmorItems.BOUND_ABYSS)
            .add(CustomArmorItems.DRUIDS_CHESTPLATE)
            .add(CustomArmorItems.SUEDE_CHESTPLATE)
            .add(CustomArmorItems.WITHER_ROSE_CHESTPLATE)

        getOrCreateTagBuilder(ItemTags.LEG_ARMOR)
            .add(CustomArmorItems.LAMIAS_GIFT)
            .add(CustomArmorItems.DRUIDS_LEGGINGS)
            .add(CustomArmorItems.SUEDE_LEGGINGS)
            .add(CustomArmorItems.WITHER_ROSE_LEGGINGS)

        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
            .add(CustomArmorItems.DRUIDS_BOOTS)
            .add(CustomArmorItems.SUEDE_BOOTS)
            .add(CustomArmorItems.WITHER_ROSE_BOOTS)

        getOrCreateTagBuilder(CustomTags.BOW)
            .add(Items.BOW)
            .add(CustomToolItems.WINDSTRING)
            .add(CustomToolItems.HAILSTORM)

        getOrCreateTagBuilder(ItemTags.BOW_ENCHANTABLE)
            .forceAddTag(CustomTags.BOW)

        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
            .forceAddTag(CustomTags.BOW)

        getOrCreateTagBuilder(ItemTags.DYEABLE)
            .add(CustomArmorItems.SUEDE_HELMET)
            .add(CustomArmorItems.SUEDE_CHESTPLATE)
            .add(CustomArmorItems.SUEDE_LEGGINGS)
            .add(CustomArmorItems.SUEDE_BOOTS)

        getOrCreateTagBuilder(CustomTags.DIAMOND_GEAR)
            .add(Items.DIAMOND_HELMET)
            .add(Items.DIAMOND_CHESTPLATE)
            .add(Items.DIAMOND_LEGGINGS)
            .add(Items.DIAMOND_BOOTS)
            .add(Items.DIAMOND_SWORD)
            .add(Items.DIAMOND_PICKAXE)
            .add(Items.DIAMOND_AXE)
            .add(Items.DIAMOND_SHOVEL)
            .add(Items.DIAMOND_HOE)

        getOrCreateTagBuilder(CustomTags.NETHERITE_GEAR)
            .add(Items.NETHERITE_HELMET)
            .add(Items.NETHERITE_CHESTPLATE)
            .add(Items.NETHERITE_LEGGINGS)
            .add(Items.NETHERITE_BOOTS)
            .add(Items.NETHERITE_SWORD)
            .add(Items.NETHERITE_PICKAXE)
            .add(Items.NETHERITE_AXE)
            .add(Items.NETHERITE_SHOVEL)
            .add(Items.NETHERITE_HOE)

        getOrCreateTagBuilder(CustomTags.DUNGEON_DROP_DISABLED)
            .add(Items.TRIDENT)
            .add(Items.MACE)

        getOrCreateTagBuilder(CustomTags.DUNGEON_DISABLED)
            .forceAddTag(ItemTags.BOATS)
            .forceAddTag(ItemTags.EGGS)
            .add(Items.ENDER_PEARL)
            .add(Items.BUCKET)
            .add(Items.WATER_BUCKET)
            .add(Items.LAVA_BUCKET)
            .add(Items.POWDER_SNOW_BUCKET)
            .add(Items.COD_BUCKET)
            .add(Items.SALMON_BUCKET)
            .add(Items.TROPICAL_FISH_BUCKET)
            .add(Items.PUFFERFISH_BUCKET)
            .add(Items.TADPOLE_BUCKET)
            .add(Items.CHORUS_FRUIT)
            .add(Items.LEAD)
            .add(Items.FLINT_AND_STEEL)
            .add(Items.FIRE_CHARGE)
            .add(Items.FIREWORK_ROCKET)
            .add(Items.MINECART)
            .add(Items.HOPPER_MINECART)
            .add(Items.CHEST_MINECART)
            .add(Items.FURNACE_MINECART)
            .add(Items.TNT_MINECART)
            .add(Items.SNOWBALL)
            .add(Items.END_CRYSTAL)
            .add(Items.PAINTING)
            .add(Items.ITEM_FRAME)
            .add(Items.GLOW_ITEM_FRAME)
    }
}