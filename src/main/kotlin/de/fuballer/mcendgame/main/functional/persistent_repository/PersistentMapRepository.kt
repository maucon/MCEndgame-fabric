package de.fuballer.mcendgame.main.functional.persistent_repository

import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.mojang.serialization.Codec
import com.mojang.serialization.JsonOps
import de.fuballer.mcendgame.main.messaging.server.ServerStartedEvent
import de.fuballer.mcendgame.main.messaging.server.ServerStoppingEvent
import de.maucon.mauconframework.event.EventSubscriber
import de.maucon.mauconframework.initializer.Initializer
import de.maucon.mauconframework.stereotype.Entity
import de.maucon.mauconframework.stereotype.extension.InMemoryMapRepository
import org.slf4j.Logger
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import kotlin.jvm.optionals.getOrNull

open class PersistentMapRepository<ID, ENTITY : Entity<ID>>(
    private val log: Logger,
    private val name: String,
    private val codec: Codec<ENTITY>
) : InMemoryMapRepository<ID, ENTITY>() {
    private lateinit var filePath: Path

    @Initializer
    private fun initFilePath(folderPath: Path) {
        filePath = folderPath.resolve("$name.json")
    }

    @EventSubscriber
    private fun on(event: ServerStartedEvent) {
        loadFromFile()
    }

    @EventSubscriber
    private fun on(event: ServerStoppingEvent) {
        writeToFile()
    }

    private fun loadFromFile() {
        if (!Files.exists(filePath)) {
            log.warn("No file for persistent repository '$name' found")
            return
        }

        val content = Files.readString(filePath, StandardCharsets.UTF_8)
        val jsonArray = JsonParser.parseString(content).asJsonArray

        val entities = jsonArray
            .map { codec.parse(JsonOps.INSTANCE, it) }
            .map { it.result() }
            .mapNotNull { it.getOrNull() }

        saveAll(entities)
        log.info("Loaded ${entities.size} entities for persistent repository '$name'")
    }

    fun writeToFile() {
        val jsonArray = JsonArray()

        findAll()
            .map { codec.encodeStart(JsonOps.INSTANCE, it) }
            .map { it.result() }
            .mapNotNull { it.getOrNull() }
            .forEach { jsonArray.add(it) }

        Files.writeString(filePath, jsonArray.toString(), StandardCharsets.UTF_8)
        log.info("Saved ${jsonArray.size()} entities for persistent repository '$name'")
    }
}