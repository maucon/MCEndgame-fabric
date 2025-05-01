package de.fuballer.mcendgame.component.entity.custom.interfaces

import net.minecraft.entity.LivingEntity

interface MeleeAttackMob {
    fun meleeAttack(target: LivingEntity)
}