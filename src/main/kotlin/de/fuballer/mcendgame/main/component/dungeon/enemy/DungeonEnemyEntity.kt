package de.fuballer.mcendgame.main.component.dungeon.enemy

import de.fuballer.mcendgame.main.accessor.LivingEntityDungeonEnemyAccessor
import de.fuballer.mcendgame.main.accessor.LivingEntityEliteAccessor
import de.fuballer.mcendgame.main.accessor.LivingEntityLootGoblinAccessor
import de.fuballer.mcendgame.main.accessor.MobEntityDungeonBossAccessor
import net.minecraft.entity.LivingEntity

open class DungeonEnemyEntity(
    val livingEntity: LivingEntity,
) {
    init {
        val enemyAccessor = livingEntity as LivingEntityDungeonEnemyAccessor
        enemyAccessor.`mcendgame$setDungeonEnemy`()
    }

    companion object {
        fun LivingEntity.toDungeonEnemyEntity() = DungeonEnemyEntity(this)
    }

    private val accessor = livingEntity as LivingEntityDungeonEnemyAccessor
    private val lootGoblinAccessor = livingEntity as LivingEntityLootGoblinAccessor
    private val eliteAccessor = livingEntity as LivingEntityEliteAccessor
    private val bossAccessor = livingEntity as? MobEntityDungeonBossAccessor

    var isDungeonBoss: Boolean
        get() = bossAccessor?.`mcendgame$isDungeonBoss`() == true
        set(value) {
            bossAccessor?.`mcendgame$setDungeonBoss`(value)
        }

    var isLootGoblin: Boolean
        get() = lootGoblinAccessor.`mcendgame$isLootGoblin`()
        set(value) = lootGoblinAccessor.`mcendgame$setLootGoblin`(value)

    fun setLootGoblin() {
        isLootGoblin = true
    }

    var isElite: Boolean
        get() = eliteAccessor.`mcendgame$isElite`()
        set(value) = eliteAccessor.`mcendgame$setElite`(value)

    fun setElite() {
        isElite = true
    }
}