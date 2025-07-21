package de.fuballer.mcendgame.client.component.datagen

import de.fuballer.mcendgame.main.component.dungeon.device.DungeonDevice
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class CustomLootTableProvider(
    dataOutput: FabricDataOutput,
    registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>,
) : FabricBlockLootTableProvider(dataOutput, registryLookup) {
    override fun generate() {
        addDrop(DungeonDevice.BLOCK)
    }
}