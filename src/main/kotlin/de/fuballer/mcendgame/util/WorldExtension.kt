package de.fuballer.mcendgame.util

import de.fuballer.mcendgame.MCEndgame
import de.fuballer.mcendgame.components.dungeon.world.DungeonWorldSettings
import net.minecraft.world.World

object WorldExtension {
    fun World.isDungeonWorld(): Boolean {
        if (registryKey.value.namespace != MCEndgame.MOD_ID) return false
        return registryKey.value.path.startsWith(DungeonWorldSettings.DUNGEON_WORLD_PREFIX)
    }
}