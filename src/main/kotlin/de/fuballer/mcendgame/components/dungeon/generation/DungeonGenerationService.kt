package de.fuballer.mcendgame.components.dungeon.generation

import de.fuballer.mcendgame.components.dungeon.generation.builder.DungeonBuilderService
import de.fuballer.mcendgame.components.dungeon.generation.enemy.EnemyGenerationService
import de.fuballer.mcendgame.components.dungeon.type.DungeonType
import de.fuballer.mcendgame.components.dungeon.world.DungeonWorldService
import de.fuballer.mcendgame.event.DungeonOpenEvent
import de.maucon.mauconframework.initializer.Initializer
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import kotlin.random.Random

@Injectable
class DungeonGenerationService(
    private val dungeonWorldService: DungeonWorldService,
    private val dungeonBuilderService: DungeonBuilderService,
    private val enemyGenerationService: EnemyGenerationService,
) {
    @Initializer
    fun on() = DungeonOpenEvent.NOTIFIER.listen { event ->
        val dungeonWorld = generate(event.player, BlockPos.ORIGIN) // FIXME
    }

    private fun generate(
        player: PlayerEntity,
        leaveLocation: BlockPos // FIXME should be a location
    ): ServerWorld { // FIXME should return start location
        val dungeonLevel = 1 // TODO from player
        val seed = Random.nextInt() // TODO player seed
        val world = dungeonWorldService.create(player)
        val random = Random(seed)
        val dungeonType = DungeonType.STRONGHOLD // TODO player dungeon type
        val (mapType, enemyTypes) = dungeonType.roll(random)

        val layoutGenerator = mapType.layoutGeneratorProvider()
        val layout = layoutGenerator.generateDungeon(random, dungeonLevel)

        dungeonBuilderService.build(world, layout.rooms)

        // TODO apply event

        enemyGenerationService.generate(world, dungeonLevel, enemyTypes, layout.enemySpawnPos, random)
        // generateBosses(layout, world, mapTier, bossEntityTypes, leaveLocation)

        // TODO calculate startPos
        // val startLocation = layout.spawnPos.pos
        // TODO create portals

        val (pos, rot) = layout.spawnPos
        player.teleport(world, pos.x + 0.5, pos.y.toDouble(), pos.z + 0.5, setOf(), rot.toFloat(), 0.0f, false)

        return world // FIXME
    }
}