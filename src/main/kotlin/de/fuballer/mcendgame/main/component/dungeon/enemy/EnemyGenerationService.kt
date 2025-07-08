package de.fuballer.mcendgame.main.component.dungeon.enemy

import de.fuballer.mcendgame.main.component.dungeon.enemy.equipment.EquipmentGenerationService
import de.fuballer.mcendgame.main.component.dungeon.enemy.potion_effect.PotionEffectService
import de.fuballer.mcendgame.main.component.dungeon.generation.data.SpawnPosition
import de.fuballer.mcendgame.main.component.entity.EntityTypeStats
import de.fuballer.mcendgame.main.util.extension.EntityExtension.setDungeonEnemy
import de.fuballer.mcendgame.main.util.extension.EntityExtension.setElite
import de.fuballer.mcendgame.main.util.extension.EntityExtension.setLootGoblin
import de.fuballer.mcendgame.main.util.minecraft.EntityUtil
import de.fuballer.mcendgame.main.util.random.RandomOption
import de.fuballer.mcendgame.main.util.random.RandomUtil
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

        val isLootGoblin = isLootGoblin(entity, type, random)

        equipmentGenerationService.generate(
            entity,
            level,
            type.canHaveWeapons,
            type.isRanged,
            type.canHaveArmor,
            world.server,
            isLootGoblin,
            random,
        )

        potionEffectService.addEffects(entity, level, type.canBeInvisible, random)

        entity.setPersistent()

        val isElite = isElite(entity, random)
        setScale(entity, isElite, random)

        entity.setDungeonEnemy()

        entity.heal(1000F)

        return entity
    }

    private fun isLootGoblin(entity: MobEntity, type: EntityTypeStats, random: Random): Boolean {
        if (!type.canHaveArmor) return false
        if (random.nextDouble() > EnemyGenerationSettings.LOOT_GOBLIN_CHANCE) return false

        entity.setLootGoblin()
        return true
    }

    private fun isElite(entity: MobEntity, random: Random): Boolean {
        if (random.nextDouble() > EnemyGenerationSettings.ELITE_CHANCE) return false
        entity.setElite()

        val healthAttributeInstance = entity.getAttributeInstance(EntityAttributes.MAX_HEALTH)
        val newMaxHealth = healthAttributeInstance?.baseValue!! * EnemyGenerationSettings.ELITE_HEALTH_FACTOR
        healthAttributeInstance.baseValue = newMaxHealth

        entity.addStatusEffect(EnemyGenerationSettings.getEliteStatusEffect())

        return true
    }

    private fun setScale(
        entity: MobEntity,
        isElite: Boolean,
        random: Random,
    ) {
        val scale = if (isElite) EnemyGenerationSettings.ELITE_SCALE else EnemyGenerationSettings.getRandomScale(random)
        entity.getAttributeInstance(EntityAttributes.SCALE)?.baseValue = scale
    }
}