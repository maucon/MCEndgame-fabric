package de.fuballer.mcendgame.main.component.custom_attribute.effects.dodge

import net.minecraft.entity.damage.DamageTypes

object DodgeSettings {
    val DODGEABLE_DAMAGE_TYPES = listOf(
        DamageTypes.ARROW,
        DamageTypes.TRIDENT,
        DamageTypes.MACE_SMASH,
        DamageTypes.MOB_ATTACK,
        DamageTypes.MOB_ATTACK_NO_AGGRO,
        DamageTypes.MOB_PROJECTILE,
        DamageTypes.PLAYER_ATTACK,
        DamageTypes.SPIT,
        DamageTypes.STING,
        DamageTypes.THROWN,
        DamageTypes.FIREBALL,
        DamageTypes.UNATTRIBUTED_FIREBALL,
        DamageTypes.WITHER_SKULL,
        DamageTypes.INDIRECT_MAGIC,
        DamageTypes.WIND_CHARGE,
        DamageTypes.FIREWORKS,
        DamageTypes.FALLING_BLOCK,
        DamageTypes.FALLING_ANVIL,
        DamageTypes.FALLING_STALACTITE,
        DamageTypes.SONIC_BOOM,
        DamageTypes.LIGHTNING_BOLT,
    )
}