package de.fuballer.mcendgame.client.component.block.totem_statue

import de.fuballer.mcendgame.client.component.block.totem_statue.TotemStatueBlockEntityModel.TotemStatueModelState
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.RenderLayers
import net.minecraft.client.render.entity.model.EntityModelLayer

class TotemStatueBlockEntityModel(
    root: ModelPart,
) : Model<TotemStatueModelState>(root, RenderLayers::entityTranslucent) {
    companion object {
        val MODEL_LAYER = EntityModelLayer(IdentifierUtil.default("totem_statue"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root
            modelPartData.addChild(
                "bb_main", ModelPartBuilder.create().uv(4, 31).cuboid(-3.0F, -8.0F, -3.0F, 6.0F, 6.0F, 6.0F, Dilation(0.0F))
                    .uv(0, 0).cuboid(-4.0F, -15.0F, -4.0F, 8.0F, 7.0F, 8.0F, Dilation(0.0F))
                    .uv(12, 16).cuboid(-1.0F, -11.0F, -5.0F, 2.0F, 4.0F, 2.0F, Dilation(0.0F))
                    .uv(8, 44).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 2.0F, 4.0F, Dilation(0.0F))
                    .uv(18, 20).cuboid(3.0F, -8.0F, -1.0F, 4.0F, 2.0F, 3.0F, Dilation(0.0F))
                    .uv(19, 26).cuboid(3.0F, -6.0F, -1.0F, 3.0F, 1.0F, 3.0F, Dilation(0.0F))
                    .uv(0, 20).cuboid(-7.0F, -8.0F, -1.0F, 4.0F, 2.0F, 3.0F, Dilation(0.0F))
                    .uv(1, 26).cuboid(-6.0F, -6.0F, -1.0F, 3.0F, 1.0F, 3.0F, Dilation(0.0F)),
                ModelTransform.NONE
            )

            return TexturedModelData.of(modelData, 32, 64)
        }
    }

    class TotemStatueModelState()
}