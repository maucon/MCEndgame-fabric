package de.fuballer.mcendgame.components.dungeon.generation.data

import net.minecraft.util.math.Vec3i

data class RoomMarkerPoints(
    var startPos: Vec3i?,
    val monsterPos: MutableList<SpawnPosition>,
    val bossPos: MutableList<SpawnPosition>,
    val doors: MutableList<Door>,
) {
    fun add(markerPoints: RoomMarkerPoints) {
        if (startPos == null) {
            startPos = markerPoints.startPos
        }

        monsterPos.addAll(markerPoints.monsterPos)
        bossPos.addAll(markerPoints.bossPos)
        doors.addAll(markerPoints.doors)
    }
}
