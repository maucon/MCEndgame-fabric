package de.fuballer.mcendgame.client.component.item.custom.armor.model.voidweaver

import de.fuballer.mcendgame.client.component.item.custom.ModelPartDataExtension.createEmptyChild
import de.fuballer.mcendgame.client.component.item.custom.armor.model.CustomArmorModel
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.render.entity.state.BipedEntityRenderState

class VoidweaverModel<S : BipedEntityRenderState>(
    root: ModelPart
) : BipedEntityModel<S>(root), CustomArmorModel {
    override val yOffsetFromEntityPos = 24.5f

    companion object {
        val MODEL_LAYER = EntityModelLayer(IdentifierUtil.default("voidweaver"), "main")

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root

            val head = modelPartData.createEmptyChild(EntityModelPartNames.HEAD)
            val hat = head.createEmptyChild(EntityModelPartNames.HAT)
            val left_leg = modelPartData.createEmptyChild(EntityModelPartNames.LEFT_LEG)
            val right_leg = modelPartData.createEmptyChild(EntityModelPartNames.RIGHT_LEG)

            val body = modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create(), ModelTransform.origin(0.0F, 0.0F, 0.0F))

            val chestplate = body.addChild(
                "chestplate", ModelPartBuilder.create().uv(19, 13).cuboid(-4.0F, 2.5F, -2.0F, 8.0F, 8.0F, 4.0F, Dilation(0.2F))
                    .uv(17, 0).cuboid(-4.5F, -0.5F, -2.6F, 9.0F, 7.0F, 5.0F, Dilation(0.0F)), ModelTransform.origin(0.0F, 0.0F, 0.0F)
            )

            val left_arm = modelPartData.addChild(EntityModelPartNames.LEFT_ARM, ModelPartBuilder.create(), ModelTransform.origin(5.0F, 2.0F, 0.0F))

            val chestplate_left_arm = left_arm.addChild(
                "chestplate_left_arm", ModelPartBuilder.create().uv(46, 12).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 11.0F, 4.0F, Dilation(0.05F))
                    .uv(46, 3).cuboid(-0.85F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, Dilation(0.3F)), ModelTransform.origin(0.0F, 0.0F, 0.0F)
            )

            val right_arm = modelPartData.addChild(EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create(), ModelTransform.origin(-5.0F, 2.0F, 0.0F))

            val chestplate_right_arm = right_arm.addChild(
                "chestplate_right_arm", ModelPartBuilder.create().uv(0, 12).cuboid(-13.0F, -2.0F, -2.0F, 4.0F, 11.0F, 4.0F, Dilation(0.05F))
                    .uv(0, 3).cuboid(-13.15F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, Dilation(0.3F)), ModelTransform.origin(10.0F, 0.0F, 0.0F)
            )

            return TexturedModelData.of(modelData, 64, 32)
        }
    }

    override fun setAngles(bipedEntityRenderState: S) {}
}