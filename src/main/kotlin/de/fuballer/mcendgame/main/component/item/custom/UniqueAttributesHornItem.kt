package de.fuballer.mcendgame.main.component.item.custom

import de.fuballer.mcendgame.main.component.item.custom.misc.horn.command.HornUseCommand
import de.maucon.mauconframework.command.CommandGateway
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
    abstract val baseCooldown: Int // ticks

    override fun getDefaultStack() = getRolledStack(this, true)

    override fun getName(stack: ItemStack): MutableText = super.getName(stack).copy().withColor(getNameColor())

    override fun use(world: World, user: PlayerEntity, hand: Hand): ActionResult {
        val result = super.use(world, user, hand)
        if (result == ActionResult.FAIL) return result

        val command = HornUseCommand(user)
        val cmd = CommandGateway.apply(command)

        onUse(world, user, cmd)

        val itemStack = user.getStackInHand(hand)
        val cooldown = (baseCooldown * cmd.getCooldownFactor()).toInt()
        user.itemCooldownManager.set(itemStack, cooldown)

        return result
    }

    abstract fun onUse(world: World, user: PlayerEntity, cmd: HornUseCommand)
}