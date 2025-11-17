package de.fuballer.mcendgame.client.component.item.custom.armor.transformer

import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.EquipmentSlot

private const val SCALE = 1.25f

class PiglinArmorTransformer : EntityArmorTransformer() {
    override fun transform(slot: EquipmentSlot, matrixStack: MatrixStack) {
        if (slot != EquipmentSlot.HEAD) return
        matrixStack.scale(SCALE, SCALE, SCALE)
        matrixStack.translate(0f, 0.0625f, 0f)
    }
}