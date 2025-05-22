package de.fuballer.mcendgame.main.component.dungeon.misc

import de.fuballer.mcendgame.main.component.tags.CustomTags
import de.fuballer.mcendgame.main.util.extension.WorldExtension.isDungeonWorld
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents

@Injectable
class BlockBreakingRegisterer {
    @Initializer
    fun init() {
        PlayerBlockBreakEvents.BEFORE.register { world, player, _, blockState, _ ->
            if (!world.isDungeonWorld()) return@register true
            if (player.isCreative) return@register true

            blockState.isIn(CustomTags.DUNGEON_BREAKABLE)
        }
    }
}