package de.fuballer.mcendgame.main.component.entity.custom.attack.data

import de.fuballer.mcendgame.main.component.entity.custom.attack.AttackPose
import software.bernie.geckolib.animatable.GeoEntity

data class AttackAnimationData(
    val startPose: AttackPose,
    val endPose: AttackPose,
    val animControllerId: String,
    val animId: String,
) {
    fun triggerAnimation(animateAble: GeoEntity) = animateAble.triggerAnim(animControllerId, animId)
}