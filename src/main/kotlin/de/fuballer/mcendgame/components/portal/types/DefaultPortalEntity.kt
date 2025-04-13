package de.fuballer.mcendgame.components.portal.types

import de.fuballer.mcendgame.components.portal.AbstractPortalEntity
import net.minecraft.entity.AnimationState
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.world.World

private const val START_ANIMATION_DURATION = 80

class DefaultPortalEntity(
    entityType: EntityType<out LivingEntity>,
    world: World
) : AbstractPortalEntity(entityType, world) {
    val startAnimationState = AnimationState()
    val idleAnimationState = AnimationState()

    override fun playAnimation(ticksSinceStart: Int) {
        if (ticksSinceStart < START_ANIMATION_DURATION) {
            idleAnimationState.stop()
            startAnimationState.startIfNotRunning(age)
        } else {
            startAnimationState.stop()
            idleAnimationState.startIfNotRunning(age)
        }
    }
}