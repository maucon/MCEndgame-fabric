package de.fuballer.mcendgame.components.entity.custom

import net.minecraft.entity.LivingEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d

interface AreaDamageDealer {
    fun dealAreaDamage(
        entity: LivingEntity,
        radius: Double,
        damage: Float
    ) {
        val world = entity.world as? ServerWorld ?: return

        val targets = world.getEntitiesByClass(
            LivingEntity::class.java,
            Box(entity.pos.add(entity.facing.doubleVector), Vec3d(0.0, 0.0, 0.0)).expand(radius)
        ) { it != entity && it.isAlive }

        for (target in targets) {
            target.damage(world, world.damageSources.mobAttack(entity), damage)
        }
    }
}