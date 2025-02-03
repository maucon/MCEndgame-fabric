package de.fuballer.mcendgame.components.item.custom.armor.leggings.druids_leggings

import net.minecraft.client.render.entity.state.BipedEntityRenderState
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sin

class DruidsLeggingsAnimation {
    private var lastAge = 0F
    private var ageStep = 0F
    private var speedIncreasedAge = 0F

    private val gravityRadiansPerAge = Math.toRadians(14.5).toFloat()
    private fun getSkirtBackYawVariance(limbAmplitude: Float) = Math.toRadians(1.0 + (limbAmplitude * 2.0)).toFloat()

    fun setTransforms(
        model: DruidsLeggingsModel<out BipedEntityRenderState>,
        entityRenderState: BipedEntityRenderState
    ) {
        increaseAge(entityRenderState)

        val limbAmplitude = entityRenderState.limbAmplitudeMultiplier

        setBattleSkirtBackTransforms(model, limbAmplitude)
        setBattleSkirtFrontTransforms(model)
    }

    private fun setBattleSkirtBackTransforms(
        model: DruidsLeggingsModel<out BipedEntityRenderState>,
        limbAmplitude: Float,
    ) {
        val defaultTransform = model.battleSkirtBack.defaultTransform

        val currentPitch = model.battleSkirtBack.pitch - defaultTransform.pitch
        val gravityPitch = currentPitch - gravityRadiansPerAge * ageStep
        val legPitch = max(model.leftLeg.pitch, model.rightLeg.pitch)

        model.battleSkirtBack.transform = defaultTransform
        model.battleSkirtBack.pitch += max(gravityPitch, legPitch)

        val yawVariance = getSkirtBackYawVariance(limbAmplitude)
        val yaw = sin(speedIncreasedAge * 0.05F) * yawVariance
        model.battleSkirtBack.yaw += yaw
    }

    private fun setBattleSkirtFrontTransforms(
        model: DruidsLeggingsModel<out BipedEntityRenderState>,
    ) {
        val defaultTransform = model.battleSkirtFront.defaultTransform

        val currentPitch = model.battleSkirtFront.pitch - defaultTransform.pitch
        val gravityPitch = currentPitch + gravityRadiansPerAge * ageStep
        val legPitch = min(model.leftLeg.pitch, model.rightLeg.pitch)

        model.battleSkirtFront.transform = defaultTransform
        model.battleSkirtFront.pitch += min(gravityPitch, legPitch)
    }

    private fun increaseAge(
        entityRenderState: BipedEntityRenderState
    ) {
        ageStep = entityRenderState.age - lastAge
        speedIncreasedAge += ageStep * (1 + entityRenderState.limbAmplitudeMultiplier * 4)

        lastAge = entityRenderState.age
    }
}