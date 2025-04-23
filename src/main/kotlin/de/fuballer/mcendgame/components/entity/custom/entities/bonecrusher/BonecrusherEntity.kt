package de.fuballer.mcendgame.components.entity.custom.entities.bonecrusher

import de.fuballer.mcendgame.components.entity.custom.goals.NoMovementMeleeAttackGoal
import de.fuballer.mcendgame.components.entity.custom.goals.StayInRangeGoal
import de.fuballer.mcendgame.components.entity.custom.interfaces.MeleeAttackMob
import de.fuballer.mcendgame.components.entity.custom.util.HorizontalRotationRelativeBoxAreaAttack
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.goal.ActiveTargetGoal
import net.minecraft.entity.ai.goal.SwimGoal
import net.minecraft.entity.ai.goal.WanderAroundFarGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.PathAwareEntity
import net.minecraft.entity.passive.VillagerEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World
import software.bernie.geckolib.animatable.GeoAnimatable
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
) : PathAwareEntity(type, world), GeoEntity, MeleeAttackMob {
    private val cache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)

    private var attackCooldown = 0
    private var attackDamageDelay = 0
    private var attackDamageFunction: (() -> Unit)? = null

    companion object {
        val WALK_ANIM: RawAnimation = RawAnimation.begin().thenLoop("movement.walk")
        val HIT_ANIM: RawAnimation = RawAnimation.begin().thenPlay("attack.hit")

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

        val HIT_AREA_ATTACK = HorizontalRotationRelativeBoxAreaAttack(3.0, 1.3, 1.5, 0.0, 0.5, 0.5)
    }

    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))
        goalSelector.add(1, NoMovementMeleeAttackGoal(this, 30, 3.0))
        goalSelector.add(2, StayInRangeGoal(this, 1.0, 2.0))
        goalSelector.add(7, WanderAroundFarGoal(this, 0.75))

        targetSelector.add(2, ActiveTargetGoal(this, PlayerEntity::class.java, true))
        targetSelector.add(3, ActiveTargetGoal(this, VillagerEntity::class.java, true))
    }

    override fun tick() {
        super.tick()
        tickAttackCooldown()
    }

    private fun tickAttackCooldown() {
        if (world.isClient) return

        if (attackCooldown > 0) {
            attackCooldown--
        }

        if (attackDamageDelay > 0) {
            if (--attackDamageDelay == 0) {
                attackDamageFunction?.let {
                    it.invoke()
                    attackDamageFunction = null
                }
            }
        }
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController("movement_controller", 5) { animTest -> movementAnimationController(animTest) },
            AnimationController<GeoAnimatable>("hit_controller") { _ -> PlayState.STOP }
                .triggerableAnim("hit", HIT_ANIM)
        )
    }

    private fun movementAnimationController(animTest: AnimationTest<BonecrusherEntity>): PlayState {
        if (animTest.isMoving) return animTest.setAndContinue(WALK_ANIM)
        return PlayState.STOP
    }

    override fun getAnimatableInstanceCache() = cache

    override fun meleeAttack(target: LivingEntity) {
        if (attackCooldown > 0) return
        attackCooldown = 20
        attackDamageDelay = 5
        attackDamageFunction = ::dealHitDamage
        triggerAnim("hit_controller", "hit")
    }

    private fun dealHitDamage() {
        if (world.isClient) return
        val damage = getAttributeValue(EntityAttributes.ATTACK_DAMAGE).toFloat()
        val knockback = getAttributeValue(EntityAttributes.ATTACK_KNOCKBACK)
        HIT_AREA_ATTACK.dealDamage(this, damage, knockback)
    }
}