package de.fuballer.mcendgame.main.component.damage.custom_type

import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.entity.Entity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.damage.DamageType
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.World

@Injectable
object CustomDamageTypes {
    val SWEEPING: RegistryKey<DamageType> = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, IdentifierUtil.default("sweeping"))
    val ELEMENTAL: RegistryKey<DamageType> = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, IdentifierUtil.default("elemental"))
    val GENERIC_ATTACK: RegistryKey<DamageType> = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, IdentifierUtil.default("generic_attack"))
    val GENERIC_ATTACK_UNBLOCKABLE: RegistryKey<DamageType> = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, IdentifierUtil.default("generic_attack_unblockable"))
    val PIERCE_ATTACK: RegistryKey<DamageType> = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, IdentifierUtil.default("pierce_attack"))
    val KINETIC_ATTACK: RegistryKey<DamageType> = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, IdentifierUtil.default("kinetic_attack"))

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