package de.fuballer.mcendgame.component.entity.custom.entities.bonecrusher

import de.fuballer.mcendgame.component.entity.custom.attack.*
import de.fuballer.mcendgame.component.entity.custom.goals.*
import de.fuballer.mcendgame.component.entity.custom.interfaces.CustomAttacksMob
import de.fuballer.mcendgame.component.entity.custom.interfaces.DisableAbleGoalsMob
import de.fuballer.mcendgame.component.entity.custom.util.BlockedMovementManager
import de.fuballer.mcendgame.util.random.RandomOption
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.ActiveTargetGoal
import net.minecraft.entity.ai.goal.SwimGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
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
        val WALK_ANIM: RawAnimation = RawAnimation.begin().thenLoop("movement.walk")

        private const val ATTACK_ANIM_CONTROLLER_ID = "Attack"
        private const val SPIN_ANIM_CONTROLLER_ID = "Spin"

        private val HIT_ANIM: RawAnimation = RawAnimation.begin().thenPlay("attack.hit")
        private const val HIT_ID = "Hit"
        private val HIT_AREA = CustomAreaAttackDamage.DamageArea(3.5, 1.4, 1.5, 0.0, 0.5, 0.5)
        private val HIT_ATTACK_DAMAGE = CustomAreaAttackDamage(0.5F, 0.35, HIT_AREA, knockbackType = CustomAreaAttackDamage.KnockbackType.FACING)
        private val HIT_ATTACK = CustomAttack(
            CustomAttackPose.DEFAULT,
            CustomAttackPose.DEFAULT,
            20,
            0,
            3.0,
            Pair(4, HIT_ATTACK_DAMAGE),
            ATTACK_ANIM_CONTROLLER_ID,
            HIT_ID,
        )

        private val SLAM_ANIM: RawAnimation = RawAnimation.begin().thenPlay("attack.slam")
        private const val SLAM_ID = "Slam"
        private val SLAM_AREA = CustomAreaAttackDamage.DamageArea(5.0, 2.5, 1.5, 1.0, 0.0, 0.5)
        private val SLAM_ATTACK_DAMAGE = CustomAreaAttackDamage(1F, 1.0, SLAM_AREA, knockbackType = CustomAreaAttackDamage.KnockbackType.AREA_CENTER)
            .setParticles(100, 0.25, ParticleTypes.CRIT, 0.5)
            .setSound(false, SoundEvents.ENTITY_GENERIC_EXPLODE.value(), 1F, 1F)
        private val SLAM_ATTACK = CustomAttack(
            CustomAttackPose.DEFAULT,
            CustomAttackPose.DEFAULT,
            40,
            100,
            4.0,
            Pair(17, SLAM_ATTACK_DAMAGE),
            ATTACK_ANIM_CONTROLLER_ID,
            SLAM_ID,
        )

        const val SPIN_ATTACK_ROTATIONS = 3
        private val SPIN_START_ANIM: RawAnimation = RawAnimation.begin().thenPlay("attack.spin.start")
        private val SPIN_ANIM: RawAnimation = RawAnimation.begin().thenPlay("attack.spin")
        private val SPIN_END_ANIM: RawAnimation = RawAnimation.begin().thenPlay("attack.spin.end")
        private const val SPIN_START_ID = "Start Spin"
        private val SPIN_FRONT_AREA = CustomAreaAttackDamage.DamageArea(4.0, 3.0, 1.0, 0.0, 0.0, 0.5)
        private val SPIN_FRONT_DAMAGE = CustomAreaAttackDamage(1F, 0.25, SPIN_FRONT_AREA, knockbackType = CustomAreaAttackDamage.KnockbackType.DAMAGER_CENTER)
        private val SPIN_BACK_AREA = CustomAreaAttackDamage.DamageArea(4.0, 3.0, 1.0, -4.0, 0.0, 0.5)
        private val SPIN_BACK_DAMAGE = CustomAreaAttackDamage(1F, 0.25, SPIN_BACK_AREA, knockbackType = CustomAreaAttackDamage.KnockbackType.DAMAGER_CENTER)
        private val SPIN_LEFT_AREA = CustomAreaAttackDamage.DamageArea(6.0, 2.0, 1.0, -3.0, -2.0, 0.5)
        private val SPIN_LEFT_DAMAGE = CustomAreaAttackDamage(1F, 0.25, SPIN_LEFT_AREA, knockbackType = CustomAreaAttackDamage.KnockbackType.DAMAGER_CENTER)
        private val SPIN_RIGHT_AREA = CustomAreaAttackDamage.DamageArea(6.0, 2.0, 1.0, -3.0, 2.0, 0.5)
        private val SPIN_RIGHT_DAMAGE = CustomAreaAttackDamage(1F, 0.25, SPIN_RIGHT_AREA, knockbackType = CustomAreaAttackDamage.KnockbackType.DAMAGER_CENTER)
        private val SPIN_ATTACK = CustomAttack(
            CustomAttackPose.DEFAULT,
            CustomAttackPose.DEFAULT,
            50 + 13 * SPIN_ATTACK_ROTATIONS,
            50 + 13 * SPIN_ATTACK_ROTATIONS + 200,
            3.0,
            getSpinAttackDamage(),
            SPIN_ANIM_CONTROLLER_ID,
            SPIN_START_ID,
        )

        private fun getSpinAttackDamage(): List<Pair<Int, CustomAttackDamage>> {
            val damage = mutableListOf<Pair<Int, CustomAttackDamage>>()

            // spin start
            damage.add(Pair(5, SPIN_LEFT_DAMAGE))
            damage.add(Pair(9, SPIN_BACK_DAMAGE))
            damage.add(Pair(13, SPIN_RIGHT_DAMAGE))

            // main spin
            for (rot in 0 until SPIN_ATTACK_ROTATIONS) {
                val base = 16 + (rot * 20 * 2 / 3.0).toInt()
                damage.add(Pair(base, SPIN_FRONT_DAMAGE))
                damage.add(Pair(base + 3, SPIN_LEFT_DAMAGE))
                damage.add(Pair(base + 6, SPIN_BACK_DAMAGE))
                damage.add(Pair(base + 10, SPIN_RIGHT_DAMAGE))
            }

            // spin end
            val base = 16 + (SPIN_ATTACK_ROTATIONS * 20 * 2 / 3.0).toInt()
            damage.add(Pair(base, SPIN_FRONT_DAMAGE))
            damage.add(Pair(base + 5, SPIN_LEFT_DAMAGE))
            damage.add(Pair(base + 10, SPIN_BACK_DAMAGE))

            return damage
        }

        private val ATTACKS = listOf(
            RandomOption(5, HIT_ATTACK),
            RandomOption(2, SLAM_ATTACK),
            RandomOption(1, SPIN_ATTACK),
        )

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
    private var spinRotationCount = 0

    override var attackPose = CustomAttackPose.DEFAULT
    override var attackDuration = 0
    override val attacks = ATTACKS
    override val attackCooldowns: MutableMap<CustomAttack, Int> = mutableMapOf()
    override val attackDamageInstances = mutableListOf<CustomAttackDamageInstance>()

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
        blockedMovementManager.tick()

        val world = world as? ServerWorld ?: return
        tickAttacks(world, this)
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController("Movement", 5, ::movementAnimationController),
            AnimationController<GeoAnimatable>(ATTACK_ANIM_CONTROLLER_ID, 0) { _ -> PlayState.STOP }
                .triggerableAnim(HIT_ID, HIT_ANIM)
                .triggerableAnim(SLAM_ID, SLAM_ANIM),
            AnimationController(SPIN_ANIM_CONTROLLER_ID, 0, ::spinAnimationController)
                .triggerableAnim(SPIN_START_ID, SPIN_START_ANIM)
        )
    }

    private fun movementAnimationController(animTest: AnimationTest<BonecrusherEntity>): PlayState {
        if (spinAttackState != SpinAttackState.NONE) return PlayState.STOP
        if (animTest.isMoving) return animTest.setAndContinue(WALK_ANIM)
        return PlayState.STOP
    }

    private fun spinAnimationController(animTest: AnimationTest<BonecrusherEntity>): PlayState {
        val controller = animTest.controller

        if (controller.currentRawAnimation == SPIN_START_ANIM && spinAttackState == SpinAttackState.NONE) {
            spinAttackState = SpinAttackState.START
            controller.stopTriggeredAnimation()
        }

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
                    if (++spinRotationCount >= SPIN_ATTACK_ROTATIONS) {
                        spinRotationCount = 0
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
}