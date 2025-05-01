package de.fuballer.mcendgame.component.entity.custom.entities.elf_duelist

import de.fuballer.mcendgame.component.entity.custom.attack.CustomAttack
import de.fuballer.mcendgame.component.entity.custom.attack.CustomAttackDamageInstance
import de.fuballer.mcendgame.component.entity.custom.attack.CustomAttackPose
import de.fuballer.mcendgame.component.entity.custom.attack.CustomBasicAttackDamage
import de.fuballer.mcendgame.component.entity.custom.goals.CustomAttacksGoal
import de.fuballer.mcendgame.component.entity.custom.goals.StayInRangeGoal
import de.fuballer.mcendgame.component.entity.custom.interfaces.CustomAttacksMob
import de.fuballer.mcendgame.util.random.RandomOption
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.*
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
) : PathAwareEntity(type, world), GeoEntity, Monster, CustomAttacksMob<ElfDuelistEntity> {
    companion object {
        private val LEAN_ANIM: RawAnimation = RawAnimation.begin().thenLoop("misc.lean")

        private const val ATTACK_ANIM_CONTROLLER_ID = "Attack"

        private val STAB_ATTACK_DAMAGE = CustomBasicAttackDamage(1F, 1.0, 3.5)

        private val STAB_RIGHT_ANIM: RawAnimation = RawAnimation.begin().thenPlayAndHold("attack.stab_right")
        private const val STAB_RIGHT_ID = "Stab Right"
        private val STAB_RIGHT_ATTACK = CustomAttack(CustomAttackPose.DEFAULT, CustomAttackPose.STAB_RIGHT, 5, 0, 3.0, Pair(4, STAB_ATTACK_DAMAGE), ATTACK_ANIM_CONTROLLER_ID, STAB_RIGHT_ID)

        private val STAB_RIGHT_RESET_ANIM: RawAnimation = RawAnimation.begin().thenPlayAndHold("attack.stab_right_reset")
        private const val STAB_RIGHT_RESET_ID = "Stab Right Reset"
        private val STAB_RIGHT_RESET_ATTACK = CustomAttack(CustomAttackPose.STAB_RIGHT, CustomAttackPose.DEFAULT, 5, 0, -1.0, null, ATTACK_ANIM_CONTROLLER_ID, STAB_RIGHT_RESET_ID)

        private val STAB_LEFT_ANIM: RawAnimation = RawAnimation.begin().thenPlayAndHold("attack.stab_left")
        private const val STAB_LEFT_ID = "Stab Left"
        private val STAB_LEFT_ATTACK = CustomAttack(CustomAttackPose.DEFAULT, CustomAttackPose.STAB_LEFT, 5, 0, 3.0, Pair(4, STAB_ATTACK_DAMAGE), ATTACK_ANIM_CONTROLLER_ID, STAB_LEFT_ID)

        private val STAB_LEFT_RESET_ANIM: RawAnimation = RawAnimation.begin().thenPlayAndHold("attack.stab_left_reset")
        private const val STAB_LEFT_RESET_ID = "Stab Left Reset"
        private val STAB_LEFT_RESET_ATTACK = CustomAttack(CustomAttackPose.STAB_LEFT, CustomAttackPose.DEFAULT, 5, 0, -1.0, null, ATTACK_ANIM_CONTROLLER_ID, STAB_LEFT_RESET_ID)

        private val ATTACKS = listOf(
            RandomOption(1, STAB_RIGHT_ATTACK),
            RandomOption(1, STAB_RIGHT_RESET_ATTACK),
            RandomOption(1, STAB_LEFT_ATTACK),
            RandomOption(1, STAB_LEFT_RESET_ATTACK),
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
        }
    }

    override var attackPose = CustomAttackPose.DEFAULT
    override var attackDuration = 0
    override val attacks = ATTACKS
    override val attackCooldowns: MutableMap<CustomAttack, Int> = mutableMapOf()
    override val attackDamageInstances = mutableListOf<CustomAttackDamageInstance>()

    private val cache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)
    override fun getAnimatableInstanceCache() = cache

    private val attackAnimationController = AnimationController<GeoAnimatable>(ATTACK_ANIM_CONTROLLER_ID) { _ -> PlayState.STOP }
        .triggerableAnim(STAB_RIGHT_ID, STAB_RIGHT_ANIM)
        .triggerableAnim(STAB_RIGHT_RESET_ID, STAB_RIGHT_RESET_ANIM)
        .triggerableAnim(STAB_LEFT_ID, STAB_LEFT_ANIM)
        .triggerableAnim(STAB_LEFT_RESET_ID, STAB_LEFT_RESET_ANIM)

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController<ElfDuelistEntity>("Walk/Idle", 5)
            { test -> test.setAndContinue(if (test.isMoving) DefaultAnimations.WALK else DefaultAnimations.IDLE) },

            AnimationController<ElfDuelistEntity>("Lean", 5)
            { test -> if (test.isMoving) test.setAndContinue(LEAN_ANIM) else PlayState.STOP },

            attackAnimationController,
        )
    }

    fun getLimbSwingCycle(tickProgress: Float) = sin(limbAnimator.getAnimationProgress(tickProgress) / 1.5)
    fun getLimbSwingAmplitude(tickProgress: Float) = limbAnimator.getAmplitude(tickProgress).toDouble()

    fun getLean(tickProgress: Float) = getLimbSwingAmplitude(tickProgress) * if (target == null) 0.5 else 1.0

    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))
        goalSelector.add(1, CustomAttacksGoal(this))
        goalSelector.add(2, StayInRangeGoal(this, 1.0, 2.5))
        goalSelector.add(3, WanderAroundFarGoal(this, 0.7))
        goalSelector.add(4, LookAtEntityGoal(this, PlayerEntity::class.java, 8F))
        goalSelector.add(4, LookAroundGoal(this))

        targetSelector.add(0, RevengeGoal(this))
        targetSelector.add(1, ActiveTargetGoal(this, PlayerEntity::class.java, true))
        targetSelector.add(2, ActiveTargetGoal(this, VillagerEntity::class.java, true))
    }

    override fun tick() {
        super.tick()
        val world = world as? ServerWorld ?: return
        tickAttacks(world, this)
    }
}