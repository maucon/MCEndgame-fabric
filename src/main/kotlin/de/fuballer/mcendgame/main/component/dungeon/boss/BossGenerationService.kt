package de.fuballer.mcendgame.main.component.dungeon.boss

import de.fuballer.mcendgame.main.component.dungeon.generation.data.SpawnPosition
import de.fuballer.mcendgame.main.component.entity.EntityTypeStats
import de.fuballer.mcendgame.main.util.extension.EntityExtension.setDungeonBoss
import de.fuballer.mcendgame.main.util.extension.EntityExtension.setDungeonBossSpawnLocation
import de.fuballer.mcendgame.main.util.extension.EntityExtension.setDungeonEnemy
import de.fuballer.mcendgame.main.util.minecraft.EntityUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.MobEntity
import net.minecraft.server.world.ServerWorld
import kotlin.random.Random

@Injectable
class BossGenerationService {
    fun generate(
        world: ServerWorld,
        level: Int,
        types: List<EntityTypeStats>,
        locations: List<SpawnPosition>,
        random: Random,
    ) {
        val bosses = locations.mapIndexed { index, pos -> spawnBoss(world, level, types[index], pos, random) }
        //TODO create event
    }

    private fun spawnBoss(
        world: ServerWorld,
        level: Int,
        type: EntityTypeStats,
        location: SpawnPosition,
        random: Random,
    ): MobEntity {
        val entity = EntityUtil.spawnEntityWithStats(world, type, location, level)

        entity.setPersistent()
        setScale(entity, random)

        entity.isAiDisabled = true

        entity.setDungeonBoss()
        entity.setDungeonBossSpawnLocation(location)
        entity.setDungeonEnemy()

        return entity
    }

    private fun setScale(
        entity: MobEntity,
        random: Random,
    ) {
        val scale = BossGenerationSettings.getRandomScale(random)
        entity.getAttributeInstance(EntityAttributes.SCALE)?.baseValue = scale
    }
}