package de.fuballer.client.mcendgame.components.item.custom.armor.helmet.emberchant

import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.render.entity.state.BipedEntityRenderState

class EmberchantModel<S : BipedEntityRenderState>(
    root: ModelPart
) : BipedEntityModel<S>(root) {
    companion object {
        val MODEL_LAYER = EntityModelLayer(IdentifierUtil.default("emberchant"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root

            val head = modelPartData.addChild(EntityModelPartNames.HEAD)
            val hat = head.addChild(EntityModelPartNames.HAT)
            val body = modelPartData.addChild(EntityModelPartNames.BODY)
            val right_arm = modelPartData.addChild(EntityModelPartNames.RIGHT_ARM)
            val left_arm = modelPartData.addChild(EntityModelPartNames.LEFT_ARM)
            val right_leg = modelPartData.addChild(EntityModelPartNames.RIGHT_LEG)
            val left_leg = modelPartData.addChild(EntityModelPartNames.LEFT_LEG)

            val helmet = head.addChild("helmet", ModelPartBuilder.create(), ModelTransform.origin(0.0f, 0.0f, 0.0f))

            val brim = helmet.addChild(
                "brim", ModelPartBuilder.create().uv(0, 47).cuboid(-8.0f, 0.0f, -8.0f, 16.0f, 1.0f, 16.0f, Dilation(0.0f))
                    .uv(5, 41).cuboid(-6.0f, 0.0f, -9.0f, 12.0f, 1.0f, 1.0f, Dilation(0.0f)), ModelTransform.of(0.0f, -5.5f, 0.0f, -0.0873f, 0.0435f, 0.0175f)
            )

            val brimLeft_r1 = brim.addChild(
                "brimLeft_r1",
                ModelPartBuilder.create().uv(5, 44).cuboid(-5.0f, 0.0f, -8.0f, 12.0f, 1.0f, 1.0f, Dilation(0.0f)),
                ModelTransform.of(-1.0f, 0.0f, 1.0f, 0.0f, 1.5708f, 0.0f)
            )

            val brimRight_r1 = brim.addChild(
                "brimRight_r1",
                ModelPartBuilder.create().uv(33, 41).cuboid(-5.0f, 0.0f, -8.0f, 12.0f, 1.0f, 1.0f, Dilation(0.0f)),
                ModelTransform.of(1.0f, 0.0f, -1.0f, 0.0f, -1.5708f, 0.0f)
            )

            val brimBack_r1 = brim.addChild(
                "brimBack_r1",
                ModelPartBuilder.create().uv(33, 44).cuboid(-5.0f, 0.0f, -8.0f, 12.0f, 1.0f, 1.0f, Dilation(0.0f)),
                ModelTransform.of(1.0f, 0.0f, 1.0f, 0.0f, 3.1416f, 0.0f)
            )

            val base = brim.addChild(
                "base",
                ModelPartBuilder.create().uv(0, 26).cuboid(-10.0f, -3.75f, 0.0f, 10.0f, 4.0f, 10.0f, Dilation(0.0f)),
                ModelTransform.of(5.0f, -0.25f, -4.75f, -0.0314f, -0.0436f, -0.0436f)
            )

            val band = base.addChild(
                "band",
                ModelPartBuilder.create().uv(0, 10).cuboid(-10.5f, -3.6f, -0.5f, 11.0f, 4.0f, 11.0f, Dilation(-0.25f)),
                ModelTransform.of(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0262f)
            )

            val tip0 = base.addChild(
                "tip0",
                ModelPartBuilder.create().uv(40, 30).cuboid(0.0f, -3.7642f, 0.0906f, 6.0f, 4.0f, 6.0f, Dilation(0.0f)),
                ModelTransform.of(-8.0f, -3.95f, 1.75f, -0.2611f, 0.0151f, 0.0859f)
            )

            val tip1 = tip0.addChild(
                "tip1",
                ModelPartBuilder.create().uv(46, 23).cuboid(-3.0f, -3.0f, 0.0f, 3.0f, 3.0f, 3.0f, Dilation(0.0f)),
                ModelTransform.of(4.5f, -3.75f, 1.25f, -0.5655f, -0.0468f, -0.0737f)
            )
            return TexturedModelData.of(modelData, 64, 64)
        }
    }
}