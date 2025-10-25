package de.fuballer.mcendgame.main.component.dungeon.generation

import de.fuballer.mcendgame.main.component.dungeon.enemy.EnemyGenerationService
import de.fuballer.mcendgame.main.component.dungeon.enemy.boss.BossGenerationService
import de.fuballer.mcendgame.main.component.dungeon.generation.builder.DungeonBuilderService
import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.DungeonEncounterGenerationService
import de.fuballer.mcendgame.main.component.dungeon.seed.DungeonSeedService
import de.fuballer.mcendgame.main.component.dungeon.world.DungeonWorldService
import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectService
import de.fuballer.mcendgame.main.configuration.RuntimeConfig
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonGenerateCommand
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonGeneratedEvent
import de.fuballer.mcendgame.main.messaging.dungeon.OpenDungeonButtonPressedEvent
import de.fuballer.mcendgame.main.util.extension.mixin.PlayerEntityMixinExtension.getDungeonLevel
import de.maucon.mauconframework.command.CommandGateway
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventGateway
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.server.world.ServerWorld
import kotlin.random.Random

@Injectable
class DungeonGenerationService(
    private val dungeonWorldService: DungeonWorldService,
    private val dungeonBuilderService: DungeonBuilderService,
    private val dungeonEncounterGenerationService: DungeonEncounterGenerationService,
    private val enemyGenerationService: EnemyGenerationService,
    private val bossGenerationService: BossGenerationService,
    private val dungeonSeedService: DungeonSeedService,
    private val aspectService: AspectService
) {
    @EventSubscriber
    fun on(event: OpenDungeonButtonPressedEvent) {
        val player = event.player
        val originWorld = player.world as ServerWorld
        val dungeonDevicePos = event.blockEntity.pos
        val affectingAspects = aspectService.getAffectingAspect(event.affectingItems)
        val playerSeed = dungeonSeedService.rollSeed(player)

        val playerDungeonLevel = player.getDungeonLevel().level
        val seed = playerSeed.seed
        val dungeonType = playerSeed.type

        val random = Random(seed)

        val (mapType, enemyTypes, bossTypes, applyMisc) = dungeonType.roll(random)

        val dungeonGenerateCommand = DungeonGenerateCommand(playerDungeonLevel, dungeonType.bossCount, affectingAspects)
        val (dungeonLevel, bossCount, _) = CommandGateway.apply(dungeonGenerateCommand)

        val layoutGenerator = mapType.layoutGeneratorProvider()
        val layout = layoutGenerator.generateDungeon(random, dungeonLevel, bossCount)

        RuntimeConfig.SERVER.execute {
            val dungeonWorld = dungeonWorldService.create(dungeonLevel, player, affectingAspects, dungeonType)

            dungeonBuilderService.build(dungeonWorld, layout.rooms)
            dungeonEncounterGenerationService.generate(dungeonWorld, dungeonLevel, layout.encounterPos, affectingAspects, random)

            enemyGenerationService.generate(dungeonWorld, dungeonLevel, enemyTypes, applyMisc, layout.enemySpawnPos)
            bossGenerationService.generate(dungeonWorld, dungeonLevel, bossTypes, applyMisc, layout.bossSpawnPos)

            val dungeonGeneratedEvent = DungeonGeneratedEvent(originWorld, dungeonWorld, layout.spawnPos, dungeonDevicePos)
            EventGateway.launchPublish(dungeonGeneratedEvent)
        }
    }
}