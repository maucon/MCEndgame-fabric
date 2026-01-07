package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getAllCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.data.IntRoll
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.messaging.misc.PlayerTakeShieldHitCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import kotlin.math.max

@Injectable
class ShieldDisabledOnBlockingHit {
    @CommandHandler
    fun on(cmd: PlayerTakeShieldHitCommand) {
        val attributes = cmd.player.getAllCustomAttributes()[CustomAttributeTypes.SHIELD_DISABLED_ON_BLOCKING_HIT]
        val cooldown = attributes?.maxOfOrNull { (it.rolls[0] as IntRoll).getValue() } ?: return
        cmd.shieldCooldown = max(cooldown.toFloat(), cmd.shieldCooldown)
    }
}