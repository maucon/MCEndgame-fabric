package de.fuballer.mcendgame.components.dungeon.portal

import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.stereotype.extension.InMemoryMapRepository
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.World

@Injectable
class DungeonPortalRepository : InMemoryMapRepository<Int, DungeonPortalEntity>() {
    fun findByDungeonWorld(world: ServerWorld): DungeonPortalEntity? =
        findAll().find { it.dungeonWorld == world }
}