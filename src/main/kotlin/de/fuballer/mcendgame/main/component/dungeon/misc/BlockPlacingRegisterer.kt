package de.fuballer.mcendgame.main.component.dungeon.misc

import de.fuballer.mcendgame.main.util.extension.WorldExtension.isDungeonWorld
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.minecraft.item.BlockItem
import net.minecraft.util.ActionResult

@Injectable
class BlockPlacingRegisterer {
    @Initializer
    fun init() {
        UseBlockCallback.EVENT.register { player, world, hand, _ ->
            if (!world.isDungeonWorld()) return@register ActionResult.PASS
            if (player.isCreative) return@register ActionResult.PASS

            val stack = player.getStackInHand(hand)
            if (stack.item is BlockItem) return@register ActionResult.FAIL

            ActionResult.PASS
        }
    }
}