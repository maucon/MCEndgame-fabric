package de.fuballer.mcendgame.main.component.damage

data class DamageCalculationResult(
    val isApplying: Boolean,
    val amount: Float = 0f,
){
    companion object {
        fun noDamage() = DamageCalculationResult(false)
        fun normalDamage(amount: Float) = DamageCalculationResult(true, amount)
    }
}