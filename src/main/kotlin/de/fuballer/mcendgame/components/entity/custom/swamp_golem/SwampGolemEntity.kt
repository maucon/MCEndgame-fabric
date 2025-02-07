package de.fuballer.mcendgame.components.entity.custom.swamp_golem

import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.ActiveTargetGoal
import net.minecraft.entity.ai.goal.LookAroundGoal
import net.minecraft.entity.ai.goal.LookAtEntityGoal
import net.minecraft.entity.ai.goal.WanderAroundFarGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.World

class SwampGolemEntity(
    type: EntityType<out SwampGolemEntity>,
    world: World,
) : HostileEntity(type, world) {

    override fun initGoals() {
        //goalSelector.add(2, MeleeAttackGoal(this, 1.0, false))
        goalSelector.add(2, SwampGolemSlamGoal(this, 1.0, false))
        goalSelector.add(7, WanderAroundFarGoal(this, 1.0))
        goalSelector.add(8, LookAtEntityGoal(this, PlayerEntity::class.java, 8.0f))
        goalSelector.add(8, LookAroundGoal(this))

        targetSelector.add(1, ActiveTargetGoal(this, PlayerEntity::class.java, true))
    }

    companion object {
        fun createAttributes(): DefaultAttributeContainer.Builder {
            return createHostileAttributes()
                .add(EntityAttributes.FOLLOW_RANGE, 35.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.20)
                .add(EntityAttributes.ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.ARMOR, 5.0)
        }
    }

    fun slam() {
        println("SLAM ANIMATION")
    }

    fun slamAttack(world: ServerWorld) {

    }
}