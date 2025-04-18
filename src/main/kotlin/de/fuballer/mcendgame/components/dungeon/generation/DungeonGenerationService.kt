package de.fuballer.mcendgame.components.dungeon.generation

import de.fuballer.mcendgame.components.dungeon.boss.BossGenerationService
import de.fuballer.mcendgame.components.dungeon.enemy.EnemyGenerationService
import de.fuballer.mcendgame.components.dungeon.generation.builder.DungeonBuilderService
import de.fuballer.mcendgame.components.dungeon.generation.data.SpawnPosition
import de.fuballer.mcendgame.components.dungeon.type.DungeonType
import de.fuballer.mcendgame.components.dungeon.world.DungeonWorldService
import de.fuballer.mcendgame.components.portal.Portals
import de.fuballer.mcendgame.components.portal.teleport.TeleportLocation
import de.fuballer.mcendgame.components.portal.type.DefaultPortalType
import de.fuballer.mcendgame.event.DungeonOpenEvent
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import kotlin.random.Random

@Injectable
class DungeonGenerationService(
    private val dungeonWorldService: DungeonWorldService,
    private val dungeonBuilderService: DungeonBuilderService,
    private val enemyGenerationService: EnemyGenerationService,
    private val bossGenerationService: BossGenerationService,
) {
    @Initializer
    fun on() = DungeonOpenEvent.NOTIFIER.listen { event ->
        val player = event.player
        val generatedDungeonData = generate(player, BlockPos.ORIGIN)

        val serverWorld = player.world as? ServerWorld ?: return@listen

        val startPos = generatedDungeonData.startPos
        val teleportLocation = TeleportLocation(generatedDungeonData.world, Vec3d.of(startPos.pos), 0f, startPos.rot.toFloat())
        val portalType = DefaultPortalType() // todo get from player

        val portal = Portals.spawn(serverWorld, player.blockPos, Vec3d.ZERO, teleportLocation, portalType)
        // TODO FIXME PLEASE
    }

    private fun generate(
        player: PlayerEntity,
        leaveLocation: BlockPos // FIXME should be a location
    ): GeneratedDungeonData {
        val dungeonLevel = 1 // TODO from player
        val seed = Random.nextInt() // TODO player seed
        val world = dungeonWorldService.create(player)
        val random = Random(seed)
        val dungeonType = DungeonType.STRONGHOLD // TODO player dungeon type
        val (mapType, enemyTypes, bossTypes) = dungeonType.roll(random)

        val layoutGenerator = mapType.layoutGeneratorProvider()
        val layout = layoutGenerator.generateDungeon(random, dungeonLevel, dungeonType.bossCount)

        dungeonBuilderService.build(world, layout.rooms)

        // TODO apply event

        enemyGenerationService.generate(world, dungeonLevel, enemyTypes, layout.enemySpawnPos, random)
        bossGenerationService.generate(world, dungeonLevel, bossTypes, layout.bossSpawnPos, random)

        // TODO calculate startPos
        // val startLocation = layout.spawnPos.pos
        // TODO create portals

        return GeneratedDungeonData(world, layout.spawnPos)
    }

    data class GeneratedDungeonData(
        val world: ServerWorld,
        val startPos: SpawnPosition
    )
}