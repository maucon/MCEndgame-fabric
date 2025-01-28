package de.fuballer.mcendgame.components.dungeon.generation

import de.fuballer.mcendgame.components.dungeon.generation.builder.DungeonBuilderService
import de.fuballer.mcendgame.components.dungeon.type.DungeonType
import de.fuballer.mcendgame.components.dungeon.world.DungeonWorldService
import de.fuballer.mcendgame.event.DungeonOpenEvent
import de.maucon.mauconframework.annotation.Initialize
import de.maucon.mauconframework.annotation.Injectable
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import kotlin.random.Random

@Injectable
class DungeonGenerationService(
    private val dungeonWorldService: DungeonWorldService,
    private val dungeonBuilderService: DungeonBuilderService
) {
    @Initialize
    fun on() = DungeonOpenEvent.NOTIFIER.listen { event ->
        val dungeonWorld = generate(event.player, BlockPos.ORIGIN) // FIXME

        event.player.teleport(dungeonWorld, 0.0, 14.0, 0.0, setOf(), 0.0f, 0.0f, false)
    }

    private fun generate(
        player: PlayerEntity,
        leaveLocation: BlockPos // FIXME should be a location
    ): ServerWorld { // FIXME should return start location
        val dungeonLevel = 1 // TODO from palyer
        val seed = Random.nextInt() // TODO player seed
        val world = dungeonWorldService.create(player)
        val random = Random(seed)
        val dungeonType = DungeonType.STRONGHOLD // TODO player dungeon type
        val (mapType) = dungeonType.roll(random)

        val layoutGenerator = mapType.layoutGeneratorProvider()
        val layout = layoutGenerator.generateDungeon(random, dungeonLevel)

        dungeonBuilderService.build(world, layout.rooms)

        // TODO apply event

        // generateEnemies(layout, random, world, mapTier, entityTypes)
        // generateBosses(layout, world, mapTier, bossEntityTypes, leaveLocation)

        // TODO calculate startPos
        // val startLocation = layout.spawnPos.pos
        // TODO create portals

        return world // FIXME
    }
}