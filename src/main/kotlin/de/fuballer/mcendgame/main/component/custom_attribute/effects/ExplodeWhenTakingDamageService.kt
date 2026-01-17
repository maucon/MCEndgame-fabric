package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getAllCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.damage.dealing.DamageDealingService.dealElementalDamage
import de.fuballer.mcendgame.main.messaging.misc.LivingEntityDamagedEvent
import de.fuballer.mcendgame.main.util.extension.EntityExtension.centerPos
import de.fuballer.mcendgame.main.util.extension.EntityExtension.isEnemy
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.entity.LivingEntity
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents

@Injectable
class ExplodeWhenTakingDamageService {
    @EventSubscriber
    fun on(event: LivingEntityDamagedEvent) {
        if (event.amount <= 0) return

        val damaged = event.damaged
        val serverWorld = damaged.world as? ServerWorld ?: return
        val attributes = damaged.getAllCustomAttributes()[CustomAttributeTypes.EXPLODE_WHEN_TAKING_DAMAGE] ?: return

        if (damaged.hurtTime != damaged.maxHurtTime) return

        val damagePercentage = attributes.sumOf { it.rolls[0].asDoubleRoll().getValue() }
        explode(serverWorld, damaged, damagePercentage)
    }

    private fun explode(
        world: ServerWorld,
        damaged: LivingEntity,
        damagePercentage: Double,
    ) {
        createParticles(world, damaged)
        playSound(world, damaged)

        getNearbyEnemies(world, damaged)
            .forEach { target -> target.dealElementalDamage(damagePercentage, damaged) }
    }

    private fun getNearbyEnemies(
        world: ServerWorld,
        damaged: LivingEntity,
    ) = world.getOtherEntities(damaged, damaged.boundingBox.expand(5.0)) { damaged.isEnemy(it) }

    private fun createParticles(
        world: ServerWorld,
        damaged: LivingEntity,
    ) {
        val pos = damaged.centerPos()
        world.spawnParticles(
            ParticleTypes.EXPLOSION,
            pos.x,
            pos.y,
            pos.z,
            8,
            2.0,
            2.0,
            2.0,
            1.0,
        )
    }

    private fun playSound(
        world: ServerWorld,
        damaged: LivingEntity,
    ) {
        val pos = damaged.centerPos()
        world.playSound(
            null,
            pos.x,
            pos.y,
            pos.z,
            SoundEvents.ENTITY_GENERIC_EXPLODE,
            SoundCategory.PLAYERS,
            0.35F,
            1.0F,
        )
    }
}