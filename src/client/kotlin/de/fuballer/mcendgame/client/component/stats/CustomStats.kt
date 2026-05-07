package de.fuballer.mcendgame.client.component.stats

import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.stat.StatFormatter
import net.minecraft.stat.Stats

@Injectable
object CustomStats {
    val TEST_STAT = IdentifierUtil.default("test_stat")

    val ALL = listOf(
        TEST_STAT
    )

    @Initializer
    fun register() {
        Registry.register(Registries.CUSTOM_STAT, TEST_STAT, TEST_STAT);
        Stats.CUSTOM.getOrCreateStat(TEST_STAT, StatFormatter.DEFAULT);
    }
}