package de.fuballer.mcendgame.components.dungeon.generation

import de.fuballer.mcendgame.components.dungeon.boss.BossGenerationService
import de.fuballer.mcendgame.components.dungeon.enemy.EnemyGenerationService
import de.fuballer.mcendgame.components.dungeon.generation.builder.DungeonBuilderService
import de.fuballer.mcendgame.components.dungeon.type.DungeonType
import de.fuballer.mcendgame.components.dungeon.world.DungeonWorldService
import de.fuballer.mcendgame.configuration.RuntimeConfig
import de.fuballer.mcendgame.framework.event.dungeon.DungeonGeneratedEvent
import de.fuballer.mcendgame.framework.event.dungeon.OpenDungeonButtonPressedEvent
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventGateway
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.server.world.ServerWorld
import kotlin.random.Random

@Injectable
class DungeonGenerationService(
    private val dungeonWorldService: DungeonWorldService,
    private val dungeonBuilderService: DungeonBuilderService,
    private val enemyGenerationService: EnemyGenerationService,
    private val bossGenerationService: BossGenerationService,
) {
    @EventSubscriber
    fun on(event: OpenDungeonButtonPressedEvent) {
        val player = event.player
        val originWorld = player.world as ServerWorld
        val dungeonDevicePos = event.blockEntity.pos

        val dungeonLevel = 1 // TODO from player
        val seed = Random.nextInt() // TODO player seed
        val random = Random(seed)

        val dungeonType = DungeonType.STRONGHOLD // TODO player dungeon type
        val (mapType, enemyTypes, bossTypes) = dungeonType.roll(random)
        val layoutGenerator = mapType.layoutGeneratorProvider()
        val layout = layoutGenerator.generateDungeon(random, dungeonLevel, dungeonType.bossCount)

        RuntimeConfig.SERVER.execute {
            val dungeonWorld = dungeonWorldService.create(player)
            dungeonBuilderService.build(dungeonWorld, layout.rooms)

            enemyGenerationService.generate(dungeonWorld, dungeonLevel, enemyTypes, layout.enemySpawnPos, random)
            bossGenerationService.generate(dungeonWorld, dungeonLevel, bossTypes, layout.bossSpawnPos, random)

            val dungeonGeneratedEvent = DungeonGeneratedEvent(originWorld, dungeonWorld, layout.spawnPos, dungeonDevicePos)
            EventGateway.launchPublish(dungeonGeneratedEvent)
        }
    }
}