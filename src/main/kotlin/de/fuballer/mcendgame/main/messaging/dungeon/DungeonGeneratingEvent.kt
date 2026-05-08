package de.fuballer.mcendgame.main.messaging.dungeon

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem
import net.minecraft.entity.player.PlayerEntity

/**
 * should not be used in a blocking way
 */
data class DungeonGeneratingEvent(
    val player: PlayerEntity,
    val affectingAspects: Map<AspectItem, Int>
)