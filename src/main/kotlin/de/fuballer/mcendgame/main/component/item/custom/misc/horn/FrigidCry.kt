package de.fuballer.mcendgame.main.component.item.custom.misc.horn

import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.item.custom.UniqueAttributesHornItem
import de.fuballer.mcendgame.main.util.extension.EntityExtension.isEnemy
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World

private const val RANGE = 8.0
private const val EFFECT_DURATION = 100 // ticks

class FrigidCry(
    settings: Settings,
) : UniqueAttributesHornItem(settings) {
    override val cooldownTicks = 600

    override fun getCustomAttributes(): List<RollableCustomAttribute> = listOf()

    override fun getAttributeModifierSlot() = AttributeModifierSlot.HAND

    override fun onUse(world: World, user: PlayerEntity) {
        val nearbyEnemies = world.getEntitiesByClass(LivingEntity::class.java, user.boundingBox.expand(RANGE)) { user.isEnemy(it) && user.distanceTo(it) <= RANGE }
        val effectInstance = StatusEffectInstance(StatusEffects.SLOWNESS, EFFECT_DURATION, 1, true, true, true)
        nearbyEnemies.forEach { it.addStatusEffect(effectInstance) }
    }
}