package de.fuballer.mcendgame.main.component.entity.custom.entities.scarred_one

import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.encounters.scarred_one.data.RolledScarredOneEffect
import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.encounters.scarred_one.event.ScarredOneInteractEvent
import de.fuballer.mcendgame.main.util.extension.mixin.WorldMixinExtension.addCustomAttribute
import de.maucon.mauconframework.event.EventGateway
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.LookAtEntityGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
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

    //TODO make persistent
    var positiveEffects = listOf<RolledScarredOneEffect>()
    var negativeEffects = listOf<RolledScarredOneEffect>()

    var gotResponse = false

    fun respond(accept: Boolean, world: ServerWorld) {
        gotResponse = true

        if (accept) {
            positiveEffects.forEach { world.addCustomAttribute(it.attribute, it.targets.predicate) }
            negativeEffects.forEach { world.addCustomAttribute(it.attribute, it.targets.predicate) }
        }

        EventGateway.publish(ScarredOneDespawnEvent(this, accept))
    }

    fun hasRolledEffects() = positiveEffects.isNotEmpty() || negativeEffects.isNotEmpty()

    override fun initGoals() {
        goalSelector.add(0, LookAtEntityGoal(this, PlayerEntity::class.java, 8F))
    }

    private val cache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)
    override fun getAnimatableInstanceCache() = cache

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
    }

    override fun interact(
        player: PlayerEntity,
        hand: Hand
    ): ActionResult {
        val world = entityWorld as? ServerWorld ?: return ActionResult.PASS
        val serverPlayer = player as? ServerPlayerEntity ?: return ActionResult.PASS

        val event = ScarredOneInteractEvent(this, serverPlayer, world)
        EventGateway.publish(event)

        return ActionResult.PASS
    }

    override fun isPushable() = false
}