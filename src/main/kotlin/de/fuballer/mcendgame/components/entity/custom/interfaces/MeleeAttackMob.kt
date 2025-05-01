package de.fuballer.mcendgame.components.entity.custom.interfaces

import net.minecraft.entity.LivingEntity

interface MeleeAttackMob {
    fun meleeAttack(target: LivingEntity)
}