package de.fuballer.mcendgame.util

import de.fuballer.mcendgame.MCEndgame
import net.minecraft.util.Identifier

object IdentifierUtil {
    fun default(id: String): Identifier {
        return Identifier.of(MCEndgame.MOD_ID, id)
    }
}