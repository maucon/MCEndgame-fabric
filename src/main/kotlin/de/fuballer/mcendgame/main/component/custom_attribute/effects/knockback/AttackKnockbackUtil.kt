package de.fuballer.mcendgame.main.component.custom_attribute.effects.knockback

import de.fuballer.mcendgame.main.messaging.misc.LivingEntityKnockbackLivingEntityCommand
import de.maucon.mauconframework.command.CommandGateway
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity

object AttackKnockbackUtil {
    fun LivingEntity.takeKnockbackFrom(
        attacker: Entity?,
        strength: Double,
        x: Double,
        z: Double,
    ) {
        if (attacker !is LivingEntity) return takeKnockback(strength, x, z)

        val command = LivingEntityKnockbackLivingEntityCommand(this, attacker, strength)
        val cmd = CommandGateway.apply(command)

        takeKnockback(cmd.strength, x, z)
    }
}