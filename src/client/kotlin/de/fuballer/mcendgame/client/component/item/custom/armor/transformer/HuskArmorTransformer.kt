package de.fuballer.mcendgame.client.component.item.custom.armor.transformer

import de.fuballer.mcendgame.client.component.item.custom.armor.model.CustomArmorModel
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.state.BipedEntityRenderState
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.EquipmentSlot

private const val SCALE = 1.0625f

class HuskArmorTransformer : EntityArmorTransformer() {
    override fun transform(slot: EquipmentSlot, matrixStack: MatrixStack, model: BipedEntityModel<out BipedEntityRenderState>) {
        matrixStack.scale(SCALE, SCALE, SCALE)

        if (model !is CustomArmorModel) return

        val originRelativeY = (24 - model.yOffsetFromEntityPos) * SCALE
        val scalingOffset = (model.yOffsetFromEntityPos + originRelativeY) * (SCALE - 1) / 16
        matrixStack.translate(0f, -scalingOffset, 0f)
    }
}