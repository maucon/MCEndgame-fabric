package de.fuballer.mcendgame.main.component.item.custom

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.GoatHornItem
import net.minecraft.item.ItemStack
import net.minecraft.text.MutableText
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.world.World

abstract class UniqueAttributesHornItem(
    val settings: Settings,
) : GoatHornItem(settings), UniqueAttributesItemInterface {
    override fun getDefaultStack() = getRolledStack(this, true)

    override fun getName(stack: ItemStack): MutableText = super.getName(stack).copy().withColor(getNameColor())

    override fun use(world: World, user: PlayerEntity, hand: Hand): ActionResult {
        val result = super.use(world, user, hand)
        if (result == ActionResult.FAIL) return result

        onUse(world, user)
        return result
    }

    abstract fun onUse(world: World, user: PlayerEntity)
}