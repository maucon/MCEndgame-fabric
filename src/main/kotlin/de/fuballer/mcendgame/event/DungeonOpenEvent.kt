package de.fuballer.mcendgame.event

data class DungeonOpenEvent(
    val value: String
) {
    companion object {
        val NOTIFIER = Notifier<DungeonOpenEvent>()
    }
}