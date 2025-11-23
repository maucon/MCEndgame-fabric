package de.fuballer.mcendgame.main.component.damage.dealing

import de.fuballer.mcendgame.main.component.damage.custom_type.CustomDamageTypes
import net.minecraft.entity.Entity
import net.minecraft.server.world.ServerWorld

object DamageDealingService {
    fun Entity.dealElementalDamage(damageFactor: Double, attacker: Entity) {
        val damageSource = CustomDamageTypes.of(world, CustomDamageTypes.ELEMENTAL, attacker)
        val damageCalculationConfig = DamageCalculationConfig(damageFactor = damageFactor)
        val extended = ExtendedDamageSource(damageCalculationConfig, damageSource)

        this.damage(world as ServerWorld, extended, 420f) // amount does not matter, will be calculated by us
    }

    fun Entity.dealGenericAttackDamage(amount: Float, attacker: Entity) {
        val damageSource = CustomDamageTypes.of(world, CustomDamageTypes.GENERIC_ATTACK, attacker)
        val extended = ExtendedDamageSource(DamageCalculationConfig(), damageSource)

        this.damage(world as ServerWorld, extended, amount)
    }
}