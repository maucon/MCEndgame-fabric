package de.fuballer.mcendgame.main.component.dimension

import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.dimension.DimensionType

@Injectable
object CustomDimensions {
    val DUNGEON: RegistryKey<DimensionType> = of("dungeon")

    private fun of(id: String) = RegistryKey.of(RegistryKeys.DIMENSION_TYPE, IdentifierUtil.default(id))
}