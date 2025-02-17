package de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist

import de.fuballer.mcendgame.components.entity.custom.entities.swamp_golem.SwampGolemEntity
import de.fuballer.mcendgame.components.entity.custom.interfaces.CustomPosesEntity
import net.minecraft.entity.AnimationState
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.entity.passive.ChickenEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World

class ElfDuelistEntity(
    type: EntityType<out ElfDuelistEntity>,
    world: World,
) : HostileEntity(type, world), CustomPosesEntity {
    val idleAnimationState = AnimationState()

    companion object {
        val CUSTOM_POSE = DataTracker.registerData(ElfDuelistEntity::class.java, CustomPosesEntity.CUSTOM_POSE_TDH)

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
        goalSelector.add(7, WanderAroundFarGoal(this, 1.0))
        goalSelector.add(8, LookAtEntityGoal(this, PlayerEntity::class.java, 8.0f))
        goalSelector.add(8, LookAroundGoal(this))

        targetSelector.add(1, ActiveTargetGoal(this, PlayerEntity::class.java, true))
        targetSelector.add(1, ActiveTargetGoal(this, ChickenEntity::class.java, true))
    }

    override fun onTrackedDataSet(data: TrackedData<*>) {
        if (data == SwampGolemEntity.CUSTOM_POSE) {
            when (dataTracker.get(SwampGolemEntity.CUSTOM_POSE)) {
                CustomPosesEntity.CustomPose.IDLING -> {
                    idleAnimationState.start(age)
                }

                else -> {}
            }
        }
        super.onTrackedDataSet(data)
    }

    override fun tick() {
        super.tick()
    }

    override fun initDataTracker(builder: DataTracker.Builder) {
        super.initDataTracker(builder)
        builder.add(CUSTOM_POSE, CustomPosesEntity.CustomPose.IDLING)
    }

    override fun setPose(pose: CustomPosesEntity.CustomPose) {
        dataTracker.set(CUSTOM_POSE, pose)
    }
}