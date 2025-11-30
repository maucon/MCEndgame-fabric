package de.fuballer.mcendgame.main.component.item.custom.misc.horn

import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.item.custom.UniqueAttributesHornItem
import de.fuballer.mcendgame.main.component.item.custom.misc.horn.command.HornUseCommand
import de.fuballer.mcendgame.main.util.extension.EntityExtension.isEnemy
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World

class FrigidCry(
    settings: Settings,
) : UniqueAttributesHornItem(settings) {
    override val id = "frigid_cry"

    override val baseCooldown = 600
    override val baseDuration = 100
    override val range = 8.0

    override fun getCustomAttributes(): List<RollableCustomAttribute> = listOf()

    override fun getAttributeModifierSlot() = AttributeModifierSlot.HAND

    override fun onUse(world: World, user: PlayerEntity, cmd: HornUseCommand) {
        val nearbyEnemies = world.getEntitiesByClass(LivingEntity::class.java, user.boundingBox.expand(range)) { user.isEnemy(it) && user.distanceTo(it) <= range }
        if (nearbyEnemies.isEmpty()) return

        val duration = (baseDuration * cmd.getDurationFactor()).toInt()
        val amplifier = if (cmd.isStronger) 2 else 1
        val effectInstance = StatusEffectInstance(StatusEffects.SLOWNESS, duration, amplifier, true, true, true)
        nearbyEnemies.forEach { it.addStatusEffect(effectInstance) }
    }
}