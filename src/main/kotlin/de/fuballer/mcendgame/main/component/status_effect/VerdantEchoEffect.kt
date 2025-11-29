package de.fuballer.mcendgame.main.component.status_effect

import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.server.world.ServerWorld

private const val REGEN_INTERVAL = 20
private const val HEAL = 1f

class VerdantEchoEffect : StatusEffect(StatusEffectCategory.BENEFICIAL, 1349140) {
    companion object {
        val ATTRIBUTE_IDENTIFIER = IdentifierUtil.default("effect.verdant_echo")
    }

    override fun applyUpdateEffect(world: ServerWorld, entity: LivingEntity, amplifier: Int): Boolean {
        if (entity.health < entity.maxHealth) entity.heal(HEAL)
        return true
    }

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int) = duration % REGEN_INTERVAL == 0
}