package de.fuballer.mcendgame.util.minecraft

import de.fuballer.mcendgame.component.dungeon.generation.data.SpawnPosition
import de.fuballer.mcendgame.component.entity.EntityTypeStats
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.MobEntity
import net.minecraft.server.world.ServerWorld

object EntityUtil {
    fun spawnEntityWithStats(
        world: ServerWorld,
        type: EntityTypeStats,
        location: SpawnPosition,
        level: Int,
    ): MobEntity {
        val entity = type.type.spawn(world, location.blockPos(), SpawnReason.STRUCTURE)
            ?: throw Exception("Couldn't  spawn entity of type: ${type.type}, in world: $world")

        entity.refreshPositionAndAngles(
            location.pos.x + 0.5,
            location.pos.y.toDouble(),
            location.pos.z + 0.5,
            location.rot.toFloat(),
            0F
        )
        setStats(entity, type, level)

        return entity
    }

    private fun setStats(
        entity: MobEntity,
        type: EntityTypeStats,
        level: Int,
    ) {
        val newMaxHealth = type.baseHealth + level * type.healthPerTier
        entity.getAttributeInstance(EntityAttributes.MAX_HEALTH)?.baseValue = newMaxHealth
        entity.health = newMaxHealth.toFloat()

        val newAttackDamage = type.baseDamage + level * type.damagePerTier
        entity.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE)?.baseValue = newAttackDamage

        val newMovementSpeed = type.baseSpeed + level * type.speedPerTier
        entity.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED)?.baseValue = newMovementSpeed

        entity.getAttributeInstance(EntityAttributes.KNOCKBACK_RESISTANCE)?.baseValue = type.knockbackResistance
    }
}