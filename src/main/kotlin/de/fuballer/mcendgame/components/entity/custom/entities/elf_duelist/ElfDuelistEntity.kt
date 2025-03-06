package de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist

import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandler
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.entity.passive.ChickenEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.MathHelper
import net.minecraft.world.World

class ElfDuelistEntity(
    type: EntityType<out ElfDuelistEntity>,
    world: World,
) : HostileEntity(type, world) {
    companion object {
        val FULL_IDLE_TICKS = 10
        val IDLE_TICKS = DataTracker.registerData(ElfDuelistEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

        val HAS_TARGET = DataTracker.registerData(ElfDuelistEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)
        val TARGET_GAINED = DataTracker.registerData(ElfDuelistEntity::class.java, TrackedDataHandlerRegistry.LONG)
        val TARGET_LOST = DataTracker.registerData(ElfDuelistEntity::class.java, TrackedDataHandlerRegistry.LONG)

        val ATTACK_POSE_TDH: TrackedDataHandler<ElfDuelistAttackPose> =
            TrackedDataHandler.create(ElfDuelistAttackPose.PACKET_CODEC)
                .also { TrackedDataHandlerRegistry.register(it) }
        val ATTACK_POSE = DataTracker.registerData(ElfDuelistEntity::class.java, ATTACK_POSE_TDH)
        val PREVIOUS_ATTACK_POSE = DataTracker.registerData(ElfDuelistEntity::class.java, ATTACK_POSE_TDH)
        val ATTACK_POSE_CHANGED =
            DataTracker.registerData(ElfDuelistEntity::class.java, TrackedDataHandlerRegistry.LONG)

        fun createAttributes(): DefaultAttributeContainer.Builder {
            return createHostileAttributes()
                .add(EntityAttributes.FOLLOW_RANGE, 35.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.28)
                .add(EntityAttributes.ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.ARMOR, 0.0)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, 0.2)
        }
    }

    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))
        goalSelector.add(1, ElfDuelistAttackGoal(this))
        goalSelector.add(7, WanderAroundFarGoal(this, 1.0))
        goalSelector.add(8, LookAtEntityGoal(this, PlayerEntity::class.java, 8.0f))
        goalSelector.add(8, LookAroundGoal(this))

        targetSelector.add(1, ActiveTargetGoal(this, PlayerEntity::class.java, true))
        targetSelector.add(1, ActiveTargetGoal(this, ChickenEntity::class.java, true))
    }

    override fun initDataTracker(builder: DataTracker.Builder) {
        super.initDataTracker(builder)
        builder.add(IDLE_TICKS, FULL_IDLE_TICKS)
        builder.add(HAS_TARGET, false)
        builder.add(TARGET_GAINED, -1000)
        builder.add(TARGET_LOST, -1000)
        builder.add(ATTACK_POSE, ElfDuelistAttackPose.DEFAULT)
        builder.add(PREVIOUS_ATTACK_POSE, ElfDuelistAttackPose.DEFAULT)
        builder.add(ATTACK_POSE_CHANGED, -1000)
    }

    override fun onTrackedDataSet(data: TrackedData<*>) {
        super.onTrackedDataSet(data)
    }

    override fun tick() {
        super.tick()

        updateIdleTime()
    }

    private fun updateIdleTime() {
        if (world.isClient) return

        val isIdle = navigation.isIdle
        val currentIdleTicks = dataTracker.get(IDLE_TICKS)
        if ((currentIdleTicks == FULL_IDLE_TICKS && isIdle) || (currentIdleTicks == 0 && !isIdle)) return
        dataTracker.set(IDLE_TICKS, MathHelper.clamp(currentIdleTicks + if (isIdle) 1 else -1, 0, FULL_IDLE_TICKS))
    }

    override fun setTarget(newTarget: LivingEntity?) {
        if (newTarget == null) {
            if (target != null) {
                dataTracker.set(TARGET_LOST, world.time)
                dataTracker.set(HAS_TARGET, false)
            }
        } else if (target == null) {
            dataTracker.set(TARGET_GAINED, world.time)
            dataTracker.set(HAS_TARGET, true)
        }

        super.setTarget(newTarget)
    }

    fun setAttackPose(newPose: ElfDuelistAttackPose) {
        val currentPose = dataTracker.get(ATTACK_POSE)
        if (newPose == currentPose) return

        dataTracker.set(PREVIOUS_ATTACK_POSE, currentPose)
        dataTracker.set(ATTACK_POSE, newPose)
        dataTracker.set(ATTACK_POSE_CHANGED, world.time)
    }
}