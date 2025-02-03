package de.fuballer.mcendgame.components.dungeon.generation.enemy

import de.fuballer.mcendgame.components.dungeon.generation.data.SpawnPosition
import de.fuballer.mcendgame.components.entity.EntityTypeStats
import de.fuballer.mcendgame.util.EntityUtil
import de.fuballer.mcendgame.util.random.RandomOption
import de.fuballer.mcendgame.util.random.RandomUtil
import de.maucon.mauconframework.annotation.Injectable
import net.minecraft.entity.LivingEntity
import net.minecraft.server.world.ServerWorld
import kotlin.random.Random

@Injectable
class EnemyGenerationService {
    fun generate(
        world: ServerWorld,
        dungeonLevel: Int,
        types: List<RandomOption<EntityTypeStats>>,
        locations: List<SpawnPosition>,
        random: Random,
    ) {
        val entities = locations.map { spawnEnemy(world, dungeonLevel, types, it, random) }
        //TODO create event
    }

    private fun spawnEnemy(
        world: ServerWorld,
        dungeonLevel: Int,
        types: List<RandomOption<EntityTypeStats>>,
        location: SpawnPosition,
        random: Random,
    ): LivingEntity {
        val type = RandomUtil.pick(types, random).option
        val entity = EntityUtil.spawnEntityWithStats(world, type, location, dungeonLevel)

        return entity
    }
}