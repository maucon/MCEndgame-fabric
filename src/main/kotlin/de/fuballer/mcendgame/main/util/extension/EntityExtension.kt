package de.fuballer.mcendgame.main.util.extension

import de.fuballer.mcendgame.main.accessor.LivingEntityDungeonEnemyAccessor
import de.fuballer.mcendgame.main.accessor.MobEntityDungeonBossAccessor
import de.fuballer.mcendgame.main.component.dungeon.generation.data.SpawnPosition
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity

object EntityExtension {
    fun Entity.isDungeonEnemy(): Boolean {
        val enemyAccessor = this as? LivingEntityDungeonEnemyAccessor ?: return false
        return enemyAccessor.`mcendgame$isDungeonEnemy`()
    }

    fun LivingEntity.setDungeonEnemy() {
        val enemyAccessor = this as LivingEntityDungeonEnemyAccessor
        enemyAccessor.`mcendgame$setDungeonEnemy`()
    }

    fun Entity.isDungeonBoss(): Boolean {
        val bossAccessor = this as? MobEntityDungeonBossAccessor ?: return false
        return bossAccessor.`mcendgame$isDungeonBoss`()
    }

    fun MobEntity.setDungeonBoss() {
        val bossAccessor = this as MobEntityDungeonBossAccessor
        bossAccessor.`mcendgame$setDungeonBoss`()
    }

    fun Entity.getDungeonBossSpawnLocation(): SpawnPosition? {
        val bossAccessor = this as? MobEntityDungeonBossAccessor ?: return null
        return bossAccessor.`mcendgame$getSpawnLocation`()
    }

    fun MobEntity.setDungeonBossSpawnLocation(location: SpawnPosition) {
        val bossAccessor = this as MobEntityDungeonBossAccessor
        bossAccessor.`mcendgame$setSpawnLocation`(location)
    }
}