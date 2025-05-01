package de.fuballer.mcendgame.component.entity.custom.attack

data class CustomAttack(
    val startPose: CustomAttackPose,
    val endPose: CustomAttackPose,
    val totalDuration: Int,
    val cooldown: Int,
    val triggerRange: Double,
    val damage: List<Pair<Int, CustomAttackDamage>>,
    val animControllerName: String,
    val animName: String,
    val squaredTriggerRange: Double = triggerRange * triggerRange,
) {
    constructor(
        startPose: CustomAttackPose,
        endPose: CustomAttackPose,
        totalDuration: Int,
        cooldown: Int,
        triggerRange: Double,
        damage: Pair<Int, CustomAttackDamage>?,
        animControllerName: String,
        animName: String,
    ) : this(startPose, endPose, totalDuration, cooldown, triggerRange, if (damage != null) listOf(damage) else listOf(), animControllerName, animName)
}