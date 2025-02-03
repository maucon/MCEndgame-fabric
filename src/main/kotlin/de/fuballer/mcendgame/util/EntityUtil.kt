package de.fuballer.mcendgame.util

import de.fuballer.mcendgame.components.dungeon.generation.data.SpawnPosition
import de.fuballer.mcendgame.components.entity.EntityTypeStats
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.server.world.ServerWorld

object EntityUtil {
    fun spawnEntityWithStats(
        world: ServerWorld,
        type: EntityTypeStats,
        location: SpawnPosition,
        dungeonLevel: Int,
    ): LivingEntity {
        val entity = type.type.spawn(world, location.blockPos(), SpawnReason.STRUCTURE)
            ?: throw Exception("Couldn't  spawn entity of type: ${type.type}, in world: $world")

        entity.refreshPositionAndAngles(
            location.pos.x + 0.5,
            location.pos.y + 0.5,
            location.pos.z + 0.5,
            location.rot.toFloat(),
            0F
        )

        setStats(entity, type, dungeonLevel)

        return entity
    }

    private fun setStats(
        entity: LivingEntity,
        type: EntityTypeStats,
        dungeonLevel: Int,
    ) {
        val newMaxHealth = type.baseHealth + dungeonLevel * type.healthPerTier
        entity.getAttributeInstance(EntityAttributes.MAX_HEALTH)?.baseValue = newMaxHealth
        entity.health = newMaxHealth.toFloat()

        val newAttackDamage = type.baseDamage + dungeonLevel * type.damagePerTier
        entity.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE)?.baseValue = newAttackDamage

        val newMovementSpeed = type.baseSpeed + dungeonLevel * type.speedPerTier
        entity.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED)?.baseValue = newMovementSpeed

        entity.getAttributeInstance(EntityAttributes.KNOCKBACK_RESISTANCE)?.baseValue = type.knockbackResistance
    }
}