package de.fuballer.mcendgame.main.component.stats

import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.stat.StatFormatter
import net.minecraft.stat.Stats
import net.minecraft.util.Identifier

object CustomStatsRegistry {
    val ENTRIES: List<Identifier>
        get() = _entries

    private val _entries = mutableListOf<Identifier>()

    fun register(name: String, formatter: StatFormatter): Identifier {
        val identifier = IdentifierUtil.default(name)

        Registry.register(Registries.CUSTOM_STAT, identifier, identifier)
        Stats.CUSTOM.getOrCreateStat(identifier, formatter)
        _entries.add(identifier)

        return identifier
    }
}