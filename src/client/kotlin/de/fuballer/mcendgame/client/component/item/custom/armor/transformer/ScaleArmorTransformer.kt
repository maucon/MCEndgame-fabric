package de.fuballer.mcendgame.client.component.item.custom.armor.transformer

import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.EquipmentSlot

class ScaleArmorTransformer(
    val scale: Float,
    val scaleOrigin: Float = 24f,
) : EntityArmorTransformer() {
    override fun transform(slot: EquipmentSlot, matrixStack: MatrixStack) {
        matrixStack.scale(scale, scale, scale)
        val totalOffset = scaleOrigin * (scale - 1) / (16 * scale)
        matrixStack.translate(0f, -totalOffset, 0f)
    }
}