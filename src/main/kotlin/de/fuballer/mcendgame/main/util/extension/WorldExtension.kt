package de.fuballer.mcendgame.main.util.extension

import de.fuballer.mcendgame.main.MCEndgame
import de.fuballer.mcendgame.main.component.dungeon.world.DungeonWorldSettings
import net.minecraft.world.World

object WorldExtension {
    fun World.isDungeonWorld(): Boolean {
        if (registryKey.value.namespace != MCEndgame.MOD_ID) return false
        return registryKey.value.path.startsWith(DungeonWorldSettings.DUNGEON_WORLD_PREFIX)
    }
}