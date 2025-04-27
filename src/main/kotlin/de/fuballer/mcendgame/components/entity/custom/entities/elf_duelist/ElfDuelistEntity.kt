package de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist

import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.Monster
import net.minecraft.entity.mob.PathAwareEntity
import net.minecraft.entity.passive.VillagerEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animatable.manager.AnimatableManager
import software.bernie.geckolib.animatable.processing.AnimationController
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.util.GeckoLibUtil
import kotlin.math.sin

class ElfDuelistEntity(
    type: EntityType<out ElfDuelistEntity>,
    world: World,
) : PathAwareEntity(type, world), GeoEntity, Monster {
    private val cache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)

    companion object {
        fun createAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.FOLLOW_RANGE, 35.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.ATTACK_KNOCKBACK, 0.5)
                .add(EntityAttributes.ARMOR, 0.0)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, 0.8)
                .add(EntityAttributes.MOVEMENT_EFFICIENCY, 0.85)
        }
    }

    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))
        goalSelector.add(1, MeleeAttackGoal(this, 1.0, true))
        goalSelector.add(2, WanderAroundFarGoal(this, 0.7))
        goalSelector.add(3, LookAtEntityGoal(this, PlayerEntity::class.java, 8F))
        goalSelector.add(3, LookAroundGoal(this))

        targetSelector.add(0, RevengeGoal(this))
        targetSelector.add(1, ActiveTargetGoal(this, PlayerEntity::class.java, true))
        targetSelector.add(2, ActiveTargetGoal(this, VillagerEntity::class.java, true))
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController<ElfDuelistEntity>("Walk/Idle", 5)
            { test -> test.setAndContinue(if (test.isMoving) DefaultAnimations.WALK else DefaultAnimations.IDLE) },
        )
    }

    fun getLimbSwingCycle(tickProgress: Float) = sin(limbAnimator.getAnimationProgress(tickProgress) / 1.5)
    fun getLimbSwingAmplitude(tickProgress: Float) = limbAnimator.getAmplitude(tickProgress).toDouble()

    override fun getAnimatableInstanceCache() = cache
}