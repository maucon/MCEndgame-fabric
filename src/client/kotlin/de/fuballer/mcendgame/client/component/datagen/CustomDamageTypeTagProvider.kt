package de.fuballer.mcendgame.client.component.datagen

import de.fuballer.mcendgame.main.MCEndgame
import de.fuballer.mcendgame.main.component.damage.custom_type.CustomDamageTypes
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.entity.damage.DamageType
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.DamageTypeTags
import java.util.concurrent.CompletableFuture

class CustomDamageTypeTagProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricTagProvider<DamageType>(output, RegistryKeys.DAMAGE_TYPE, registriesFuture) {
    override fun getName() = "${MCEndgame.MOD_ID}DamageTypeTagProvider"

    override fun configure(arg: RegistryWrapper.WrapperLookup) {
        getOrCreateTagBuilder(DamageTypeTags.NO_KNOCKBACK)
            .addOptional(CustomDamageTypes.ELEMENTAL)
    }
}