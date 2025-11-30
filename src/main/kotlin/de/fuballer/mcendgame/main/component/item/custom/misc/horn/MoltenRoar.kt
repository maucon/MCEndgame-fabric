package de.fuballer.mcendgame.main.component.item.custom.misc.horn

import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.item.custom.UniqueAttributesHornItem
import de.fuballer.mcendgame.main.component.status_effect.CustomStatusEffects
import de.fuballer.mcendgame.main.util.extension.EntityExtension.isAlly
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World

private const val RANGE = 10.0
private const val EFFECT_DURATION = 200 // ticks

class MoltenRoar(
    settings: Settings,
) : UniqueAttributesHornItem(settings) {
    override val cooldownTicks = 600

    override fun getCustomAttributes(): List<RollableCustomAttribute> = listOf()

    override fun getAttributeModifierSlot() = AttributeModifierSlot.HAND

    override fun onUse(world: World, user: PlayerEntity) {
        val nearbyAllies = world.getEntitiesByClass(LivingEntity::class.java, user.boundingBox.expand(RANGE)) { user.isAlly(it) && user.distanceTo(it) <= RANGE }
        val effectInstance = StatusEffectInstance(CustomStatusEffects.MOLTEN_ROAR, EFFECT_DURATION, 0, true, true, true)
        nearbyAllies.forEach { it.addStatusEffect(effectInstance) }
    }
}