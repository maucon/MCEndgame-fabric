package de.fuballer.mcendgame.components.entity.custom.util

data class CustomAttack(
    val startPose: CustomAttackPose,
    val endPose: CustomAttackPose,
    val damageDelay: Int,
    val totalDuration: Int,
    val hitRange: Double,
    val damageFactor: Float,
    val knockbackFactor: Double,
    val animControllerName: String,
    val animName: String,
    val squaredHitRange: Double = hitRange * hitRange,
)