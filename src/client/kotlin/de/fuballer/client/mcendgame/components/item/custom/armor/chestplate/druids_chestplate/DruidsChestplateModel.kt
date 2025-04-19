package de.fuballer.client.mcendgame.components.item.custom.armor.chestplate.druids_chestplate

import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.render.entity.state.BipedEntityRenderState

class DruidsChestplateModel<S : BipedEntityRenderState>(
    root: ModelPart
) : BipedEntityModel<S>(root) {
    companion object {
        val MODEL_LAYER = EntityModelLayer(IdentifierUtil.default("druids_chestplate"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root

            val head = modelPartData.addChild(EntityModelPartNames.HEAD)
            val hat = head.addChild(EntityModelPartNames.HAT)
            val right_leg = modelPartData.addChild(EntityModelPartNames.RIGHT_LEG)
            val left_leg = modelPartData.addChild(EntityModelPartNames.LEFT_LEG)

            val body = modelPartData.addChild(EntityModelPartNames.BODY)

            val chestplate =
                body.addChild("chestplate", ModelPartBuilder.create(), ModelTransform.origin(0.0f, 0.0f, 0.0f))

            val chestplate_armor = chestplate.addChild(
                "chestplate_armor",
                ModelPartBuilder.create().uv(40, 89).cuboid(-4.5f, -0.25f, -2.75f, 9.0f, 5.0f, 5.0f, Dilation(0.5f))
                    .uv(44, 105).cuboid(-4.5f, 4.0f, 1.2f, 9.0f, 2.0f, 1.0f, Dilation(0.5f)),
                ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )

            val armor_bottom_front = chestplate_armor.addChild(
                "armor_bottom_front",
                ModelPartBuilder.create().uv(44, 99).cuboid(-4.5f, 0.5f, 0.5f, 9.0f, 5.0f, 1.0f, Dilation(0.5f)),
                ModelTransform.of(0.0f, 5.25f, -3.25f, 0.0873f, 0.0f, 0.0f)
            )

            val chestplate_base = chestplate.addChild(
                "chestplate_base",
                ModelPartBuilder.create().uv(41, 108).cuboid(-4.5f, -0.3f, -2.75f, 9.0f, 6.0f, 5.0f, Dilation(0.3f))
                    .uv(42, 119).cuboid(-4.0f, 6.1f, -2.0f, 8.0f, 5.0f, 4.0f, Dilation(0.51f)),
                ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )

            val left_arm =
                modelPartData.addChild(
                    EntityModelPartNames.LEFT_ARM,
                    ModelPartBuilder.create(),
                    ModelTransform.origin(5.0f, 2.0f, 0.0f)
                )

            val chestplate_left_arm = left_arm.addChild(
                "chestplate_left_arm",
                ModelPartBuilder.create().uv(68, 91).cuboid(-1.0f, -2.0f, -2.0f, 4.0f, 6.0f, 4.0f, Dilation(0.4f))
                    .uv(68, 101).cuboid(-1.0f, 4.0f, -2.0f, 4.0f, 6.0f, 4.0f, Dilation(0.26f))
                    .uv(84, 101).cuboid(-1.5f, 4.0f, -2.5f, 5.0f, 2.0f, 5.0f, Dilation(-0.15f))
                    .uv(84, 108).cuboid(-1.5f, 6.55f, -2.5f, 5.0f, 4.0f, 5.0f, Dilation(-0.15f)),
                ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )

            val shoulderpad_left = chestplate_left_arm.addChild(
                "shoulderpad_left",
                ModelPartBuilder.create().uv(84, 93).cuboid(0.0f, 0.5f, -2.0f, 3.0f, 4.0f, 4.0f, Dilation(0.5f)),
                ModelTransform.of(0.5f, -3.0f, 0.1f, 0.0f, 0.0436f, 0.0873f)
            )

            val right_arm =
                modelPartData.addChild(
                    EntityModelPartNames.RIGHT_ARM,
                    ModelPartBuilder.create(),
                    ModelTransform.origin(-5.0f, 2.0f, 0.0f)
                )

            val chestplate_right_arm = right_arm.addChild(
                "chestplate_right_arm",
                ModelPartBuilder.create().uv(24, 91).cuboid(-3.0f, -2.0f, -2.0f, 4.0f, 6.0f, 4.0f, Dilation(0.4f))
                    .uv(24, 101).cuboid(-3.0f, 4.0f, -2.0f, 4.0f, 6.0f, 4.0f, Dilation(0.26f))
                    .uv(4, 101).cuboid(-3.5f, 4.0f, -2.5f, 5.0f, 2.0f, 5.0f, Dilation(-0.15f))
                    .uv(4, 108).cuboid(-3.5f, 6.55f, -2.5f, 5.0f, 4.0f, 5.0f, Dilation(-0.15f)),
                ModelTransform.origin(0.0f, 0.0f, 0.0f)
            )

            val shoulderpad_right = chestplate_right_arm.addChild(
                "shoulderpad_right",
                ModelPartBuilder.create().uv(10, 93).cuboid(-3.0f, 0.5f, -2.0f, 3.0f, 4.0f, 4.0f, Dilation(0.5f)),
                ModelTransform.of(-0.5f, -3.0f, 0.1f, 0.0f, -0.0436f, -0.0873f)
            )
            return TexturedModelData.of(modelData, 128, 128)
        }
    }
}