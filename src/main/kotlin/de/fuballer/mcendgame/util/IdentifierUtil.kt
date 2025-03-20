package de.fuballer.mcendgame.util

import de.fuballer.mcendgame.MCEndgame
import net.minecraft.util.Identifier
import java.util.*

object IdentifierUtil {
    fun default(id: String): Identifier {
        return Identifier.of(MCEndgame.MOD_ID, id)
    }

    fun defaultRandom(): Identifier {
        return Identifier.of(MCEndgame.MOD_ID, UUID.randomUUID().toString())
    }
}