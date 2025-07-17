package de.fuballer.mcendgame.main.component.dungeon.enemy.boss

import de.fuballer.mcendgame.main.component.dungeon.generation.data.SpawnPosition
import de.fuballer.mcendgame.main.component.dungeon.world.DungeonWorld
import de.fuballer.mcendgame.main.component.entity.EntityTypeStats
import de.fuballer.mcendgame.main.util.extension.mixin.EntityMixinExtension.setDungeonBoss
import de.fuballer.mcendgame.main.util.extension.mixin.EntityMixinExtension.setDungeonBossSpawnPosition
import de.fuballer.mcendgame.main.util.extension.mixin.EntityMixinExtension.setDungeonEnemy
import de.fuballer.mcendgame.main.util.minecraft.EntityUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.MobEntity
import kotlin.random.Random

@Injectable
class BossGenerationService {
    fun generate(
        dungeonWorld: DungeonWorld,
        level: Int,
        types: List<EntityTypeStats>,
        locations: List<SpawnPosition>,
        random: Random,
    ) {
        val shuffledTypes = types.shuffled(random)
        val bosses = locations.mapIndexed { index, pos ->
            val type = shuffledTypes[index % shuffledTypes.size]
            spawnBoss(dungeonWorld, level, type, pos, random)
        }
        //TODO create event
    }

    private fun spawnBoss(
        dungeonWorld: DungeonWorld,
        level: Int,
        type: EntityTypeStats,
        spawnPosition: SpawnPosition,
        random: Random,
    ): MobEntity {
        val bossEntity = EntityUtil.spawnEntityWithStats(dungeonWorld.world, type, spawnPosition, level)

        bossEntity.setPersistent()
        setScale(bossEntity, random)

        bossEntity.isAiDisabled = true

        bossEntity.setDungeonEnemy()
        bossEntity.setDungeonBoss()
        bossEntity.setDungeonBossSpawnPosition(spawnPosition)

        return bossEntity
    }

    private fun setScale(
        entity: MobEntity,
        random: Random,
    ) {
        val scale = BossGenerationSettings.getRandomScale(random)
        entity.getAttributeInstance(EntityAttributes.SCALE)?.baseValue = scale
    }
}