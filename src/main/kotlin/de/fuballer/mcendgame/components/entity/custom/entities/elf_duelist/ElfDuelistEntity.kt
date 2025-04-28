package de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist

import de.fuballer.mcendgame.components.entity.custom.goals.StayInRangeGoal
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.MobEntity
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
import kotlin.math.min
import kotlin.math.sin

class ElfDuelistEntity(
    type: EntityType<out ElfDuelistEntity>,
    world: World,
) : PathAwareEntity(type, world), GeoEntity, Monster {
    companion object {
        private val LEAN_ANIM: RawAnimation = RawAnimation.begin().thenLoop("misc.lean")

        private val STAB_RIGHT_ANIM: RawAnimation = RawAnimation.begin().thenPlayAndHold("attack.stab_right")
        private const val STAB_RIGHT_ANIM_NAME = "Stab Right"
        private val STAB_RIGHT_RESET_ANIM: RawAnimation = RawAnimation.begin().thenPlayAndHold("attack.stab_right_reset")
        private const val STAB_RIGHT_RESET_ANIM_NAME = "Stab Right Reset"
        private val ATTACKS = listOf(
            Attack(AttackPose.DEFAULT, AttackPose.STAB_RIGHT, 4, 5, 3.0, 1F, 1.0, STAB_RIGHT_ANIM, STAB_RIGHT_ANIM_NAME),
            Attack(AttackPose.STAB_RIGHT, AttackPose.DEFAULT, -1, 5, -1.0, 0F, 1.0, STAB_RIGHT_RESET_ANIM, STAB_RIGHT_RESET_ANIM_NAME),
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

    private val cache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)

    private val attackAnimationController = AnimationController<GeoAnimatable>("Attack") { _ -> PlayState.STOP }
        .triggerableAnim(STAB_RIGHT_ANIM_NAME, STAB_RIGHT_ANIM)
        .triggerableAnim(STAB_RIGHT_RESET_ANIM_NAME, STAB_RIGHT_RESET_ANIM)

    private var attackPose = AttackPose.DEFAULT
    private var attackDuration = 0

    enum class AttackPose {
        DEFAULT,
        STAB_RIGHT,
    }

    data class Attack(
        val startPose: AttackPose,
        val endPose: AttackPose,
        val damageDelay: Int,
        val totalDuration: Int,
        val hitRange: Double,
        val damageFactor: Float,
        val knockbackFactor: Double,
        val animation: RawAnimation,
        val triggerableName: String,
        val squaredHitRange: Double = hitRange * hitRange
    )

    private val attackDamageInstances = mutableListOf<AttackDamageInstance>()

    private class AttackDamageInstance(
        private var delay: Int,
        private val target: LivingEntity,
        private val squaredRange: Double,
        private val damage: Float,
        private val knockback: Double,
    ) {
        fun tick() = --delay == 0

        fun apply(world: ServerWorld, damager: MobEntity) {
            if (!target.isAlive) return
            val squaredDistance = min(damager.squaredDistanceTo(target), damager.squaredDistanceTo(target.eyePos))
            if (squaredDistance > squaredRange) return

            val damageSource = damager.damageSources.mobAttack(damager)
            target.damage(world, damageSource, damage)

            val knockbackDirection = target.pos.subtract(damager.pos).normalize()
            target.takeKnockback(knockback, -knockbackDirection.x, -knockbackDirection.z)
        }
    }

    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))
        goalSelector.add(1, ElfDuelistAttackGoal(this, ATTACKS))
        goalSelector.add(2, StayInRangeGoal(this, 1.0, 2.5))
        goalSelector.add(3, WanderAroundFarGoal(this, 0.7))
        goalSelector.add(4, LookAtEntityGoal(this, PlayerEntity::class.java, 8F))
        goalSelector.add(4, LookAroundGoal(this))

        targetSelector.add(0, RevengeGoal(this))
        targetSelector.add(1, ActiveTargetGoal(this, PlayerEntity::class.java, true))
        targetSelector.add(2, ActiveTargetGoal(this, VillagerEntity::class.java, true))
    }

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

    override fun getAnimatableInstanceCache() = cache

    override fun tick() {
        super.tick()
        tickAttacks()
    }

    private fun tickAttacks() {
        val world = world as? ServerWorld ?: return

        tickAttackDamageInstances(world)

        if (attackDuration > 0) {
            --attackDuration
            return
        }

        if (target != null && target?.isAlive == true) return
        if (attackPose == AttackPose.DEFAULT) return
        attack(getResetAttack())
    }

    private fun tickAttackDamageInstances(
        world: ServerWorld
    ) {
        val toRemove = mutableListOf<AttackDamageInstance>()
        for (attack in attackDamageInstances) {
            if (!attack.tick()) continue
            attack.apply(world, this)
            toRemove.add(attack)
        }
        attackDamageInstances.removeAll(toRemove)
    }

    private fun getResetAttack() = ATTACKS.filter { it.startPose == attackPose && it.endPose == AttackPose.DEFAULT }.random()

    fun getAttackPose() = attackPose

    fun canAttack() = attackDuration == 0

    fun attack(attack: Attack) {
        if (!canAttack()) return
        if (attackPose != attack.startPose) return

        attackDuration = attack.totalDuration
        attackPose = attack.endPose

        triggerAnim(attackAnimationController.name, attack.triggerableName)

        val existingTarget = target ?: return
        val damage = getAttributeValue(EntityAttributes.ATTACK_DAMAGE).toFloat() * attack.damageFactor
        val knockback = getAttributeValue(EntityAttributes.ATTACK_KNOCKBACK) * attack.knockbackFactor
        val damageInstance = AttackDamageInstance(attack.damageDelay, existingTarget, attack.squaredHitRange, damage, knockback)
        attackDamageInstances.add(damageInstance)
    }
}