package de.fuballer.mcendgame.main.component.entity.custom.entities.scarred_one

import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.LookAtEntityGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animatable.manager.AnimatableManager
import software.bernie.geckolib.util.GeckoLibUtil

class ScarredOneEntity(
    type: EntityType<out ScarredOneEntity>,
    world: World,
) : MobEntity(type, world), GeoEntity {
    companion object {
        fun createAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.FOLLOW_RANGE, 15.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.0)
        }
    }

    override fun initGoals() {
        goalSelector.add(0, LookAtEntityGoal(this, PlayerEntity::class.java, 8F))
    }

    private val cache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)
    override fun getAnimatableInstanceCache() = cache

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
    }

    override fun isPushable() = false
}