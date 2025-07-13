package de.fuballer.mcendgame.main.component.dungeon.enemy

import de.fuballer.mcendgame.main.component.dungeon.enemy.equipment.EquipmentGenerationService
import de.fuballer.mcendgame.main.component.dungeon.enemy.potion_effect.PotionEffectService
import de.fuballer.mcendgame.main.component.dungeon.generation.data.SpawnPosition
import de.fuballer.mcendgame.main.component.dungeon.world.DungeonWorld
import de.fuballer.mcendgame.main.component.entity.EntityTypeStats
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonGenerateEnemiesCommand
import de.fuballer.mcendgame.main.util.extension.EntityExtension.setDungeonEnemy
import de.fuballer.mcendgame.main.util.extension.EntityExtension.setElite
import de.fuballer.mcendgame.main.util.extension.EntityExtension.setLootGoblin
import de.fuballer.mcendgame.main.util.minecraft.EntityUtil
import de.fuballer.mcendgame.main.util.random.RandomOption
import de.fuballer.mcendgame.main.util.random.RandomUtil
import de.maucon.mauconframework.command.CommandGateway
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.MobEntity
import kotlin.random.Random

@Injectable
class EnemyGenerationService(
    private val equipmentGenerationService: EquipmentGenerationService,
    private val potionEffectService: PotionEffectService,
) {
    fun generate(
        dungeonWorld: DungeonWorld,
        level: Int,
        types: List<RandomOption<EntityTypeStats>>,
        spawnPositions: List<SpawnPosition>,
        random: Random,
    ) {
        val generateDungeonEnemiesCommand = DungeonGenerateEnemiesCommand.of(dungeonWorld, spawnPositions.toMutableList())
        var cmd = CommandGateway.apply(generateDungeonEnemiesCommand)

        val entities = cmd.spawnPositions.map {
            spawnEnemy(
                dungeonWorld,
                level,
                types,
                it,
                random,
                cmd.uniqueEquipmentChance,
                cmd.lootGoblinLuckyAttributes,
            )
        }.toMutableList()
        entities.addAll(cmd.eliteSpawnPositions.map {
            spawnEnemy(
                dungeonWorld,
                level,
                types,
                it,
                random,
                cmd.uniqueEquipmentChance,
                cmd.lootGoblinLuckyAttributes,
                isForcedElite = true,
            )
        })
        entities.addAll(cmd.lootGoblinSpawnPositions.map {
            spawnEnemy(
                dungeonWorld,
                level,
                types,
                it,
                random,
                cmd.uniqueEquipmentChance,
                cmd.lootGoblinLuckyAttributes,
                isForcedLootGoblin = true,
            )
        })

        //TODO create event
    }

    private fun spawnEnemy(
        dungeonWorld: DungeonWorld,
        level: Int,
        types: List<RandomOption<EntityTypeStats>>,
        location: SpawnPosition,
        random: Random,
        uniqueEquipmentProbability: Double,
        lootGoblinLuckyAttributes: Boolean,
        isForcedElite: Boolean = false,
        isForcedLootGoblin: Boolean = false,
    ): MobEntity {
        val isLootGoblin = isForcedLootGoblin || EnemyGenerationSettings.randomLootGoblin(random)

        val validTypes = if (!isLootGoblin) types else types.filter { it.option.canHaveArmor }
        val type = RandomUtil.pick(validTypes, random).option
        val entity = EntityUtil.spawnEntityWithStats(dungeonWorld.world, type, location, level)

        entity.setPersistent()
        entity.setDungeonEnemy()
        if (isLootGoblin) entity.setLootGoblin()

        val isElite = isForcedElite || EnemyGenerationSettings.randomElite(random)
        if (isElite) setElite(entity)
        setScale(entity, isElite, random)

        val luckyAttributes = isLootGoblin && lootGoblinLuckyAttributes
        equipmentGenerationService.generate(
            entity,
            level,
            type.canHaveWeapons,
            type.isRanged,
            type.canHaveArmor,
            dungeonWorld.world.server,
            isLootGoblin,
            random,
            uniqueEquipmentProbability,
            luckyAttributes,
        )

        potionEffectService.addEffects(entity, level, type.canBeInvisible, random)

        entity.heal(1000F)
        return entity
    }

    private fun setElite(entity: MobEntity): Boolean {
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