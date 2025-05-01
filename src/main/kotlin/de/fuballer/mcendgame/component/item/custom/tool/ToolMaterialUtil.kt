package de.fuballer.mcendgame.component.item.custom.tool

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.tag.TagKey

object ToolMaterialUtil {
    fun of(
        incorrectBlocksForDrops: TagKey<Block>,
        durability: Int,
        speed: Float,
        attackDamageBonus: Float,
        enchantmentValue: Int,
        repairItems: TagKey<Item>
    ) = ToolMaterial(incorrectBlocksForDrops, durability, speed, attackDamageBonus, enchantmentValue, repairItems)
}

