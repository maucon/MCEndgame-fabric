package de.fuballer.mcendgame.components.entity.custom.interfaces

import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity

interface HookAttackMob {
    fun shootHookAt(target: LivingEntity)
    fun addHookedEntity(hooked: Entity)
    fun removeHookedEntity(hooked: Entity)
}