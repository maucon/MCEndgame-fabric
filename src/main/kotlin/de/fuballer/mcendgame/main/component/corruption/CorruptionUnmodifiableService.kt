package de.fuballer.mcendgame.main.component.corruption

import de.fuballer.mcendgame.main.component.corruption.CorruptionExtensions.isCorrupted
import de.fuballer.mcendgame.main.messaging.misc.CraftingRecipeCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.item.ItemStack

@Injectable
object CorruptionUnmodifiableService {
    @CommandHandler
    fun on(cmd: CraftingRecipeCommand) {
        val stacks = cmd.input.stacks
        if (stacks.none { it.isCorrupted() }) return
        cmd.result = ItemStack.EMPTY
    }
}