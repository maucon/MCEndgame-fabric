package de.fuballer.mcendgame.client.component.datagen

import de.fuballer.mcendgame.main.component.entity.custom.CustomEntities
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.EntityTypeTags
import java.util.concurrent.CompletableFuture

class CustomEntityTagProvider(
    dataOutput: FabricDataOutput,
    registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>,
) : FabricTagProvider.EntityTypeTagProvider(dataOutput, registriesFuture) {
    override fun configure(wrapperLookup: RegistryWrapper.WrapperLookup) {
        valueLookupBuilder(EntityTypeTags.ARTHROPOD)
            .add(CustomEntities.ARACHNE)

        valueLookupBuilder(EntityTypeTags.ZOMBIES)
            .add(CustomEntities.BONECRUSHER)
    }
}