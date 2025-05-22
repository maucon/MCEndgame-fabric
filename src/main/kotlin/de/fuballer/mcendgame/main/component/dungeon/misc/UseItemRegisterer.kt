package de.fuballer.mcendgame.main.component.dungeon.misc

import de.fuballer.mcendgame.main.component.tags.CustomTags
import de.fuballer.mcendgame.main.util.extension.WorldExtension.isDungeonWorld
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.event.player.UseItemCallback
import net.minecraft.util.ActionResult

@Injectable
class UseItemRegisterer {
    @Initializer
    fun init() {
        UseItemCallback.EVENT.register { player, world, hand ->
            if (!world.isDungeonWorld()) return@register ActionResult.PASS
            if (player.isCreative) return@register ActionResult.PASS

            val stack = player.getStackInHand(hand)
            if (stack.isIn(CustomTags.DUNGEON_DISABLED)) return@register ActionResult.FAIL

            ActionResult.PASS
        }
    }
}