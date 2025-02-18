package de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist

import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

class ElfDuelistEntityModel(
    modelPart: ModelPart,
) : EntityModel<ElfDuelistRenderState>(modelPart) {
    val elf = root.getChild("elf")
    val elfBody = elf.getChild("elfBody")
    val elfUpperBody = elfBody.getChild("elfUpperBody")
    val elfChest = elfUpperBody.getChild("elfChest")
    val elfBreast = elfChest.getChild("elfBreast")
    val elfCape = elfChest.getChild("elfCape")
    val elfNeck = elfChest.getChild("elfNeck")
    val elfHead = elfNeck.getChild("elfHead")
    val elfEarLeft = elfHead.getChild("elfEarLeft")
    val elfEarRight = elfHead.getChild("elfEarRight")
    val elfArmLeft = elfChest.getChild("elfArmLeft")
    val elfArmUpperLeft = elfArmLeft.getChild("elfArmUpperLeft")
    val elfPauldronLeft = elfArmUpperLeft.getChild("elfPauldronLeft")
    val elfArmLowerLeft = elfArmUpperLeft.getChild("elfArmLowerLeft")
    val elfSwordLeft = elfArmLowerLeft.getChild("elfSwordLeft")
    val elfArmRight = elfChest.getChild("elfArmRight")
    val elfArmUpperRight = elfArmRight.getChild("elfArmUpperRight")
    val elfPauldronRight = elfArmUpperRight.getChild("elfPauldronRight")
    val elfArmLowerRight = elfArmUpperRight.getChild("elfArmLowerRight")
    val elfSwordRight = elfArmLowerRight.getChild("elfSwordRight")
    val elfLegLeft = elf.getChild("elfLegLeft")
    val elfLegRight = elf.getChild("elfLegRight")

    companion object {
        val ELF_DUELIST = EntityModelLayer(IdentifierUtil.default("elf_duelist"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root
            val elf = modelPartData.addChild("elf", ModelPartBuilder.create(), ModelTransform.pivot(0.0f, 0.0f, 0.0f))

            val elfBody = elf.addChild(
                "elfBody",
                ModelPartBuilder.create().uv(55, 46).cuboid(-4.0f, -1.5f, -1.85f, 8.0f, 3.0f, 4.0f, Dilation(0.0f)),
                ModelTransform.pivot(0.0f, 10.5f, 0.0f)
            )

            val elfUpperBody = elfBody.addChild(
                "elfUpperBody",
                ModelPartBuilder.create().uv(57, 39).cuboid(-3.5f, -3.0f, -1.5f, 7.0f, 3.0f, 3.0f, Dilation(0.0f)),
                ModelTransform.pivot(0.0f, -1.5f, 0.0f)
            )

            val elfChest = elfUpperBody.addChild(
                "elfChest",
                ModelPartBuilder.create().uv(57, 22).cuboid(-3.5f, -6.0f, -1.5f, 7.0f, 6.0f, 3.0f, Dilation(0.25f)),
                ModelTransform.pivot(0.0f, -3.0f, 0.0f)
            )

            val elfBreast = elfChest.addChild(
                "elfBreast",
                ModelPartBuilder.create().uv(58, 32).cuboid(-3.5f, 0.0f, 0.0f, 7.0f, 4.0f, 2.0f, Dilation(0.0f)),
                ModelTransform.of(0.0f, -4.75f, -1.75f, -0.2182f, 0.0f, 0.0f)
            )

            val elfCape = elfChest.addChild(
                "elfCape",
                ModelPartBuilder.create().uv(104, 25).cuboid(-4.0f, 0.0f, -0.25f, 8.0f, 14.0f, 0.0f, Dilation(0.0f)),
                ModelTransform.of(0.0f, -5.75f, 2.0f, 0.1309f, 0.0f, 0.0f)
            )

            val elfNeck = elfChest.addChild(
                "elfNeck",
                ModelPartBuilder.create().uv(63, 17).cuboid(-1.0f, -1.75f, -1.0f, 2.0f, 2.0f, 2.0f, Dilation(0.25f)),
                ModelTransform.pivot(0.0f, -6.25f, 0.0f)
            )

            val elfHead = elfNeck.addChild(
                "elfHead",
                ModelPartBuilder.create().uv(51, 0).cuboid(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, Dilation(0.0f))
                    .uv(96, 0).cuboid(-4.0f, -8.0f, -4.0f, 8.0f, 16.0f, 8.0f, Dilation(0.25f)),
                ModelTransform.pivot(0.0f, -0.75f, 0.0f)
            )

            val elfEarLeft = elfHead.addChild(
                "elfEarLeft",
                ModelPartBuilder.create().uv(84, 7).cuboid(0.0f, -6.0f, 0.0f, 0.0f, 6.0f, 3.0f, Dilation(0.0f)),
                ModelTransform.of(3.5f, -1.25f, -1.25f, -0.2618f, 0.6109f, 0.0f)
            )

            val elfEarRight = elfHead.addChild(
                "elfEarRight",
                ModelPartBuilder.create().uv(44, 7).cuboid(0.0f, -6.0f, 0.0f, 0.0f, 6.0f, 3.0f, Dilation(0.0f)),
                ModelTransform.of(-3.5f, -1.25f, -1.25f, -0.2618f, -0.6109f, 0.0f)
            )

            val elfArmLeft =
                elfChest.addChild("elfArmLeft", ModelPartBuilder.create(), ModelTransform.pivot(3.5f, -5.25f, 0.25f))

            val elfArmUpperLeft = elfArmLeft.addChild(
                "elfArmUpperLeft",
                ModelPartBuilder.create().uv(80, 32).cuboid(3.75f, -0.25f, -1.3f, 3.0f, 6.0f, 3.0f, Dilation(0.0f)),
                ModelTransform.pivot(-3.75f, -0.75f, -0.25f)
            )

            val elfPauldronLeft = elfArmUpperLeft.addChild(
                "elfPauldronLeft",
                ModelPartBuilder.create().uv(80, 23).cuboid(0.25f, 0.25f, -1.5f, 3.0f, 5.0f, 3.0f, Dilation(0.25f)),
                ModelTransform.of(3.75f, -0.75f, 0.25f, 0.0f, 0.0f, 0.0436f)
            )

            val elfArmLowerLeft = elfArmUpperLeft.addChild(
                "elfArmLowerLeft",
                ModelPartBuilder.create().uv(80, 42).cuboid(3.75f, 5.25f, -1.3f, 3.0f, 7.0f, 3.0f, Dilation(-0.25f)),
                ModelTransform.pivot(0.0f, 0.0f, 0.0f)
            )

            val elfSwordLeft = elfArmLowerLeft.addChild(
                "elfSwordLeft",
                ModelPartBuilder.create().uv(95, 46).cuboid(-0.5f, -1.75f, -0.5f, 1.0f, 5.0f, 1.0f, Dilation(0.0f))
                    .uv(89, 53).cuboid(-2.0f, -2.75f, -2.0f, 4.0f, 1.0f, 4.0f, Dilation(0.0f))
                    .uv(95, 59).cuboid(-0.5f, -19.75f, -0.5f, 1.0f, 17.0f, 1.0f, Dilation(0.0f)),
                ModelTransform.of(5.0f, 11.0f, 0.25f, 1.5708f, 0.0f, 0.0f)
            )

            val elfArmRight =
                elfChest.addChild("elfArmRight", ModelPartBuilder.create(), ModelTransform.pivot(-3.5f, -5.25f, 0.25f))

            val elfArmUpperRight = elfArmRight.addChild(
                "elfArmUpperRight",
                ModelPartBuilder.create().uv(42, 32).cuboid(-6.75f, -0.25f, -1.3f, 3.0f, 6.0f, 3.0f, Dilation(0.0f)),
                ModelTransform.pivot(3.75f, -0.75f, -0.25f)
            )

            val elfPauldronRight = elfArmUpperRight.addChild(
                "elfPauldronRight",
                ModelPartBuilder.create().uv(42, 23).cuboid(-3.25f, 0.25f, -1.5f, 3.0f, 5.0f, 3.0f, Dilation(0.25f)),
                ModelTransform.of(-3.75f, -0.75f, 0.25f, 0.0f, 0.0f, -0.0436f)
            )

            val elfArmLowerRight = elfArmUpperRight.addChild(
                "elfArmLowerRight",
                ModelPartBuilder.create().uv(42, 42).cuboid(-6.75f, 5.25f, -1.3f, 3.0f, 7.0f, 3.0f, Dilation(-0.25f)),
                ModelTransform.pivot(0.0f, 0.0f, 0.0f)
            )

            val elfSwordRight = elfArmLowerRight.addChild(
                "elfSwordRight",
                ModelPartBuilder.create().uv(35, 46).cuboid(-0.5f, -1.75f, -0.5f, 1.0f, 5.0f, 1.0f, Dilation(0.0f))
                    .uv(29, 53).cuboid(-2.0f, -2.75f, -2.0f, 4.0f, 1.0f, 4.0f, Dilation(0.0f))
                    .uv(35, 59).cuboid(-0.5f, -19.75f, -0.5f, 1.0f, 17.0f, 1.0f, Dilation(0.0f)),
                ModelTransform.of(-5.0f, 11.0f, 0.25f, 1.5708f, 0.0f, 0.0f)
            )

            val elfLegLeft = elf.addChild(
                "elfLegLeft",
                ModelPartBuilder.create().uv(73, 72).cuboid(-1.5f, 0.0f, -1.5f, 3.0f, 6.0f, 3.0f, Dilation(0.25f))
                    .uv(73, 82).cuboid(-1.5f, 6.0f, -1.5f, 3.0f, 6.0f, 3.0f, Dilation(0.0f))
                    .uv(71, 54).cuboid(-2.0f, -1.75f, -1.75f, 4.0f, 13.0f, 4.0f, Dilation(0.25f)),
                ModelTransform.pivot(2.0f, 12.0f, 0.0f)
            )

            val elfLegRight = elf.addChild(
                "elfLegRight",
                ModelPartBuilder.create().uv(49, 72).cuboid(-1.25f, 0.0f, -1.5f, 3.0f, 6.0f, 3.0f, Dilation(0.25f))
                    .uv(49, 82).cuboid(-1.25f, 6.0f, -1.5f, 3.0f, 6.0f, 3.0f, Dilation(0.0f))
                    .uv(47, 54).cuboid(-1.75f, -1.75f, -1.75f, 4.0f, 13.0f, 4.0f, Dilation(0.25f)),
                ModelTransform.pivot(-2.25f, 12.0f, 0.0f)
            )
            return TexturedModelData.of(modelData, 128, 128)
        }
    }

    override fun setAngles(
        renderState: ElfDuelistRenderState,
    ) {
        super.setAngles(renderState)

        setHeadAngles(renderState)
        setIdleAngles(renderState)
        setWalkAngles(renderState)
    }

    private fun setHeadAngles(
        renderState: ElfDuelistRenderState,
    ) {
        elfHead.pitch += Math.toRadians(renderState.pitch.toDouble()).toFloat()
        elfHead.yaw += Math.toRadians(renderState.yawDegrees.toDouble()).toFloat()
        elfHead.yaw = Math.clamp(elfHead.yaw, -1F, 1F)
    }

    private fun setIdleAngles(
        renderState: ElfDuelistRenderState,
    ) {
        val idleState = renderState.idleAnimationState

        val ageSinCurve = (sin(renderState.age * 0.08F) + 1F) / 2F
        val armRoll = ageSinCurve * idleState * 0.1F + 0.05F
        elfArmLeft.roll -= armRoll
        elfArmRight.roll += armRoll

        val ageCosCurve = cos(renderState.age * 0.08F)
        val armPitch = ageCosCurve * idleState * 0.06F
        elfArmLeft.pitch += armPitch
        elfArmRight.pitch += armPitch

        elfBreast.pitch -= ageSinCurve * idleState * 0.1F
        elfCape.roll += ageCosCurve * 0.02F
    }

    private fun setWalkAngles(
        renderState: ElfDuelistRenderState,
    ) {
        val limbSwing = sin(renderState.limbFrequency * 0.6F)
        val speed = renderState.limbAmplitudeMultiplier
        val limbAmplitude = min(1.2F, speed * 1.5F)
        val legAngle = limbSwing * limbAmplitude
        elfLegLeft.pitch += legAngle
        elfLegRight.pitch -= legAngle

        val aggressionState = renderState.aggressionAnimationState
        val aggressionLean = Math.clamp(speed - 0.25F, 0F, 1F) * aggressionState
        elfBody.pitch += aggressionLean * 0.2F
        elfUpperBody.pitch += aggressionLean * 0.2F

        val swordsRotation = Math.clamp(speed, 0.2F, 1F) * aggressionState
        elfArmLeft.roll -= swordsRotation * 0.15F
        elfArmLeft.yaw -= swordsRotation * 0.6F
        elfArmLeft.pitch += swordsRotation * 0.3F
        elfArmRight.roll += swordsRotation * 0.15F
        elfArmRight.yaw += swordsRotation * 0.6F
        elfArmRight.pitch += swordsRotation * 0.3F
    }
}