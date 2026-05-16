package de.fuballer.mcendgame.main.component.entity.custom.entities.spiderling

import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.entity.passive.TameableEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.world.World
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animatable.manager.AnimatableManager
import software.bernie.geckolib.util.GeckoLibUtil

class SpiderlingEntity(
    type: EntityType<out SpiderlingEntity>,
    world: World,
) : TameableEntity(type, world), GeoEntity {
    companion object {
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

    private val cache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)
    override fun getAnimatableInstanceCache() = cache

    override fun initGoals() {
        goalSelector.add(1, SwimGoal(this))
        goalSelector.add(2, MeleeAttackGoal(this, 1.0, true))
        goalSelector.add(3, FollowOwnerGoal(this, 1.0, 6.0f, 1.5f))
        goalSelector.add(4, WanderAroundGoal(this, 1.0))
        goalSelector.add(5, LookAtEntityGoal(this, PlayerEntity::class.java, 8.0f))
        goalSelector.add(5, LookAroundGoal(this))

        targetSelector.add(1, TrackOwnerAttackerGoal(this))
        targetSelector.add(2, AttackWithOwnerGoal(this))
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
    }

    override fun isBreedingItem(item: ItemStack) = false

    override fun createChild(world: ServerWorld, entity: PassiveEntity) = null

    //TODO remove
    override fun interact(player: PlayerEntity, hand: Hand): ActionResult {
        setTamed(true, false)
        owner = player
        return ActionResult.SUCCESS
    }
}