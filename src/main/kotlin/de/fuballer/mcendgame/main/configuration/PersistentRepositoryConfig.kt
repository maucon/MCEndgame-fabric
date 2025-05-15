package de.fuballer.mcendgame.main.configuration

import de.fuballer.mcendgame.main.MCEndgame
import de.maucon.mauconframework.di.annotation.Configuration
import de.maucon.mauconframework.di.annotation.Injectable
import net.fabricmc.loader.api.FabricLoader
import java.nio.file.Files
import java.nio.file.Path

private const val folderName = "repository"

@Configuration
object PersistentRepositoryConfig {
    @Injectable
    fun repositoryFolder(fabricLoader: FabricLoader): Path {
        val path = fabricLoader.gameDir
            .resolve(MCEndgame.MOD_ID)
            .resolve(folderName)

        return Files.createDirectories(path)
    }
}