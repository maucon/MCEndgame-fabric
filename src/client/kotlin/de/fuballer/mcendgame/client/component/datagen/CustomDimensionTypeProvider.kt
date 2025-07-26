package de.fuballer.mcendgame.client.component.datagen

import com.google.gson.JsonObject
import de.fuballer.mcendgame.main.MCEndgame
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.data.DataProvider
import net.minecraft.data.DataWriter
import java.util.concurrent.CompletableFuture

class CustomDimensionTypeProvider(
    val dataOutput: FabricDataOutput,
) : DataProvider {
    override fun getName() = "${MCEndgame.MOD_ID} Dimension Type Provider"

    override fun run(writer: DataWriter): CompletableFuture<*> {
        val dungeonJson = generateDimensionTypeJSON(
            hasRaids = false,
            hasSkylight = false,
            piglinSafe = true,
        )
        return DataProvider.writeToPath(writer, dungeonJson, getPath("dungeon"))
    }

    private fun generateDimensionTypeJSON(
        ambientLight: Float = 0F,
        bedWorks: Boolean = true,
        coordinateScale: Double = 1.0,
        effects: String = "minecraft:overworld",
        hasCeiling: Boolean = false,
        hasRaids: Boolean = true,
        hasSkylight: Boolean = true,
        height: Int = 384,
        infiniburn: String = "#minecraft:infiniburn_overworld",
        logicalHeight: Int = 384,
        minY: Int = -64,
        monsterSpawnBlockLightLimit: Int = 0,
        monsterSpawnLightLevelType: String = "minecraft:uniform",
        monsterSpawnLightLevelMaxInclusive: Int = 7,
        monsterSpawnLightLevelMinInclusive: Int = 0,
        natural: Boolean = true,
        piglinSafe: Boolean = false,
        respawnAnchorWorks: Boolean = false,
        ultrawarm: Boolean = false,
    ) = JsonObject().apply {
        addProperty("ambient_light", ambientLight)
        addProperty("bed_works", bedWorks)
        addProperty("coordinate_scale", coordinateScale)
        addProperty("effects", effects)
        addProperty("has_ceiling", hasCeiling)
        addProperty("has_raids", hasRaids)
        addProperty("has_skylight", hasSkylight)
        addProperty("height", height)
        addProperty("infiniburn", infiniburn)
        addProperty("logical_height", logicalHeight)
        addProperty("min_y", minY)
        addProperty("monster_spawn_block_light_limit", monsterSpawnBlockLightLimit)

        add("monster_spawn_light_level", JsonObject().apply {
            addProperty("type", monsterSpawnLightLevelType)
            addProperty("max_inclusive", monsterSpawnLightLevelMaxInclusive)
            addProperty("min_inclusive", monsterSpawnLightLevelMinInclusive)
        })

        addProperty("natural", natural)
        addProperty("piglin_safe", piglinSafe)
        addProperty("respawn_anchor_works", respawnAnchorWorks)
        addProperty("ultrawarm", ultrawarm)
    }

    private fun getPath(name: String) = dataOutput.path.resolve("data/${MCEndgame.MOD_ID}/dimension_type/$name.json")
}