package de.fuballer.mcendgame.components.dungeon.portal

import de.fuballer.mcendgame.components.portal.PortalEntity
import de.fuballer.mcendgame.components.portal.Portals
import de.fuballer.mcendgame.components.portal.teleport.TeleportLocation
import de.fuballer.mcendgame.components.portal.type.DefaultPortalType
import de.fuballer.mcendgame.components.portal.type.PortalType
import de.fuballer.mcendgame.event.DungeonGeneratedEvent
import de.fuballer.mcendgame.event.OpenDungeonButtonPressedEvent
import de.fuballer.mcendgame.util.BlockPosExtension.toVec3d
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.Vec3d

@Injectable
class DungeonPortalService(
    private val dungeonPortalRepo: DungeonPortalRepository
) {
    @EventSubscriber
    fun on(event: DungeonGeneratedEvent) {
        val startPos = event.startPos
        val centeredSpawnPos = Vec3d.of(startPos.pos).add(0.5, 0.0, 0.5)
        val portalType = DefaultPortalType() // TODO get from player

        val deviceCenterPos = event.dungeonDevicePos.toVec3d().add(0.5, 0.0, 0.5)

        spawnLeavePortal(centeredSpawnPos, deviceCenterPos, portalType, event.originWorld, event.dungeonWorld)

        val deviceId = event.dungeonDevicePos.hashCode()
        val dungeonTeleportLocation = TeleportLocation(event.dungeonWorld, centeredSpawnPos, 0f, startPos.rot.toFloat())
        createEntryPortals(deviceId, deviceCenterPos, portalType, event.originWorld, dungeonTeleportLocation)
    }

    @EventSubscriber
    fun on(event: OpenDungeonButtonPressedEvent) {
        val id = event.blockEntity.pos.hashCode()
        val entity = dungeonPortalRepo.findById(id) ?: return
        clearPortals(entity)
    }

    // TODO on map device destroyed
    // TODO on dungeon boss killed
    // TODO close when dungeon world closed

    private fun spawnLeavePortal(
        spawnPos: Vec3d,
        devicePos: Vec3d,
        portalType: PortalType,
        originWorld: ServerWorld,
        dungeonWorld: ServerWorld
    ) {
        val teleportLocation = TeleportLocation(originWorld, devicePos.add(0.0, 1.0, 0.0))
        val portalLocation = spawnPos.subtract(0.5, 0.0, 0.0)

        Portals.spawn(dungeonWorld, portalLocation, teleportLocation, type = portalType, lookAt = spawnPos)
    }

    private fun createEntryPortals(
        deviceId: Int,
        devicePos: Vec3d,
        portalType: PortalType,
        originWorld: ServerWorld,
        dungeonTeleportLocation: TeleportLocation
    ) {
        val angle = Math.toRadians(360.0 / 6).toFloat()
        var offset = Vec3d(3.0, 0.0, 0.0)

        val portals = mutableListOf<PortalEntity>()
        repeat(6) {
            val portalPos = devicePos.add(offset)
            val portal = Portals.spawn(originWorld, portalPos, dungeonTeleportLocation, type = portalType, lookAt = devicePos, singleUse = true)

            portals.add(portal)
            offset = offset.rotateY(angle)
        }

        val entity = DungeonPortalEntity(deviceId, portals)
        dungeonPortalRepo.save(entity)
    }

    private fun clearPortals(dungeonPortalEntity: DungeonPortalEntity) {
        dungeonPortalEntity.portals.forEach { it.discard() }
    }
}