package de.fuballer.mcendgame.components.dungeon.portal

import de.fuballer.mcendgame.components.portal.PortalEntity
import de.maucon.mauconframework.stereotype.Entity

class DungeonPortalEntity(
    override var id: Int,
    val portals: MutableList<PortalEntity>
) : Entity<Int>