package de.fuballer.mcendgame.main.messaging.dungeon

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem

data class DungeonPlayerIncreaseProgressCommand(
    val aspects: HashMap<AspectItem, Int>,
    var progressGranted: Int = 1,
)