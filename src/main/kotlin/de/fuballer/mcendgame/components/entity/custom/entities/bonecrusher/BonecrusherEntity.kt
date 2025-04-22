package de.fuballer.mcendgame.components.entity.custom.entities.bonecrusher

import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.ActiveTargetGoal
import net.minecraft.entity.ai.goal.MeleeAttackGoal
import net.minecraft.entity.ai.goal.SwimGoal
import net.minecraft.entity.ai.goal.WanderAroundFarGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.PathAwareEntity
import net.minecraft.entity.passive.VillagerEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animatable.manager.AnimatableManager
import software.bernie.geckolib.animatable.processing.AnimationController
import software.bernie.geckolib.animatable.processing.AnimationTest
import software.bernie.geckolib.animation.PlayState
import software.bernie.geckolib.animation.RawAnimation
import software.bernie.geckolib.util.GeckoLibUtil


class BonecrusherEntity(
    type: EntityType<out BonecrusherEntity>,
    world: World,
) : PathAwareEntity(type, world), GeoEntity {
    private val cache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)

    companion object {
        val WALK_ANIM: RawAnimation = RawAnimation.begin().thenLoop("animation.bonecrusher.walk")

        fun createAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.FOLLOW_RANGE, 35.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.2)
                .add(EntityAttributes.ATTACK_DAMAGE, 7.0)
                .add(EntityAttributes.ATTACK_KNOCKBACK, 2.0)
                .add(EntityAttributes.ARMOR, 0.0)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, 0.8)
                .add(EntityAttributes.MOVEMENT_EFFICIENCY, 0.85)
        }
    }

    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))
        goalSelector.add(1, MeleeAttackGoal(this, 1.0, true))
        goalSelector.add(7, WanderAroundFarGoal(this, 0.75))

        targetSelector.add(2, ActiveTargetGoal(this, PlayerEntity::class.java, true))
        targetSelector.add(3, ActiveTargetGoal(this, VillagerEntity::class.java, true))
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(AnimationController("walk", 5) { state -> walkController(state) })
    }

    private fun walkController(event: AnimationTest<BonecrusherEntity>): PlayState {
        if (event.isMoving) return event.setAndContinue(WALK_ANIM)
        return PlayState.STOP
    }

    override fun getAnimatableInstanceCache() = cache
}