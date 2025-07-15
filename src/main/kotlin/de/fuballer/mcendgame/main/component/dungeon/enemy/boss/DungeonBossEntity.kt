package de.fuballer.mcendgame.main.component.dungeon.enemy.boss

import de.fuballer.mcendgame.main.accessor.MobEntityDungeonBossAccessor
import de.fuballer.mcendgame.main.component.dungeon.enemy.DungeonEnemyEntity
import de.fuballer.mcendgame.main.component.dungeon.generation.data.SpawnPosition
import net.minecraft.entity.mob.MobEntity
import net.minecraft.util.math.Vec3i

class DungeonBossEntity(
    val mobEntity: MobEntity,
) : DungeonEnemyEntity(mobEntity) {
    companion object {
        fun MobEntity.toDungeonBossEntity() = DungeonBossEntity(this)
        fun DungeonEnemyEntity.toDungeonBossEntity(): DungeonBossEntity {
            val mobEntity = this.livingEntity as? MobEntity ?: throw ClassCastException("Dungeon boss has to be of type MobEntity")
            return mobEntity.toDungeonBossEntity()
        }
    }

    private val accessor = mobEntity as MobEntityDungeonBossAccessor

    var spawnPosition: SpawnPosition
        get() = accessor.`mcendgame$getSpawnPosition`() ?: SpawnPosition(Vec3i.ZERO)
        set(value) = accessor.`mcendgame$setSpawnPosition`(value)
}