package de.fuballer.mcendgame.client.component.item.custom.armor.transformer

import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.state.BipedEntityRenderState
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.EquipmentSlot

abstract class EntityArmorTransformer {
    abstract fun transform(slot: EquipmentSlot, matrixStack: MatrixStack, model: BipedEntityModel<out BipedEntityRenderState>)
}