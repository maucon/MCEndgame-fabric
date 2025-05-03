package de.fuballer.mcendgame.component.entity.custom.attack.damage

data class DelayedAttackDamage(
    val damage: AttackDamage,
    val minDelay: Int,
    val maxDelay: Int = minDelay,
)