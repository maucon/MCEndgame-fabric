package de.fuballer.mcendgame.components.dungeon.portal

import de.fuballer.mcendgame.components.portal.PortalEntity
import de.fuballer.mcendgame.components.portal.teleport.TeleportLocation
import de.maucon.mauconframework.stereotype.Entity
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.World

class DungeonPortalEntity(
    override var id: Int,
    val dungeonWorld: ServerWorld,
    val leaveLocation: TeleportLocation,
    val portals: MutableList<PortalEntity>
) : Entity<Int>