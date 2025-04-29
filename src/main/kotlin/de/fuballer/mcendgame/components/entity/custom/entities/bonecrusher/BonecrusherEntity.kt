package de.fuballer.mcendgame.components.entity.custom.entities.bonecrusher

import de.fuballer.mcendgame.components.entity.custom.attack.CustomAreaAttack
import de.fuballer.mcendgame.components.entity.custom.attack.CustomAttackDamageInstance
import de.fuballer.mcendgame.components.entity.custom.attack.CustomAttackPose
import de.fuballer.mcendgame.components.entity.custom.attack.CustomBasicAttack
import de.fuballer.mcendgame.components.entity.custom.goals.*
import de.fuballer.mcendgame.components.entity.custom.interfaces.CustomAttacksMob
import de.fuballer.mcendgame.components.entity.custom.interfaces.DisableAbleGoalsMob
import de.fuballer.mcendgame.components.entity.custom.util.BlockedMovementManager
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.ActiveTargetGoal
import net.minecraft.entity.ai.goal.SwimGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.Monster
import net.minecraft.entity.mob.PathAwareEntity
import net.minecraft.entity.passive.VillagerEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerWorld
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
) : PathAwareEntity(type, world), GeoEntity, DisableAbleGoalsMob, Monster, CustomAttacksMob<BonecrusherEntity> {
    companion object {
        const val SPIN_ATTACK_DURATION = 100

        val WALK_ANIM: RawAnimation = RawAnimation.begin().thenLoop("movement.walk")

        /*val SPIN_START_ANIM: RawAnimation = RawAnimation.begin().thenPlay("attack.spin.start")
        val SPIN_ANIM: RawAnimation = RawAnimation.begin().thenPlay("attack.spin")
        val SPIN_END_ANIM: RawAnimation = RawAnimation.begin().thenPlay("attack.spin.end")
        */

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

        /*
        val SPIN_AREA_ATTACK_FRONT = CustomAreaAttack(
            4.0, 3.0, 1.0, 0.0, 0.0, 0.5,
            knockbackType = CustomAreaAttack.KnockbackType.DAMAGER_CENTER
        )
        val SPIN_AREA_ATTACK_BACK = CustomAreaAttack(
            4.0, 3.0, 1.0, -4.0, 0.0, 0.5,
            knockbackType = CustomAreaAttack.KnockbackType.DAMAGER_CENTER
        )
        val SPIN_AREA_ATTACK_LEFT = CustomAreaAttack(
            6.0, 2.0, 1.0, -3.0, -2.0, 0.5,
            knockbackType = CustomAreaAttack.KnockbackType.DAMAGER_CENTER
        )
        val SPIN_AREA_ATTACK_RIGHT = CustomAreaAttack(
            6.0, 2.0, 1.0, -3.0, 2.0, 0.5,
            knockbackType = CustomAreaAttack.KnockbackType.DAMAGER_CENTER
        )*/

        private const val ATTACK_ANIM_CONTROLLER_ID = "Attack"

        private val HIT_ANIM: RawAnimation = RawAnimation.begin().thenPlay("attack.hit")
        private const val HIT_ID = "Hit"
        private val HIT_AREA = CustomAreaAttack.DamageArea(3.0, 1.3, 1.5, 0.0, 0.5, 0.5)
        private val HIT_ATTACK = CustomAreaAttack(
            CustomAttackPose.DEFAULT,
            CustomAttackPose.DEFAULT,
            4,
            20,
            3.0,
            0.5F,
            0.35,
            ATTACK_ANIM_CONTROLLER_ID,
            HIT_ID,
            HIT_AREA,
            true,
            CustomAreaAttack.KnockbackType.FACING
        )

        val SLAM_ANIM: RawAnimation = RawAnimation.begin().thenPlay("attack.slam")
        private const val SLAM_ID = "Slam"
        private val SLAM_AREA = CustomAreaAttack.DamageArea(5.0, 2.5, 1.5, 1.0, 0.0, 0.5)
        private val SLAM_ATTACK = CustomAreaAttack(
            CustomAttackPose.DEFAULT,
            CustomAttackPose.DEFAULT,
            17,
            40,
            4.0,
            1.0F,
            1.0,
            ATTACK_ANIM_CONTROLLER_ID,
            SLAM_ID,
            SLAM_AREA,
            true,
            CustomAreaAttack.KnockbackType.AREA_CENTER
        ).setParticles(100, 0.25, ParticleTypes.CRIT, 0.5)
            .setSound(false, SoundEvents.ENTITY_GENERIC_EXPLODE.value(), 1F, 1F)

        private val ATTACKS = listOf(
            HIT_ATTACK,
            SLAM_ATTACK,
        )
        private val RESET_ATTACKS = listOf<CustomBasicAttack>()
    }

    private val cache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)
    override fun getAnimatableInstanceCache() = cache

    private val attackGoal = CustomAttacksGoal(this)
    private val stayInMeleeRangeGoal = StayInRangeGoal(this, 1.0, 2.5)

    private val wanderGoal = DisableAbleWanderAroundFarGoal(this, 1.0)
    private val lookAtPlayerGoal = DisableAbleLookAtEntityGoal(this, PlayerEntity::class.java, 8F)
    private val lookAroundGoal = DisableAbleLookAroundGoal(this)

    private val blockedMovementManager = BlockedMovementManager(this)

    private enum class SpinAttackState {
        NONE,
        START,
        SPIN,
        END,
    }

    private var spinAttackState = SpinAttackState.NONE
    private var spinAttackDuration = 0

    override var attackPose = CustomAttackPose.DEFAULT
    override var attackDuration = 0
    override val attacks = ATTACKS
    override val resetAttacks = RESET_ATTACKS
    override val attackDamageInstances = mutableListOf<CustomAttackDamageInstance>()

    override fun initDataTracker(builder: DataTracker.Builder) {
        super.initDataTracker(builder)
        builder.add(IS_SPIN_ATTACKING, false)
    }

    override fun onTrackedDataSet(data: TrackedData<*>) {
        if (data == IS_SPIN_ATTACKING) startSpinAttackAnimation()
        super.onTrackedDataSet(data)
    }

    init {
        initDynamicGoals()
    }

    private fun initDynamicGoals() {
        goalSelector.add(1, attackGoal)
        goalSelector.add(2, stayInMeleeRangeGoal)
        goalSelector.add(3, lookAtPlayerGoal)
        goalSelector.add(3, lookAroundGoal)
        goalSelector.add(4, wanderGoal)
    }

    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))

        targetSelector.add(2, ActiveTargetGoal(this, PlayerEntity::class.java, true))
        targetSelector.add(3, ActiveTargetGoal(this, VillagerEntity::class.java, true))
    }

    override fun updateGoals() {
        val movementBlocked = blockedMovementManager.isBlocked()
        attackGoal.isDisabled = movementBlocked
        stayInMeleeRangeGoal.isDisabled = movementBlocked
        wanderGoal.isDisabled = movementBlocked
        lookAtPlayerGoal.isDisabled = movementBlocked
        lookAroundGoal.isDisabled = movementBlocked
    }

    override fun tick() {
        super.tick()
        tickSpinAttack()
        blockedMovementManager.tick()

        val world = world as? ServerWorld ?: return
        tickAttacks(world, this)
    }

    private fun tickSpinAttack() {
        if (world.isClient) return

        if (spinAttackDuration == 0) return
        if (--spinAttackDuration > 0) return
        dataTracker.set(IS_SPIN_ATTACKING, false)
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController("Movement", 5, ::movementAnimationController),
            AnimationController<GeoAnimatable>(ATTACK_ANIM_CONTROLLER_ID) { _ -> PlayState.STOP }
                .triggerableAnim(HIT_ID, HIT_ANIM)
                .triggerableAnim(SLAM_ID, SLAM_ANIM),
            //AnimationController("Spin", 0, ::spinAnimationController)
        )
    }

    private fun movementAnimationController(animTest: AnimationTest<BonecrusherEntity>): PlayState {
        if (spinAttackState != SpinAttackState.NONE) return PlayState.STOP
        if (animTest.isMoving) return animTest.setAndContinue(WALK_ANIM)
        return PlayState.STOP
    }

    private fun startSpinAttackAnimation() {
        if (!dataTracker.get(IS_SPIN_ATTACKING)) return
        if (spinAttackState != SpinAttackState.NONE) return
        spinAttackState = SpinAttackState.START
    }

    /*
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
    }*/

    /*
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
    }*/
}