package de.fuballer.mcendgame.main.component.item.custom.misc.horn

import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.item.custom.UniqueAttributesHornItem
import de.fuballer.mcendgame.main.component.item.custom.misc.horn.command.HornUseCommand
import de.fuballer.mcendgame.main.component.status_effect.CustomStatusEffects
import de.fuballer.mcendgame.main.util.extension.EntityExtension.isAlly
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.text.Text
import net.minecraft.world.World

class VerdantEcho(
    settings: Settings,
) : UniqueAttributesHornItem(settings) {
    override val id = "verdant_echo"

    override val description = listOf(
        Text.translatable(DESCRIPTION_KEY + id + "0"),
        Text.translatable(DESCRIPTION_KEY + id + "1"),
    )

    override val baseCooldown = 600
    override val baseDuration = 200
    override val range = 10.0

    override fun getCustomAttributes(): List<RollableCustomAttribute> = listOf()

    override fun getAttributeModifierSlot() = AttributeModifierSlot.HAND

    override fun onUse(world: World, user: PlayerEntity, cmd: HornUseCommand) {
        val nearbyAllies = world.getEntitiesByClass(LivingEntity::class.java, user.boundingBox.expand(range)) { user.isAlly(it) && user.distanceTo(it) <= range }

        val duration = (baseDuration * cmd.getDurationFactor()).toInt()
        val amplifier = if (cmd.isStronger) 1 else 0
        val effectInstance = StatusEffectInstance(CustomStatusEffects.VERDANT_ECHO, duration, amplifier, false, true, true)
        nearbyAllies.forEach { it.addStatusEffect(effectInstance) }
    }
}