package de.fuballer.mcendgame.components.entity.custom.feature.webbed

import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.entity.model.EntityModelLayer

class WebbedModel(
    modelPart: ModelPart,
) : Model(modelPart, RenderLayer::getEntityCutoutNoCull) {
    companion object {
        val WEBBED_LAYER = EntityModelLayer(IdentifierUtil.default("webbed"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root
            val webbed = modelPartData.addChild(
                "webbed",
                ModelPartBuilder.create().uv(-3, 0).cuboid(-5.0f, -16.0f, -8.0f, 10.0f, 16.0f, 2.0f, Dilation(0.0f))
                    .uv(-3, 0).cuboid(-7.0f, -16.0f, -7.0f, 2.0f, 16.0f, 2.0f, Dilation(0.0f))
                    .uv(-3, 0).cuboid(5.0f, -16.0f, -7.0f, 2.0f, 16.0f, 2.0f, Dilation(0.0f)),
                ModelTransform.pivot(0.0f, 16.0f, 0.0f)
            )

            val cube_r1 = webbed.addChild(
                "cube_r1",
                ModelPartBuilder.create().uv(-3, 0).cuboid(-4.0f, -16.0f, -9.0f, 2.0f, 16.0f, 2.0f, Dilation(0.0f)),
                ModelTransform.of(2.0f, 0.0f, 3.0f, 0.0f, 1.5708f, 0.0f)
            )

            val cube_r2 = webbed.addChild(
                "cube_r2",
                ModelPartBuilder.create().uv(-3, 0).cuboid(-4.0f, -16.0f, -9.0f, 2.0f, 16.0f, 2.0f, Dilation(0.0f)),
                ModelTransform.of(-2.0f, 0.0f, 9.0f, 0.0f, -1.5708f, 0.0f)
            )

            val cube_r3 = webbed.addChild(
                "cube_r3",
                ModelPartBuilder.create().uv(-3, 0).cuboid(-4.0f, -16.0f, -9.0f, 10.0f, 16.0f, 2.0f, Dilation(0.0f)),
                ModelTransform.of(-1.0f, 0.0f, -1.0f, 0.0f, -1.5708f, 0.0f)
            )

            val cube_r4 = webbed.addChild(
                "cube_r4",
                ModelPartBuilder.create().uv(-3, 0).cuboid(-4.0f, -16.0f, -9.0f, 10.0f, 16.0f, 2.0f, Dilation(0.0f)),
                ModelTransform.of(1.0f, 0.0f, -1.0f, 0.0f, 3.1416f, 0.0f)
            )

            val cube_r5 = webbed.addChild(
                "cube_r5",
                ModelPartBuilder.create().uv(-3, 0).cuboid(-4.0f, -16.0f, -9.0f, 10.0f, 16.0f, 2.0f, Dilation(0.0f)),
                ModelTransform.of(1.0f, 0.0f, 1.0f, 0.0f, 1.5708f, 0.0f)
            )
            return TexturedModelData.of(modelData, 32, 32)
        }
    }
}