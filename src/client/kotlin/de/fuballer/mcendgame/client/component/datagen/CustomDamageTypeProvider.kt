package de.fuballer.mcendgame.client.component.datagen

import com.google.gson.JsonObject
import de.fuballer.mcendgame.main.MCEndgame
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.data.DataProvider
import net.minecraft.data.DataWriter
import java.util.concurrent.CompletableFuture

class CustomDamageTypeProvider(
    val dataOutput: FabricDataOutput,
) : DataProvider {
    override fun getName() = "${MCEndgame.MOD_ID} Damage Type Provider"

    override fun run(writer: DataWriter): CompletableFuture<*>? {
        val sweepingJson = generateDamageTypeJSON("sweeping")
        return DataProvider.writeToPath(writer, sweepingJson, getPath("sweeping"))
    }

    private fun generateDamageTypeJSON(
        messageId: String,
        exhaustion: Double = 0.1,
        scaleWithDifficulty: String = "when_caused_by_living_non_player",
        deathMessageType: String = "default",
        soundEffects: String = "hurt",
    ) = JsonObject().apply {
        addProperty("message_id", messageId)
        addProperty("exhaustion", exhaustion)
        addProperty("scaling", scaleWithDifficulty)
        addProperty("death_message_type", deathMessageType)
        addProperty("effects", soundEffects)
    }

    private fun getPath(name: String) = dataOutput.path.resolve("data/${MCEndgame.MOD_ID}/damage_type/$name.json")
}