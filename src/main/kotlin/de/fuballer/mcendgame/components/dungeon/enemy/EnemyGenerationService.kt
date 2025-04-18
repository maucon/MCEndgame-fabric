package de.fuballer.mcendgame.components.dungeon.enemy

import de.fuballer.mcendgame.components.dungeon.generation.data.SpawnPosition
import de.fuballer.mcendgame.components.dungeon.enemy.equipment.EquipmentGenerationService
import de.fuballer.mcendgame.components.dungeon.enemy.potion_effect.PotionEffectService
import de.fuballer.mcendgame.components.entity.EntityTypeStats
import de.fuballer.mcendgame.util.EntityUtil
import de.fuballer.mcendgame.util.random.RandomOption
import de.fuballer.mcendgame.util.random.RandomUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.MobEntity
import net.minecraft.server.world.ServerWorld
import kotlin.random.Random

@Injectable
class EnemyGenerationService(
    private val equipmentGenerationService: EquipmentGenerationService,
    private val potionEffectService: PotionEffectService,
) {
    fun generate(
        world: ServerWorld,
        level: Int,
        types: List<RandomOption<EntityTypeStats>>,
        locations: List<SpawnPosition>,
        random: Random,
    ) {
        val entities = locations.map { spawnEnemy(world, level, types, it, random) }
        //TODO create event
    }

    private fun spawnEnemy(
        world: ServerWorld,
        level: Int,
        types: List<RandomOption<EntityTypeStats>>,
        location: SpawnPosition,
        random: Random,
    ): MobEntity {
        val type = RandomUtil.pick(types, random).option
        val entity = EntityUtil.spawnEntityWithStats(world, type, location, level)

        equipmentGenerationService.generate(
            entity,
            level,
            type.canHaveWeapons,
            type.isRanged,
            type.canHaveArmor,
            world.server,
            random
        )

        potionEffectService.addEffects(entity, level, type.canBeInvisible, random)

        entity.setPersistent()
        setScale(entity, random)

        return entity
    }

    private fun setScale(
        entity: MobEntity,
        random: Random,
    ) {
        val scale = EnemyGenerationSettings.getRandomScale(random)
        entity.getAttributeInstance(EntityAttributes.SCALE)?.baseValue = scale
    }
}