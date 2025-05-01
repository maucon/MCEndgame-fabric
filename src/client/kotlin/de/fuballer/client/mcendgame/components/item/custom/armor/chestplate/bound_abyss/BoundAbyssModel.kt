package de.fuballer.client.mcendgame.components.item.custom.armor.chestplate.bound_abyss

import de.fuballer.client.mcendgame.mixin.client.living_entity.LivingEntityRenderStateAccessor
import de.fuballer.client.mcendgame.components.item.custom.ModelPartDataExtension.createEmptyChild
import de.fuballer.client.mcendgame.components.item.custom.armor.CustomVertexConsumer
import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.render.entity.state.BipedEntityRenderState
import net.minecraft.client.render.entity.state.EntityRenderState
import org.joml.Vector3f

class BoundAbyssModel<S : BipedEntityRenderState>(
    root: ModelPart
) : BipedEntityModel<S>(root), CustomVertexConsumer {
    private val shoulderPadLeft: ModelPart
    private val vambraceLeft: ModelPart
    private val shoulderPadRight: ModelPart
    private val vambraceRight: ModelPart

    init {
        val chestplateArmLeft = this.leftArm.getChild("chestplateArmLeft")
        shoulderPadLeft = chestplateArmLeft.getChild("shoulderPadLeft")
        val sleeveLeft = chestplateArmLeft.getChild("sleeveLeft")
        vambraceLeft = sleeveLeft.getChild("vambraceLeft")

        val chestplateArmRight = this.rightArm.getChild("chestplateArmRight")
        shoulderPadRight = chestplateArmRight.getChild("shoulderPadRight")
        val sleeveRight = chestplateArmRight.getChild("sleeveRight")
        vambraceRight = sleeveRight.getChild("vambraceRight")
    }

    override fun getVertexConsumer(
        renderState: EntityRenderState,
        provider: VertexConsumerProvider,
        default: VertexConsumer,
    ): VertexConsumer {
        if (renderState !is LivingEntityRenderStateAccessor) return default

        val effectStrength = renderState.`mcendgame$getLowHealthTicks20`() / 20.0
        return BoundAbyssVertexConsumer(default, effectStrength)
    }

    companion object {
        val MODEL_LAYER = EntityModelLayer(IdentifierUtil.default("bound_abyss"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root

            val head = modelPartData.createEmptyChild(EntityModelPartNames.HEAD)
            val hat = head.createEmptyChild(EntityModelPartNames.HAT)
            val right_leg = modelPartData.createEmptyChild(EntityModelPartNames.RIGHT_LEG)
            val left_leg = modelPartData.createEmptyChild(EntityModelPartNames.LEFT_LEG)

            val body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.origin(0.0f, 0.0f, 0.0f))

            val chestplateBody = body.addChild(
                "chestplateBody",
                ModelPartBuilder.create().uv(18, 35).cuboid(-4.5f, -24.5f, -2.5f, 9.0f, 14.0f, 5.0f, Dilation(0.25f))
                    .uv(16, 22).cuboid(-5.0f, -24.85f, -3.0f, 10.0f, 7.0f, 6.0f, Dilation(0.0f)),
                ModelTransform.origin(0.0f, 24.0f, 0.0f)
            )

            val belt = chestplateBody.addChild(
                "belt",
                ModelPartBuilder.create().uv(14, 54).cuboid(-5.5f, -2.0f, -3.5f, 11.0f, 3.0f, 7.0f, Dilation(-0.5f)),
                ModelTransform.of(0.0f, -15.25f, 0.0f, 0.0f, 0.0f, -0.0873f)
            )

            val left_arm =
                modelPartData.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.origin(5.0f, 2.0f, 0.0f))

            val chestplateArmLeft = left_arm.addChild(
                "chestplateArmLeft",
                ModelPartBuilder.create(),
                ModelTransform.origin(-5.0f, 22.0f, 0.0f)
            )

            val shoulderPadLeft = chestplateArmLeft.addChild(
                "shoulderPadLeft",
                ModelPartBuilder.create().uv(44, 18).cuboid(-3.0f, -2.0f, -2.5f, 5.0f, 5.0f, 5.0f, Dilation(0.0f)),
                ModelTransform.of(7.0f, -23.0f, 0.0f, 0.0436f, 0.0436f, 0.1309f)
            )

            val sleeveLeft = chestplateArmLeft.addChild(
                "sleeveLeft",
                ModelPartBuilder.create().uv(48, 30).cuboid(4.0f, -24.0f, -2.0f, 4.0f, 12.0f, 4.0f, Dilation(0.35f)),
                ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )

            val vambraceLeft = sleeveLeft.addChild(
                "vambraceLeft",
                ModelPartBuilder.create().uv(48, 46).cuboid(4.0f, -18.0f, -2.0f, 4.0f, 6.0f, 4.0f, Dilation(0.5f)),
                ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )

            val right_arm =
                modelPartData.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.origin(-5.0f, 2.0f, 0.0f))

            val chestplateArmRight = right_arm.addChild(
                "chestplateArmRight",
                ModelPartBuilder.create(),
                ModelTransform.origin(5.0f, 22.0f, 0.0f)
            )

            val shoulderPadRight = chestplateArmRight.addChild(
                "shoulderPadRight",
                ModelPartBuilder.create().uv(0, 18).cuboid(-2.0f, -2.0f, -2.5f, 5.0f, 5.0f, 5.0f, Dilation(0.0f)),
                ModelTransform.of(-7.0f, -23.0f, 0.0f, 0.0436f, -0.0436f, -0.1309f)
            )

            val sleeveRight = chestplateArmRight.addChild(
                "sleeveRight",
                ModelPartBuilder.create().uv(0, 30).cuboid(-8.0f, -24.0f, -2.0f, 4.0f, 12.0f, 4.0f, Dilation(0.35f)),
                ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )

            val vambraceRight = sleeveRight.addChild(
                "vambraceRight",
                ModelPartBuilder.create().uv(0, 46).cuboid(-8.0f, -18.0f, -2.0f, 4.0f, 6.0f, 4.0f, Dilation(0.5f)),
                ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )

            return TexturedModelData.of(modelData, 64, 64)
        }
    }

    override fun setAngles(renderState: S) {
        resetNotCopiedTransforms()
        setLowHealthAngles(renderState)
    }

    private fun resetNotCopiedTransforms() {
        shoulderPadLeft.resetTransform()
        shoulderPadRight.resetTransform()
        vambraceLeft.resetTransform()
        vambraceRight.resetTransform()
    }

    private fun setLowHealthAngles(renderState: S) {
        val accessor = renderState as? LivingEntityRenderStateAccessor ?: return
        val lowHealthTicks20 = accessor.`mcendgame$getLowHealthTicks20`()
        val openPercent = lowHealthTicks20 / 20F

        shoulderPadLeft.moveOrigin(Vector3f(openPercent * 1.5F, openPercent * -0.8F, 0F))
        shoulderPadRight.moveOrigin(Vector3f(openPercent * -1.5F, openPercent * -0.8F, 0F))
        vambraceLeft.moveOrigin(Vector3f(openPercent * 1.2F, 0F, 0F))
        vambraceRight.moveOrigin(Vector3f(openPercent * -1.2F, 0F, 0F))
    }
}