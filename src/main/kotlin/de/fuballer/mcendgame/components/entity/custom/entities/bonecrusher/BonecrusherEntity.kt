package de.fuballer.mcendgame.components.entity.custom.entities.bonecrusher

import de.fuballer.mcendgame.components.entity.custom.goals.DisableAbleWanderAroundFarGoal
import de.fuballer.mcendgame.components.entity.custom.goals.NoMovementMeleeAttackGoal
import de.fuballer.mcendgame.components.entity.custom.goals.StayInRangeGoal
import de.fuballer.mcendgame.components.entity.custom.interfaces.DisableAbleGoalsMob
import de.fuballer.mcendgame.components.entity.custom.interfaces.MeleeAttackMob
import de.fuballer.mcendgame.components.entity.custom.util.BlockedMovementManager
import de.fuballer.mcendgame.components.entity.custom.util.HorizontalRotationRelativeBoxAreaAttack
import de.fuballer.mcendgame.util.random.RandomOption
import de.fuballer.mcendgame.util.random.RandomUtil
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.goal.ActiveTargetGoal
import net.minecraft.entity.ai.goal.SwimGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.PathAwareEntity
import net.minecraft.entity.passive.VillagerEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.SoundEvents
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
) : PathAwareEntity(type, world), GeoEntity, MeleeAttackMob, DisableAbleGoalsMob {
    private val cache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)

    private var attackCooldown = 0
    private val delayedAttackDamage = mutableListOf<DelayedAttackDamage>()

    private class DelayedAttackDamage(
        var delay: Int,
        private val damageFunction: () -> Unit
    ) {
        fun tick() = --delay == 0
        fun apply() = damageFunction.invoke()
    }

    private val meleeAttackGoal = NoMovementMeleeAttackGoal(this, 30, 3.0)
    private val stayInMeleeRangeGoal = StayInRangeGoal(this, 1.0, 2.0)

    private val wanderGoal = DisableAbleWanderAroundFarGoal(this, 1.0)

    private val blockedMovementManager = BlockedMovementManager(this)

    private enum class SpinAttackState {
        NONE,
        START,
        SPIN,
        END,
    }

    private var spinAttackState = SpinAttackState.NONE
    private var spinAttackDuration = 0

    private val attackOptions = listOf<RandomOption<() -> Unit>>(
        RandomOption(8, ::startHitAttack),
        RandomOption(6, ::startSlamAttack),
        RandomOption(3, ::startSpinAttack),
    )

    companion object {
        const val SPIN_ATTACK_DURATION = 100

        val WALK_ANIM: RawAnimation = RawAnimation.begin().thenLoop("movement.walk")
        val HIT_ANIM: RawAnimation = RawAnimation.begin().thenPlay("attack.hit")
        val SLAM_ANIM: RawAnimation = RawAnimation.begin().thenPlay("attack.slam")
        val SPIN_START_ANIM: RawAnimation = RawAnimation.begin().thenPlay("attack.spin.start")
        val SPIN_ANIM: RawAnimation = RawAnimation.begin().thenPlay("attack.spin")
        val SPIN_END_ANIM: RawAnimation = RawAnimation.begin().thenPlay("attack.spin.end")

        private val IS_SPIN_ATTACKING = DataTracker.registerData(BonecrusherEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)

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

        val HIT_AREA_ATTACK =
            HorizontalRotationRelativeBoxAreaAttack(
                3.0, 1.3, 1.5, 0.0, 0.5, 0.5
            )
        val SLAM_AREA_ATTACK =
            HorizontalRotationRelativeBoxAreaAttack(
                5.0, 2.5, 1.5, 1.0, 0.0, 0.5,
                knockbackType = HorizontalRotationRelativeBoxAreaAttack.KnockbackType.BOX_CENTER
            ).setParticles(100, 0.25, ParticleTypes.CRIT, 0.5)
                .setSound(false, SoundEvents.ENTITY_GENERIC_EXPLODE.value(), 1F, 1F)

        val SPIN_AREA_ATTACK_FRONT = HorizontalRotationRelativeBoxAreaAttack(
            4.0, 3.0, 1.0, 0.0, 0.0, 0.5,
            knockbackType = HorizontalRotationRelativeBoxAreaAttack.KnockbackType.DAMAGER_CENTER
        )
        val SPIN_AREA_ATTACK_BACK = HorizontalRotationRelativeBoxAreaAttack(
            4.0, 3.0, 1.0, -4.0, 0.0, 0.5,
            knockbackType = HorizontalRotationRelativeBoxAreaAttack.KnockbackType.DAMAGER_CENTER
        )
        val SPIN_AREA_ATTACK_LEFT = HorizontalRotationRelativeBoxAreaAttack(
            6.0, 2.0, 1.0, -3.0, -2.0, 0.5,
            knockbackType = HorizontalRotationRelativeBoxAreaAttack.KnockbackType.DAMAGER_CENTER
        )
        val SPIN_AREA_ATTACK_RIGHT = HorizontalRotationRelativeBoxAreaAttack(
            6.0, 2.0, 1.0, -3.0, 2.0, 0.5,
            knockbackType = HorizontalRotationRelativeBoxAreaAttack.KnockbackType.DAMAGER_CENTER
        )
    }

    override fun initDataTracker(builder: DataTracker.Builder) {
        super.initDataTracker(builder)
        builder.add(IS_SPIN_ATTACKING, false)
    }

    override fun onTrackedDataSet(data: TrackedData<*>) {
        if (data == IS_SPIN_ATTACKING) startSpinAttackAnimation()
        super.onTrackedDataSet(data)
    }

    private fun startSpinAttackAnimation() {
        if (!dataTracker.get(IS_SPIN_ATTACKING)) return
        if (spinAttackState != SpinAttackState.NONE) return
        spinAttackState = SpinAttackState.START
    }

    init {
        initDynamicGoals()
    }

    private fun initDynamicGoals() {
        goalSelector.add(1, meleeAttackGoal)
        goalSelector.add(2, stayInMeleeRangeGoal)
        goalSelector.add(7, wanderGoal)
    }

    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))

        targetSelector.add(2, ActiveTargetGoal(this, PlayerEntity::class.java, true))
        targetSelector.add(3, ActiveTargetGoal(this, VillagerEntity::class.java, true))
    }

    override fun updateGoals() {
        val movementBlocked = blockedMovementManager.isBlocked()
        meleeAttackGoal.isDisabled = movementBlocked
        stayInMeleeRangeGoal.isDisabled = movementBlocked
        wanderGoal.isDisabled = movementBlocked
    }

    override fun tick() {
        super.tick()
        tickAttackCooldown()
        tickSpinAttack()
        blockedMovementManager.tick()
    }

    private fun tickAttackCooldown() {
        if (world.isClient) return

        if (attackCooldown > 0) {
            attackCooldown--
        }

        val toRemoveAttacks = mutableListOf<DelayedAttackDamage>()
        for (attack in delayedAttackDamage) {
            if (!attack.tick()) continue
            attack.apply()
            toRemoveAttacks.add(attack)
        }
        delayedAttackDamage.removeAll(toRemoveAttacks)
    }

    private fun tickSpinAttack() {
        if (world.isClient) return

        if (spinAttackDuration == 0) return
        if (--spinAttackDuration > 0) return
        dataTracker.set(IS_SPIN_ATTACKING, false)
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController("movement_controller", 5, ::movementAnimationController),
            AnimationController<GeoAnimatable>("attack_controller") { _ -> PlayState.STOP }
                .triggerableAnim("hit", HIT_ANIM)
                .triggerableAnim("slam", SLAM_ANIM),
            AnimationController("spin_controller", 0, ::spinAnimationController)
        )
    }

    private fun movementAnimationController(animTest: AnimationTest<BonecrusherEntity>): PlayState {
        if (spinAttackState != SpinAttackState.NONE) return PlayState.STOP
        if (animTest.isMoving) return animTest.setAndContinue(WALK_ANIM)
        return PlayState.STOP
    }

    private fun spinAnimationController(animTest: AnimationTest<BonecrusherEntity>): PlayState {
        val controller = animTest.controller
        when (spinAttackState) {
            SpinAttackState.START -> {
                if (controller.currentRawAnimation == SPIN_START_ANIM && controller.hasAnimationFinished()) {
                    spinAttackState = SpinAttackState.SPIN
                    return animTest.setAndContinue(SPIN_ANIM)
                }
                return animTest.setAndContinue(SPIN_START_ANIM)
            }

            SpinAttackState.SPIN -> {
                if (controller.hasAnimationFinished()) {
                    if (!dataTracker.get(IS_SPIN_ATTACKING)) {
                        spinAttackState = SpinAttackState.END
                        return animTest.setAndContinue(SPIN_END_ANIM)
                    }
                    controller.forceAnimationReset()
                }
                return animTest.setAndContinue(SPIN_ANIM)
            }

            SpinAttackState.END -> {
                if (controller.hasAnimationFinished()) {
                    spinAttackState = SpinAttackState.NONE
                    return PlayState.STOP
                }
                return animTest.setAndContinue(SPIN_END_ANIM)
            }

            else -> return PlayState.STOP
        }
    }

    override fun getAnimatableInstanceCache() = cache

    override fun meleeAttack(target: LivingEntity) {
        if (attackCooldown > 0) return
        val attack = RandomUtil.pick(attackOptions).option
        attack.invoke()
    }

    private fun startHitAttack() {
        attackCooldown = 20
        delayedAttackDamage.add(DelayedAttackDamage(5, ::dealHitAttackDamage))
        triggerAnim("attack_controller", "hit")
    }

    private fun startSlamAttack() {
        attackCooldown = 50
        delayedAttackDamage.add(DelayedAttackDamage(17, ::dealSlamAttackDamage))
        blockedMovementManager.blockMovement(25)
        triggerAnim("attack_controller", "slam")
    }

    private fun startSpinAttack() {
        spinAttackDuration = SPIN_ATTACK_DURATION
        attackCooldown = SPIN_ATTACK_DURATION + 50
        dataTracker.set(IS_SPIN_ATTACKING, true)

        addSpinAttackDamage(16, ::dealSpinAttackDamageFront)
        addSpinAttackDamage(19, ::dealSpinAttackDamageLeft)
        addSpinAttackDamage(22, ::dealSpinAttackDamageBack)
        addSpinAttackDamage(26, ::dealSpinAttackDamageRight)
    }

    private fun addSpinAttackDamage(
        delay: Int,
        damageFunction: () -> Unit,
    ) {
        val spinTime = (20 * 2 / 3.0)
        for (i in 0 until ((SPIN_ATTACK_DURATION - delay) / spinTime).toInt()) {
            val damageDelay = delay + (i * spinTime).toInt()
            delayedAttackDamage.add(DelayedAttackDamage(damageDelay, damageFunction))
        }
    }

    private fun dealHitAttackDamage() {
        if (world.isClient) return
        val damage = getAttributeValue(EntityAttributes.ATTACK_DAMAGE).toFloat()
        val knockback = getAttributeValue(EntityAttributes.ATTACK_KNOCKBACK)
        HIT_AREA_ATTACK.dealDamage(this, damage, knockback)
    }

    private fun dealSlamAttackDamage() {
        if (world.isClient) return
        val damage = getAttributeValue(EntityAttributes.ATTACK_DAMAGE).toFloat()
        val knockback = getAttributeValue(EntityAttributes.ATTACK_KNOCKBACK)
        SLAM_AREA_ATTACK.dealDamage(this, damage, knockback)
    }

    private fun dealSpinAttackDamageFront() = dealSpinAttackDamage(SPIN_AREA_ATTACK_FRONT)
    private fun dealSpinAttackDamageBack() = dealSpinAttackDamage(SPIN_AREA_ATTACK_BACK)
    private fun dealSpinAttackDamageLeft() = dealSpinAttackDamage(SPIN_AREA_ATTACK_LEFT)
    private fun dealSpinAttackDamageRight() = dealSpinAttackDamage(SPIN_AREA_ATTACK_RIGHT)

    private fun dealSpinAttackDamage(attackArea: HorizontalRotationRelativeBoxAreaAttack) {
        if (world.isClient) return
        val damage = getAttributeValue(EntityAttributes.ATTACK_DAMAGE).toFloat()
        val knockback = getAttributeValue(EntityAttributes.ATTACK_KNOCKBACK)
        attackArea.dealDamage(this, damage, knockback)
    }
}