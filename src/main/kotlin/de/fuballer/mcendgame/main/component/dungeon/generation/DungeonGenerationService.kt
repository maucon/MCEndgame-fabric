package de.fuballer.mcendgame.main.component.dungeon.generation

import de.fuballer.mcendgame.main.component.dungeon.enemy.boss.BossGenerationService
import de.fuballer.mcendgame.main.component.dungeon.enemy.EnemyGenerationService
import de.fuballer.mcendgame.main.component.dungeon.generation.builder.DungeonBuilderService
import de.fuballer.mcendgame.main.component.dungeon.level.DungeonLevelService
import de.fuballer.mcendgame.main.component.dungeon.type.DungeonType
import de.fuballer.mcendgame.main.component.dungeon.world.DungeonWorldService
import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem
import de.fuballer.mcendgame.main.configuration.RuntimeConfig
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonGenerateCommand
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonGeneratedEvent
import de.fuballer.mcendgame.main.messaging.dungeon.OpenDungeonButtonPressedEvent
import de.maucon.mauconframework.command.CommandGateway
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventGateway
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.collection.DefaultedList
import kotlin.random.Random

@Injectable
class DungeonGenerationService(
    private val dungeonWorldService: DungeonWorldService,
    private val dungeonBuilderService: DungeonBuilderService,
    private val enemyGenerationService: EnemyGenerationService,
    private val bossGenerationService: BossGenerationService,
    private val dungeonLevelService: DungeonLevelService
) {
    @EventSubscriber
    fun on(event: OpenDungeonButtonPressedEvent) {
        val player = event.player
        val originWorld = player.world as ServerWorld
        val dungeonDevicePos = event.blockEntity.pos
        val affectingAspects = getAffectingAspectItems(event.affectingItems)

        val dungeonLevel = dungeonLevelService.getLevel(player)
        val seed = Random.nextInt() // TODO player seed
        val random = Random(seed)

        val dungeonType = if (random.nextBoolean()) DungeonType.STRONGHOLD else DungeonType.NETHER // TODO player dungeon type
        val (mapType, enemyTypes, bossTypes) = dungeonType.roll(random)

        val generateLayoutCommand = DungeonGenerateCommand(dungeonLevel, dungeonType.bossCount, affectingAspects)
        val cmd = CommandGateway.apply(generateLayoutCommand)

        val layoutGenerator = mapType.layoutGeneratorProvider()
        val layout = layoutGenerator.generateDungeon(random, cmd.dungeonLevel, cmd.bossCount)

        RuntimeConfig.SERVER.execute {
            val dungeonWorld = dungeonWorldService.create(dungeonLevel)
            dungeonWorld.aspects = affectingAspects

            dungeonBuilderService.build(dungeonWorld.world, layout.rooms)

            enemyGenerationService.generate(dungeonWorld, cmd.dungeonLevel, enemyTypes, layout.enemySpawnPos, random)
            bossGenerationService.generate(dungeonWorld, cmd.dungeonLevel, bossTypes, layout.bossSpawnPos, random)

            val dungeonGeneratedEvent = DungeonGeneratedEvent(originWorld, dungeonWorld, layout.spawnPos, dungeonDevicePos)
            EventGateway.launchPublish(dungeonGeneratedEvent)
        }
    }

    private fun getAffectingAspectItems(
        affectingItemStacks: DefaultedList<ItemStack>,
    ): Map<AspectItem, Int> {
        val aspectItemStacks = affectingItemStacks.filter { it.item is AspectItem }.sortedBy { (it.item as AspectItem).tier }

        val affectingAspects = HashMap<AspectItem, Int>()
        val disabledAspects = mutableSetOf<AspectItem>()
        aspectItemStacks.forEach {
            val item = it.item as AspectItem
            if (disabledAspects.contains(item)) return@forEach
            if ((affectingAspects[item] ?: 0) >= item.limit) return@forEach

            it.decrement(1)

            disabledAspects.addAll(item.disabledAspects)

            affectingAspects[item] = (affectingAspects[item] ?: 0) + 1
        }

        return affectingAspects
    }
}