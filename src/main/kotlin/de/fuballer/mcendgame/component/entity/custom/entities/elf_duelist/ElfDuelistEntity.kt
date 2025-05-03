package de.fuballer.mcendgame.component.entity.custom.entities.elf_duelist

import de.fuballer.mcendgame.component.entity.custom.attack.Attack
import de.fuballer.mcendgame.component.entity.custom.attack.AttackPose
import de.fuballer.mcendgame.component.entity.custom.attack.LeapAttack
import de.fuballer.mcendgame.component.entity.custom.attack.damage.BasicAttackDamage
import de.fuballer.mcendgame.component.entity.custom.attack.damage.DelayedAttackDamage
import de.fuballer.mcendgame.component.entity.custom.attack.damage.instance.AttackDamageInstance
import de.fuballer.mcendgame.component.entity.custom.attack.data.AttackAnimationData
import de.fuballer.mcendgame.component.entity.custom.attack.trigger_condition.*
import de.fuballer.mcendgame.component.entity.custom.goals.*
import de.fuballer.mcendgame.component.entity.custom.interfaces.BlockAbleMovementMob
import de.fuballer.mcendgame.component.entity.custom.interfaces.CustomAttacksMob
import de.fuballer.mcendgame.component.entity.custom.interfaces.DisableAbleGoalsMob
import de.fuballer.mcendgame.util.random.RandomOption
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.ActiveTargetGoal
import net.minecraft.entity.ai.goal.RevengeGoal
import net.minecraft.entity.ai.goal.SwimGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.Monster
import net.minecraft.entity.mob.PathAwareEntity
import net.minecraft.entity.passive.VillagerEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.World
import software.bernie.geckolib.animatable.GeoAnimatable
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animatable.manager.AnimatableManager
import software.bernie.geckolib.animatable.processing.AnimationController
import software.bernie.geckolib.animation.PlayState
import software.bernie.geckolib.animation.RawAnimation
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.util.GeckoLibUtil
import kotlin.math.sin

class ElfDuelistEntity(
    type: EntityType<out ElfDuelistEntity>,
    world: World,
) : PathAwareEntity(type, world), GeoEntity, DisableAbleGoalsMob, BlockAbleMovementMob<ElfDuelistEntity>, Monster, CustomAttacksMob<ElfDuelistEntity> {
    companion object {
        private val LEAN_ANIM: RawAnimation = RawAnimation.begin().thenLoop("misc.lean")

        private const val EAR_TWITCH_PROBABILITY = 0.005
        private const val EAR_ANIM_CONTROLLED_ID = "Ear"
        private val EAR_TWITCH_LEFT_ANIM: RawAnimation = RawAnimation.begin().thenPlay("misc.ear_twitch_left")
        private const val EAR_TWITCH_LEFT_ID = "Ear Twitch Left"
        private val EAR_TWITCH_RIGHT_ANIM: RawAnimation = RawAnimation.begin().thenPlay("misc.ear_twitch_right")
        private const val EAR_TWITCH_RIGHT_ID = "Ear Twitch Right"

        private const val ATTACK_ANIM_CONTROLLER_ID = "Attack"

        private val ATTACK_DAMAGE = BasicAttackDamage(1F, 1.0, 3.5)

        private val STAB_RIGHT_ANIM: RawAnimation = RawAnimation.begin().thenPlayAndHold("attack.stab_right")
        private const val STAB_RIGHT_ID = "Stab Right"
        private val STAB_RIGHT_ANIM_DATA = AttackAnimationData(AttackPose.DEFAULT, AttackPose.STAB_RIGHT, ATTACK_ANIM_CONTROLLER_ID, STAB_RIGHT_ID)
        private val STAB_RIGHT_ATTACK =
            Attack<ElfDuelistEntity>(
                STAB_RIGHT_ANIM_DATA,
                5,
                0,
                DistanceTriggerCondition(3.0),
                DelayedAttackDamage(ATTACK_DAMAGE, 4),
            )

        private val STAB_RIGHT_RESET_ANIM: RawAnimation = RawAnimation.begin().thenPlayAndHold("attack.stab_right_reset")
        private const val STAB_RIGHT_RESET_ID = "Stab Right Reset"
        private val STAB_RIGHT_RESET_ANIM_DATA = AttackAnimationData(AttackPose.STAB_RIGHT, AttackPose.DEFAULT, ATTACK_ANIM_CONTROLLER_ID, STAB_RIGHT_RESET_ID)
        private val STAB_RIGHT_RESET_ATTACK =
            Attack<ElfDuelistEntity>(
                STAB_RIGHT_RESET_ANIM_DATA,
                5,
                0,
                AlwaysTrueTriggerCondition(),
                null,
            )

        private val STAB_LEFT_ANIM: RawAnimation = RawAnimation.begin().thenPlayAndHold("attack.stab_left")
        private const val STAB_LEFT_ID = "Stab Left"
        private val STAB_LEFT_ANIM_DATA = AttackAnimationData(AttackPose.DEFAULT, AttackPose.STAB_LEFT, ATTACK_ANIM_CONTROLLER_ID, STAB_LEFT_ID)
        private val STAB_LEFT_ATTACK =
            Attack<ElfDuelistEntity>(
                STAB_LEFT_ANIM_DATA,
                5,
                0,
                DistanceTriggerCondition(3.0),
                DelayedAttackDamage(ATTACK_DAMAGE, 4),
            )

        private val STAB_LEFT_RESET_ANIM: RawAnimation = RawAnimation.begin().thenPlayAndHold("attack.stab_left_reset")
        private const val STAB_LEFT_RESET_ID = "Stab Left Reset"
        private val STAB_LEFT_RESET_ANIM_DATA = AttackAnimationData(AttackPose.STAB_LEFT, AttackPose.DEFAULT, ATTACK_ANIM_CONTROLLER_ID, STAB_LEFT_RESET_ID)
        private val STAB_LEFT_RESET_ATTACK =
            Attack<ElfDuelistEntity>(
                STAB_LEFT_RESET_ANIM_DATA,
                5,
                0,
                AlwaysTrueTriggerCondition(),
                null,
            )

        private val STAB_SWAP_LR_ANIM: RawAnimation = RawAnimation.begin().thenPlayAndHold("attack.stab_swap_left_right")
        private const val STAB_SWAP_LR_ID = "Stab Swap Left Right"
        private val STAB_SWAP_LR_ANIM_DATA = AttackAnimationData(AttackPose.STAB_LEFT, AttackPose.STAB_RIGHT, ATTACK_ANIM_CONTROLLER_ID, STAB_SWAP_LR_ID)
        private val STAB_SWAP_LR_ATTACK =
            Attack<ElfDuelistEntity>(
                STAB_SWAP_LR_ANIM_DATA,
                5,
                0,
                DistanceTriggerCondition(3.0),
                DelayedAttackDamage(ATTACK_DAMAGE, 4),
            )

        private val STAB_SWAP_RL_ANIM: RawAnimation = RawAnimation.begin().thenPlayAndHold("attack.stab_swap_right_left")
        private const val STAB_SWAP_RL_ID = "Stab Swap Right Left"
        private val STAB_SWAP_RL_ANIM_DATA = AttackAnimationData(AttackPose.STAB_RIGHT, AttackPose.STAB_LEFT, ATTACK_ANIM_CONTROLLER_ID, STAB_SWAP_RL_ID)
        private val STAB_SWAP_RL_ATTACK =
            Attack<ElfDuelistEntity>(
                STAB_SWAP_RL_ANIM_DATA,
                5,
                0,
                DistanceTriggerCondition(3.0),
                DelayedAttackDamage(ATTACK_DAMAGE, 4),
            )

        private val UPWARDS_SLICE_LEFT_ANIM: RawAnimation = RawAnimation.begin().thenPlayAndHold("attack.upwards_slice_left")
        private const val UPWARDS_SLICE_LEFT_ID = "Upwards Slice Left"
        private val UPWARDS_SLICE_LEFT_ANIM_DATA = AttackAnimationData(AttackPose.DEFAULT, AttackPose.UPWARDS_SLICE_LEFT, ATTACK_ANIM_CONTROLLER_ID, UPWARDS_SLICE_LEFT_ID)
        private val UPWARDS_SLICE_LEFT_ATTACK =
            Attack<ElfDuelistEntity>(
                UPWARDS_SLICE_LEFT_ANIM_DATA,
                5,
                0,
                DistanceTriggerCondition(3.0),
                DelayedAttackDamage(ATTACK_DAMAGE, 4),
            )

        private val UPWARDS_SLICE_LEFT_RESET_ANIM: RawAnimation = RawAnimation.begin().thenPlayAndHold("attack.upwards_slice_left_reset")
        private const val UPWARDS_SLICE_LEFT_RESET_ID = "Upwards Slice Left Reset"
        private val UPWARDS_SLICE_LEFT_RESET_ANIM_DATA = AttackAnimationData(AttackPose.UPWARDS_SLICE_LEFT, AttackPose.DEFAULT, ATTACK_ANIM_CONTROLLER_ID, UPWARDS_SLICE_LEFT_RESET_ID)
        private val UPWARDS_SLICE_LEFT_RESET_ATTACK =
            Attack<ElfDuelistEntity>(
                UPWARDS_SLICE_LEFT_RESET_ANIM_DATA,
                5,
                0,
                AlwaysTrueTriggerCondition(),
                DelayedAttackDamage(ATTACK_DAMAGE, 3),
            )

        private val UPWARDS_SLICE_RIGHT_ANIM: RawAnimation = RawAnimation.begin().thenPlayAndHold("attack.upwards_slice_right")
        private const val UPWARDS_SLICE_RIGHT_ID = "Upwards Slice Right"
        private val UPWARDS_SLICE_RIGHT_ANIM_DATA = AttackAnimationData(AttackPose.DEFAULT, AttackPose.UPWARDS_SLICE_RIGHT, ATTACK_ANIM_CONTROLLER_ID, UPWARDS_SLICE_RIGHT_ID)
        private val UPWARDS_SLICE_RIGHT_ATTACK =
            Attack<ElfDuelistEntity>(
                UPWARDS_SLICE_RIGHT_ANIM_DATA,
                5,
                0,
                DistanceTriggerCondition(3.0),
                DelayedAttackDamage(ATTACK_DAMAGE, 4),
            )

        private val UPWARDS_SLICE_RIGHT_RESET_ANIM: RawAnimation = RawAnimation.begin().thenPlayAndHold("attack.upwards_slice_right_reset")
        private const val UPWARDS_SLICE_RIGHT_RESET_ID = "Upwards Slice Right Reset"
        private val UPWARDS_SLICE_RIGHT_RESET_ANIM_DATA = AttackAnimationData(AttackPose.UPWARDS_SLICE_RIGHT, AttackPose.DEFAULT, ATTACK_ANIM_CONTROLLER_ID, UPWARDS_SLICE_RIGHT_RESET_ID)
        private val UPWARDS_SLICE_RIGHT_RESET_ATTACK =
            Attack<ElfDuelistEntity>(
                UPWARDS_SLICE_RIGHT_RESET_ANIM_DATA,
                5,
                0,
                AlwaysTrueTriggerCondition(),
                DelayedAttackDamage(ATTACK_DAMAGE, 3),
            )

        private val SWEEP_LEFT_ANIM: RawAnimation = RawAnimation.begin().thenPlayAndHold("attack.sweep_left")
        private const val SWEEP_LEFT_ID = "Sweep Left"
        private val SWEEP_LEFT_ANIM_DATA = AttackAnimationData(AttackPose.UPWARDS_SLICE_LEFT, AttackPose.SWEEP_LEFT, ATTACK_ANIM_CONTROLLER_ID, SWEEP_LEFT_ID)
        private val SWEEP_LEFT_ATTACK =
            Attack<ElfDuelistEntity>(
                SWEEP_LEFT_ANIM_DATA,
                5,
                0,
                DistanceTriggerCondition(3.0),
                DelayedAttackDamage(ATTACK_DAMAGE, 2),
            )

        private val SWEEP_LEFT_RESET_ANIM: RawAnimation = RawAnimation.begin().thenPlayAndHold("attack.sweep_left_reset")
        private const val SWEEP_LEFT_RESET_ID = "Sweep Left Reset"
        private val SWEEP_LEFT_RESET_ANIM_DATA = AttackAnimationData(AttackPose.SWEEP_LEFT, AttackPose.DEFAULT, ATTACK_ANIM_CONTROLLER_ID, SWEEP_LEFT_RESET_ID)
        private val SWEEP_LEFT_RESET_ATTACK =
            Attack<ElfDuelistEntity>(
                SWEEP_LEFT_RESET_ANIM_DATA,
                5,
                0,
                AlwaysTrueTriggerCondition(),
                null,
            )

        private val SWEEP_RIGHT_ANIM: RawAnimation = RawAnimation.begin().thenPlayAndHold("attack.sweep_right")
        private const val SWEEP_RIGHT_ID = "Sweep Right"
        private val SWEEP_RIGHT_ANIM_DATA = AttackAnimationData(AttackPose.UPWARDS_SLICE_RIGHT, AttackPose.SWEEP_RIGHT, ATTACK_ANIM_CONTROLLER_ID, SWEEP_RIGHT_ID)
        private val SWEEP_RIGHT_ATTACK =
            Attack<ElfDuelistEntity>(
                SWEEP_RIGHT_ANIM_DATA,
                5,
                0,
                DistanceTriggerCondition(3.0),
                DelayedAttackDamage(ATTACK_DAMAGE, 2),
            )

        private val SWEEP_RIGHT_RESET_ANIM: RawAnimation = RawAnimation.begin().thenPlayAndHold("attack.sweep_right_reset")
        private const val SWEEP_RIGHT_RESET_ID = "Sweep Right Reset"
        private val SWEEP_RIGHT_RESET_ANIM_DATA = AttackAnimationData(AttackPose.SWEEP_RIGHT, AttackPose.DEFAULT, ATTACK_ANIM_CONTROLLER_ID, SWEEP_RIGHT_RESET_ID)
        private val SWEEP_RIGHT_RESET_ATTACK =
            Attack<ElfDuelistEntity>(
                SWEEP_RIGHT_RESET_ANIM_DATA,
                5,
                0,
                AlwaysTrueTriggerCondition(),
                null,
            )

        private val LEAP_TRIGGER_CONDITION = TriggerConditionGroup(
            TriggerConditionGroup.TriggerConditionJoinType.OR,
            listOf(
                TriggerConditionGroup(
                    TriggerConditionGroup.TriggerConditionJoinType.AND,
                    listOf(
                        YDistanceTriggerCondition(-2.5, 2.5),
                        DistanceTriggerCondition(5.0, 10.0),
                    )
                ),
                TriggerConditionGroup(
                    TriggerConditionGroup.TriggerConditionJoinType.AND,
                    listOf(
                        YDistanceTriggerCondition(2.5, 5.2),
                        HorizontalDistanceTriggerCondition(3.0),
                    )
                )
            )
        )

        private val LEAP_RIGHT_ANIM: RawAnimation = RawAnimation.begin().thenPlayAndHold("attack.stab_right")
        private const val LEAP_RIGHT_ID = "Leap Right"
        private val LEAP_RIGHT_ANIM_DATA = AttackAnimationData(AttackPose.DEFAULT, AttackPose.STAB_RIGHT, ATTACK_ANIM_CONTROLLER_ID, LEAP_RIGHT_ID)
        private val LEAP_RIGHT_ATTACK =
            LeapAttack<ElfDuelistEntity>(
                LEAP_RIGHT_ANIM_DATA,
                15,
                0,
                LEAP_TRIGGER_CONDITION,
                DelayedAttackDamage(ATTACK_DAMAGE, 4, 10),
                LeapAttack.LeapType.BASIC,
                10
            )

        private val LEAP_LEFT_ANIM: RawAnimation = RawAnimation.begin().thenPlayAndHold("attack.stab_left")
        private const val LEAP_LEFT_ID = "Leap Left"
        private val LEAP_LEFT_ANIM_DATA = AttackAnimationData(AttackPose.DEFAULT, AttackPose.STAB_LEFT, ATTACK_ANIM_CONTROLLER_ID, LEAP_LEFT_ID)
        private val LEAP_LEFT_ATTACK =
            LeapAttack<ElfDuelistEntity>(
                LEAP_LEFT_ANIM_DATA,
                15,
                0,
                LEAP_TRIGGER_CONDITION,
                DelayedAttackDamage(ATTACK_DAMAGE, 4, 10),
                LeapAttack.LeapType.BASIC,
                10
            )

        private val ATTACKS = listOf(
            RandomOption(1, STAB_RIGHT_ATTACK),
            RandomOption(1, STAB_RIGHT_RESET_ATTACK),
            RandomOption(1, STAB_LEFT_ATTACK),
            RandomOption(1, STAB_LEFT_RESET_ATTACK),
            RandomOption(1, STAB_SWAP_LR_ATTACK),
            RandomOption(1, STAB_SWAP_RL_ATTACK),
            RandomOption(1, UPWARDS_SLICE_LEFT_ATTACK),
            RandomOption(1, UPWARDS_SLICE_LEFT_RESET_ATTACK),
            RandomOption(1, UPWARDS_SLICE_RIGHT_ATTACK),
            RandomOption(1, UPWARDS_SLICE_RIGHT_RESET_ATTACK),
            RandomOption(2, SWEEP_LEFT_ATTACK),
            RandomOption(1, SWEEP_LEFT_RESET_ATTACK),
            RandomOption(2, SWEEP_RIGHT_ATTACK),
            RandomOption(1, SWEEP_RIGHT_RESET_ATTACK),
            RandomOption(1, LEAP_RIGHT_ATTACK),
            RandomOption(1, LEAP_LEFT_ATTACK),
        )

        fun createAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.FOLLOW_RANGE, 35.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.ATTACK_DAMAGE, 3.0)
                .add(EntityAttributes.ATTACK_KNOCKBACK, 0.3)
                .add(EntityAttributes.ARMOR, 0.0)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, 0.8)
                .add(EntityAttributes.MOVEMENT_EFFICIENCY, 0.85)
                .add(EntityAttributes.SAFE_FALL_DISTANCE, 10.0)
                .add(EntityAttributes.FALL_DAMAGE_MULTIPLIER, 0.1)
        }
    }

    override var attackPose = AttackPose.DEFAULT
    override var attackDuration = 0
    override val attacks = ATTACKS
    override val attackCooldowns: MutableMap<Attack<ElfDuelistEntity>, Int> = mutableMapOf()
    override val attackDamageInstances = mutableListOf<AttackDamageInstance>()

    override var blockAbleMovementEntity = this
    override var blockedMovementTicks = 0
    override var blockedMovementAirborne = false

    private val cache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)
    override fun getAnimatableInstanceCache() = cache

    private val earAnimationController = AnimationController<GeoAnimatable>(EAR_ANIM_CONTROLLED_ID) { _ -> PlayState.STOP }
        .triggerableAnim(EAR_TWITCH_LEFT_ID, EAR_TWITCH_LEFT_ANIM)
        .triggerableAnim(EAR_TWITCH_RIGHT_ID, EAR_TWITCH_RIGHT_ANIM)

    private val attackAnimationController = AnimationController<GeoAnimatable>(ATTACK_ANIM_CONTROLLER_ID) { _ -> PlayState.STOP }
        .triggerableAnim(STAB_RIGHT_ID, STAB_RIGHT_ANIM)
        .triggerableAnim(STAB_RIGHT_RESET_ID, STAB_RIGHT_RESET_ANIM)
        .triggerableAnim(STAB_LEFT_ID, STAB_LEFT_ANIM)
        .triggerableAnim(STAB_LEFT_RESET_ID, STAB_LEFT_RESET_ANIM)
        .triggerableAnim(STAB_SWAP_LR_ID, STAB_SWAP_LR_ANIM)
        .triggerableAnim(STAB_SWAP_RL_ID, STAB_SWAP_RL_ANIM)
        .triggerableAnim(UPWARDS_SLICE_LEFT_ID, UPWARDS_SLICE_LEFT_ANIM)
        .triggerableAnim(UPWARDS_SLICE_LEFT_RESET_ID, UPWARDS_SLICE_LEFT_RESET_ANIM)
        .triggerableAnim(UPWARDS_SLICE_RIGHT_ID, UPWARDS_SLICE_RIGHT_ANIM)
        .triggerableAnim(UPWARDS_SLICE_RIGHT_RESET_ID, UPWARDS_SLICE_RIGHT_RESET_ANIM)
        .triggerableAnim(SWEEP_LEFT_ID, SWEEP_LEFT_ANIM)
        .triggerableAnim(SWEEP_LEFT_RESET_ID, SWEEP_LEFT_RESET_ANIM)
        .triggerableAnim(SWEEP_RIGHT_ID, SWEEP_RIGHT_ANIM)
        .triggerableAnim(SWEEP_RIGHT_RESET_ID, SWEEP_RIGHT_RESET_ANIM)
        .triggerableAnim(LEAP_RIGHT_ID, LEAP_RIGHT_ANIM)
        .triggerableAnim(LEAP_LEFT_ID, LEAP_LEFT_ANIM)

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController<ElfDuelistEntity>("Walk/Idle", 5)
            { test -> test.setAndContinue(if (test.isMoving) DefaultAnimations.WALK else DefaultAnimations.IDLE) },

            AnimationController<ElfDuelistEntity>("Lean", 5)
            { test -> if (test.isMoving) test.setAndContinue(LEAN_ANIM) else PlayState.STOP },

            earAnimationController,
            attackAnimationController,
        )
    }

    fun getLimbSwingCycle(tickProgress: Float) = sin(limbAnimator.getAnimationProgress(tickProgress) / 1.5)
    fun getLimbSwingAmplitude(tickProgress: Float) = limbAnimator.getAmplitude(tickProgress).toDouble()

    fun getLean(tickProgress: Float) = getLimbSwingAmplitude(tickProgress) * if (target == null) 0.5 else 1.0

    private val attackGoal = CustomAttacksGoal(this)
    private val stayInMeleeRangeGoal = StayInRangeGoal(this, 1.0, 2.5)
    private val wanderGoal = DisableAbleWanderAroundFarGoal(this, 0.7)
    private val lookAtPlayerGoal = DisableAbleLookAtEntityGoal(this, PlayerEntity::class.java, 8F)
    private val lookAroundGoal = DisableAbleLookAroundGoal(this)

    init {
        initDynamicGoals()
    }

    private fun initDynamicGoals() {
        goalSelector.add(1, attackGoal)
        goalSelector.add(2, stayInMeleeRangeGoal)
        goalSelector.add(3, wanderGoal)
        goalSelector.add(4, lookAtPlayerGoal)
        goalSelector.add(4, lookAroundGoal)
    }

    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))

        targetSelector.add(0, RevengeGoal(this))
        targetSelector.add(1, ActiveTargetGoal(this, PlayerEntity::class.java, true))
        targetSelector.add(2, ActiveTargetGoal(this, VillagerEntity::class.java, true))
    }

    override fun updateGoals() {
        val movementBlocked = isMovementBlocked()
        attackGoal.isDisabled = movementBlocked
        stayInMeleeRangeGoal.isDisabled = movementBlocked
        wanderGoal.isDisabled = movementBlocked
        lookAtPlayerGoal.isDisabled = movementBlocked
        lookAroundGoal.isDisabled = movementBlocked
    }

    override fun tick() {
        super.tick()
        val world = world as? ServerWorld ?: return
        tickBlockedMovement()
        tickAttacks(world, this)
        tickEars()
    }

    private fun tickEars() {
        if (random.nextDouble() > EAR_TWITCH_PROBABILITY) return
        if (random.nextBoolean()) triggerAnim(EAR_ANIM_CONTROLLED_ID, EAR_TWITCH_LEFT_ID)
        else triggerAnim(EAR_ANIM_CONTROLLED_ID, EAR_TWITCH_RIGHT_ID)
    }
}