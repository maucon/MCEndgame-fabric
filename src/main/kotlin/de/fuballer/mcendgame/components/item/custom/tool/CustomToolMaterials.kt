package de.fuballer.mcendgame.components.item.custom.tool

import net.minecraft.item.ToolMaterial
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.ItemTags

object CustomToolMaterials {
    val BLOODHARVEST = ToolMaterial(
        BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
        2031, // durability
        0.0F, // attack damage
        0.0F, // attack speed
        22, // enchantAbility
        ItemTags.REPAIRS_NETHERITE_ARMOR,
    )
    val TWINFIRE = ToolMaterial(
        BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
        2031, // durability
        0.0F, // attack damage
        0.0F, // attack speed
        22, // enchantAbility
        ItemTags.REPAIRS_NETHERITE_ARMOR,
    )
}