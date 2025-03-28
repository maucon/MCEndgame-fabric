package de.fuballer.mcendgame.components.entity.custom.entities.arachne

import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.entity.passive.VillagerEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World

class ArachneEntity(
    type: EntityType<out ArachneEntity>,
    world: World,
) : HostileEntity(type, world) {

    companion object {
        val MOVING_START = DataTracker.registerData(ArachneEntity::class.java, TrackedDataHandlerRegistry.LONG)

        fun createAttributes(): DefaultAttributeContainer.Builder {
            return createHostileAttributes()
                .add(EntityAttributes.FOLLOW_RANGE, 35.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.28)
                .add(EntityAttributes.ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.ARMOR, 0.0)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, 0.8)
        }
    }

    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))
        goalSelector.add(1, MeleeAttackGoal(this, 1.0, false))
        goalSelector.add(7, WanderAroundFarGoal(this, 1.0))
        goalSelector.add(8, LookAtEntityGoal(this, PlayerEntity::class.java, 8.0f))
        goalSelector.add(8, LookAroundGoal(this))

        targetSelector.add(1, ActiveTargetGoal(this, PlayerEntity::class.java, true))
        targetSelector.add(1, ActiveTargetGoal(this, VillagerEntity::class.java, true))
    }

    override fun initDataTracker(builder: DataTracker.Builder) {
        super.initDataTracker(builder)
        builder.add(MOVING_START, -1)
    }

    override fun tick() {
        super.tick()

        updateMovementState()
    }

    private fun updateMovementState() {
        if (world.isClient) return

        val isIdle = navigation.isIdle
        val movingStart = dataTracker.get(MOVING_START)

        if (isIdle) {
            if (movingStart != -1L) {
                dataTracker.set(MOVING_START, -1L)
            }
            return
        }
        if (movingStart >= 0) return

        dataTracker.set(MOVING_START, world.time)
    }
}