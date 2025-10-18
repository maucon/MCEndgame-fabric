package de.fuballer.mcendgame.main.component.dungeon.generation.encounter

import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.messaging.CollectDungeonEncountersCommand
import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.messaging.GenerateDungeonEncountersEvent
import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem
import de.maucon.mauconframework.command.CommandGateway
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventGateway
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.Vec3i
import kotlin.random.Random

@Injectable
class DungeonEncounterGenerationService {
    fun generate(
        world: ServerWorld,
        positions: List<Vec3i>,
        aspects: Map<AspectItem, Int>,
        random: Random,
    ) {
        val collectCommand = CollectDungeonEncountersCommand(random, aspects)
        val cmd = CommandGateway.apply(collectCommand)

        val generateEvent = GenerateDungeonEncountersEvent(world, positions.toMutableList(), aspects, cmd.encounters)
        EventGateway.launchPublish(generateEvent)
    }
}