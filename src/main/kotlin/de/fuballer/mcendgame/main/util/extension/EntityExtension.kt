package de.fuballer.mcendgame.main.util.extension

import de.fuballer.mcendgame.main.accessor.LivingEntityDungeonEnemyAccessor
import de.fuballer.mcendgame.main.accessor.MobEntityDungeonBossAccessor
import de.fuballer.mcendgame.main.component.dungeon.generation.data.SpawnPosition
import de.fuballer.mcendgame.main.util.extension.WorldExtension.isDungeonWorld
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.Tameable
import net.minecraft.entity.decoration.ArmorStandEntity
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.mob.Monster
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.entity.passive.IronGolemEntity
import net.minecraft.entity.passive.VillagerEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.Vec3d

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

    fun Entity.isValidSecondaryTarget(primaryTarget: Entity, attacker: Entity): Boolean {
        if (this == attacker || this == primaryTarget) return false

        if (world.isDungeonWorld())
            return isValidSecondaryTargetInDungeon(primaryTarget)
        return isValidSecondaryTargetOutsideDungeon(primaryTarget, attacker)
    }

    private fun Entity.isValidSecondaryTargetInDungeon(primaryTarget: Entity) = isDungeonEnemy() == primaryTarget.isDungeonEnemy()

    private fun Entity.isValidSecondaryTargetOutsideDungeon(primaryTarget: Entity, attacker: Entity): Boolean {
        if (this.type == primaryTarget.type) return true

        if (attacker.isOrIsTameableOf(PlayerEntity::class.java)) {
            if (this.isOrIsTameableOf(Monster::class.java)) return true
        }

        if (primaryTarget is ArmorStandEntity) return this is ArmorStandEntity
        if (primaryTarget.isOrIsTameableOf(Monster::class.java)) return this.isOrIsTameableOf(Monster::class.java)
        if (primaryTarget is AnimalEntity) return this is AnimalEntity
        if (primaryTarget is VillagerEntity) return this is VillagerEntity || this is AnimalEntity || this is IronGolemEntity
        if (primaryTarget.isOrIsTameableOf(PlayerEntity::class.java)) return this.isOrIsTameableOf(PlayerEntity::class.java)

        return false
    }

    private fun Entity.isOrIsTameableOf(clazz: Class<*>): Boolean {
        if (clazz.isInstance(this)) return true

        val tameable = this as? Tameable ?: return false
        val owner = tameable.owner ?: return false
        return clazz.isInstance(owner)
    }

    fun Entity.centerPos(): Vec3d = pos.add(0.0, height.toDouble(), 0.0)
}