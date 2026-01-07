package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getAllCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.damage.DamageCalculationCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Items

@Injectable
class IncreasedDamageWhileShieldDisabledService {
    @CommandHandler
    fun on(cmd: DamageCalculationCommand) {
        val player = (cmd.damager as? PlayerEntity) ?: return
        if (!player.itemCooldownManager.isCoolingDown(Items.SHIELD.defaultStack)) return // only check default shield since cooldowns should be synced anyway

        val attributes = player.getAllCustomAttributes()[CustomAttributeTypes.INCREASED_DAMAGE_WHILE_SHIELD_DISABLED] ?: return
        val increase = attributes.sumOf { (it.rolls[0] as DoubleRoll).getValue() }
        cmd.increasedDamage.add(increase)
    }
}