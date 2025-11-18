package de.fuballer.mcendgame.main.component.damage.dealing

import de.fuballer.mcendgame.main.component.damage.DifficultyScaling
import net.minecraft.entity.Entity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.damage.DamageType
import net.minecraft.registry.entry.RegistryEntry

class ExtendedDamageSource(
    val damageCalculationConfig: DamageCalculationConfig,
    type: RegistryEntry<DamageType>,
    source: Entity?,
    attacker: Entity?
) : DamageSource(type, source, attacker) {
    constructor(damageCalculationConfig: DamageCalculationConfig, damageSource: DamageSource)
            : this(damageCalculationConfig, damageSource.typeRegistryEntry, damageSource.source, damageSource.attacker)

    constructor(damageSource: DamageSource)
            : this(DamageCalculationConfig(), damageSource)
}

data class DamageCalculationConfig(
    var isArmadilloDamageReduction: Boolean = false,
    var isEnderDragonDamageReduction: Boolean = false,
    var difficultyScaling: DifficultyScaling = DifficultyScaling.NONE,

    val vanillaMoreDamage: MutableList<Double> = mutableListOf(),
    val vanillaMoreDamageTaken: MutableList<Double> = mutableListOf(),
    var shieldBlocked: Boolean = false,
    var damageFactor: Double = 1.0,
) {
    constructor() : this(false) // explicitly empty constructor for java

    fun armadilloDamageReduction(armadilloDamageReduction: Boolean): DamageCalculationConfig {
        this.isArmadilloDamageReduction = armadilloDamageReduction
        return this
    }

    fun enderDragonDamageReduction(enderDragonDamageReduction: Boolean): DamageCalculationConfig {
        this.isEnderDragonDamageReduction = enderDragonDamageReduction
        return this
    }

    fun difficultyScaling(difficultyScaling: DifficultyScaling): DamageCalculationConfig {
        this.difficultyScaling = difficultyScaling
        return this
    }
}
