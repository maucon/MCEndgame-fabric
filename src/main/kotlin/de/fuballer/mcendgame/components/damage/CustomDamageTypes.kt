package de.fuballer.mcendgame.components.damage

import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.entity.Entity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.damage.DamageType
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.World

object CustomDamageTypes {
    val SWEEPING_KEY = IdentifierUtil.default("sweeping")
    val SWEEPING: RegistryKey<DamageType> = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, SWEEPING_KEY);

    fun of(
        world: World,
        key: RegistryKey<DamageType>,
        attacker: Entity,
        source: Entity? = attacker
    ): DamageSource {
        val damageType = world.registryManager
            .getOrThrow(RegistryKeys.DAMAGE_TYPE)
            .getEntry(key.value)
            .get()

        return DamageSource(damageType, source, attacker)
    }
}