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
        var path = fabricLoader.configDir.resolve(MCEndgame.MOD_ID)
        path = path.resolve(folderName)
        return Files.createDirectories(path)
    }
}