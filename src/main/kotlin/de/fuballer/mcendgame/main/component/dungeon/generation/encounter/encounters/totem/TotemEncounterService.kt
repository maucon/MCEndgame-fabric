package de.fuballer.mcendgame.main.component.dungeon.generation.encounter.encounters.totem

import de.fuballer.mcendgame.main.component.block.CustomBlocks
import de.fuballer.mcendgame.main.component.block.blocks.totem_statue.TotemStatueSpawnEnemiesCommand
import de.fuballer.mcendgame.main.component.dungeon.enemy.EnemyGenerationService
import de.fuballer.mcendgame.main.component.dungeon.generation.data.SpawnPosition
import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.EncounterType
import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.messaging.CollectDungeonEncountersCommand
import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.messaging.GenerateDungeonEncountersEvent
import de.fuballer.mcendgame.main.util.extension.Vec3iExtension.toBlockPos
import de.fuballer.mcendgame.main.util.extension.mixin.WorldMixinExtension.getDungeonLevel
import de.fuballer.mcendgame.main.util.extension.mixin.WorldMixinExtension.getDungeonType
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.block.Blocks
import net.minecraft.state.property.Properties

@Injectable
class TotemEncounterService(
    val enemyGenerationService: EnemyGenerationService,
) {
    @CommandHandler
    fun on(cmd: CollectDungeonEncountersCommand) {
        if (cmd.random.nextDouble() >= TotemEncounterSettings.BASE_PROBABILITY) return
        cmd.add(EncounterType.TOTEM)
    }

    @EventSubscriber(sync = true)
    fun on(event: GenerateDungeonEncountersEvent) {
        val amount = event.encounters[EncounterType.TOTEM] ?: return
        if (amount <= 0) return

        val positions = event.takeLocations(amount)
        if (positions.isEmpty()) return

        positions.forEach { encounterLocation ->
            val loc = encounterLocation.location
            val wallState = Blocks.POLISHED_TUFF_WALL.defaultState
            event.world.setBlockState(loc.toBlockPos(), wallState)

            val rotation = encounterLocation.getRotation16()
            val totemState = CustomBlocks.TOTEM_STATUE.defaultState
                .with(Properties.ROTATION, rotation)
            event.world.setBlockState(loc.add(0, 1, 0).toBlockPos(), totemState)
        }
    }

    @CommandHandler
    fun on(cmd: TotemStatueSpawnEnemiesCommand) {
        val world = cmd.world
        val dungeonType = world.getDungeonType()
        val enemyTypes = dungeonType.getEntityTypes()
        val spawnPositions = cmd.positions.map { SpawnPosition(it, 0.0) }

        cmd.enemies = enemyGenerationService.generate(
            world,
            world.getDungeonLevel(),
            enemyTypes,
            dungeonType.applyMisc,
            spawnPositions,
            true,
        )
    }
}