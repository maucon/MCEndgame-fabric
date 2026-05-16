package de.fuballer.mcendgame.client.component.item.custom.armor.model.broodmother

import de.fuballer.mcendgame.client.component.item.custom.ModelPartDataExtension.createEmptyChild
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.render.entity.state.BipedEntityRenderState


class BroodmotherModel<S : BipedEntityRenderState>(
    root: ModelPart
) : BipedEntityModel<S>(root) {
    companion object {
        val MODEL_LAYER = EntityModelLayer(IdentifierUtil.default("broodmother"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root

            val head = modelPartData.createEmptyChild(EntityModelPartNames.HEAD)
            val hat = head.createEmptyChild(EntityModelPartNames.HAT)
            val left_leg = modelPartData.createEmptyChild(EntityModelPartNames.LEFT_LEG)
            val right_leg = modelPartData.createEmptyChild(EntityModelPartNames.RIGHT_LEG)

            val body = modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create(), ModelTransform.origin(0.0f, 0.0f, 0.0f))

            val chestplate = body.addChild(
                "chestplate", ModelPartBuilder.create().uv(36, 13).cuboid(-4.0f, 4.0f, -2.0f, 8.0f, 8.0f, 4.0f, Dilation(0.15f))
                    .uv(34, 0).cuboid(-4.5f, 0.0f, -2.65f, 9.0f, 7.0f, 5.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )

            val web = chestplate.addChild("web", ModelPartBuilder.create().uv(39, 37).cuboid(-4.5f, 0.5f, 0.0f, 9.0f, 15.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 2.0f, 2.95f))

            val legs = chestplate.addChild("legs", ModelPartBuilder.create().uv(42, 26).cuboid(-3.0f, -5.0f, 0.0f, 6.0f, 6.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(0.0f, 6.0f, 2.85f))

            val left_legs =
                legs.addChild("left_legs", ModelPartBuilder.create().uv(55, 26).cuboid(0.0f, -10.0f, 0.0f, 11.0f, 10.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(3.0f, 0.0f, 0.0f))

            val right_legs =
                legs.addChild("right_legs", ModelPartBuilder.create().uv(19, 26).cuboid(-11.0f, -10.0f, 0.0f, 11.0f, 10.0f, 0.0f, Dilation(0.0f)), ModelTransform.origin(-3.0f, 0.0f, 0.0f))

            val left_arm = modelPartData.addChild(EntityModelPartNames.LEFT_ARM, ModelPartBuilder.create(), ModelTransform.origin(5.0f, 2.0f, 0.0f))

            val chestplate_left_arm = left_arm.addChild(
                "chestplate_left_arm", ModelPartBuilder.create().uv(61, 11).cuboid(-1.0f, 2.0f, -2.0f, 4.0f, 7.0f, 4.0f, Dilation(0.1f))
                    .uv(63, 1).cuboid(-1.25f, -2.5f, -2.25f, 5.0f, 4.0f, 5.0f, Dilation(0.0f))
                    .uv(78, 11).cuboid(-1.25f, -2.5f, -1.25f, 5.0f, 15.0f, 4.0f, Dilation(-0.1f)), ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )

            val right_arm = modelPartData.addChild(EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create(), ModelTransform.origin(-5.0f, 2.0f, 0.0f))

            val chestplate_right_arm = right_arm.addChild(
                "chestplate_right_arm", ModelPartBuilder.create().uv(19, 11).cuboid(-3.0f, 2.0f, -2.0f, 4.0f, 7.0f, 4.0f, Dilation(0.1f))
                    .uv(13, 1).cuboid(-3.75f, -2.5f, -2.25f, 5.0f, 4.0f, 5.0f, Dilation(0.0f))
                    .uv(0, 11).cuboid(-3.75f, -2.5f, -1.25f, 5.0f, 15.0f, 4.0f, Dilation(-0.1f)), ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )
            return TexturedModelData.of(modelData, 128, 64)
        }
    }

    override fun setAngles(renderState: S) {}
}