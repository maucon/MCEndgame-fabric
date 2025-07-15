package de.fuballer.mcendgame.main.component.dungeon.portal

import de.fuballer.mcendgame.main.component.portal.PortalEntity
import de.fuballer.mcendgame.main.component.portal.Portals
import de.fuballer.mcendgame.main.component.portal.teleport.TeleportLocation
import de.fuballer.mcendgame.main.component.portal.type.DefaultPortalType
import de.fuballer.mcendgame.main.component.portal.type.PortalType
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonBossDeathEvent
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonGeneratedEvent
import de.fuballer.mcendgame.main.messaging.dungeon.OpenDungeonButtonPressedEvent
import de.fuballer.mcendgame.main.util.extension.BlockPosExtension.toVec3d
import de.fuballer.mcendgame.main.util.extension.Vec3iExtension.toCenter
import de.fuballer.mcendgame.main.util.extension.WorldExtension.isDungeonWorld
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

        val dungeonWorld = event.dungeonWorld
        val serverWorld = dungeonWorld.world
        val leaveLocation = TeleportLocation(event.originWorld, deviceCenterPos.add(0.0, 1.0, 0.0))
        spawnLeavePortal(leaveLocation, centeredSpawnPos, portalType, serverWorld)

        val deviceId = event.dungeonDevicePos.hashCode()
        val dungeonTeleportLocation = TeleportLocation(serverWorld, centeredSpawnPos, 0f, startPos.rot.toFloat())
        val portals = createEntryPortals(deviceCenterPos, portalType, event.originWorld, dungeonTeleportLocation)

        val entity = DungeonPortalEntity(deviceId, serverWorld, leaveLocation, portals)
        dungeonPortalRepo.save(entity)
    }

    @EventSubscriber
    fun on(event: OpenDungeonButtonPressedEvent) {
        val id = event.blockEntity.pos.hashCode()
        val entity = dungeonPortalRepo.findById(id) ?: return
        clearPortals(entity)
    }

    @EventSubscriber
    fun on(event: DungeonBossDeathEvent) {
        if (event.isClient) return

        val world = event.world as ServerWorld
        if (!event.world.isDungeonWorld()) return

        val spawnPosition = event.bossEntity.spawnPosition
        val dungeonPortalEntity = dungeonPortalRepo.findByDungeonWorld(world) ?: return

        Portals.spawn(world, spawnPosition.pos.toCenter(), dungeonPortalEntity.leaveLocation, rotation = spawnPosition.rot.toFloat())
    }

    private fun spawnLeavePortal(
        leaveLocation: TeleportLocation,
        spawnPos: Vec3d,
        portalType: PortalType,
        dungeonWorld: ServerWorld
    ) {
        val portalLocation = spawnPos.subtract(0.5, 0.0, 0.0)

        Portals.spawn(dungeonWorld, portalLocation, leaveLocation, type = portalType, lookAt = spawnPos)
    }

    private fun createEntryPortals(
        devicePos: Vec3d,
        portalType: PortalType,
        originWorld: ServerWorld,
        dungeonTeleportLocation: TeleportLocation
    ): MutableList<PortalEntity> {
        val angle = Math.toRadians(360.0 / 6).toFloat()
        var offset = Vec3d(3.0, 0.0, 0.0)

        val portals = mutableListOf<PortalEntity>()
        repeat(6) {
            val portalPos = devicePos.add(offset)
            val portal = Portals.spawn(originWorld, portalPos, dungeonTeleportLocation, type = portalType, lookAt = devicePos, singleUse = true)

            portals.add(portal)
            offset = offset.rotateY(angle)
        }

        return portals
    }

    private fun clearPortals(dungeonPortalEntity: DungeonPortalEntity) {
        dungeonPortalEntity.portals
            .onEach { it.discard() }
            .clear()
    }
}