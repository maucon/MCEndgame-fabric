package de.fuballer.client.mcendgame.components.item.custom.armor.chestplate.bound_abyss

import de.fuballer.client.mcendgame.mixin_interfaces.LivingEntityRenderStateAccessor
import net.minecraft.client.render.entity.state.BipedEntityRenderState

class BoundAbyssAnimation {
    fun setTransforms(
        model: BoundAbyssModel<out BipedEntityRenderState>,
        entityRenderState: BipedEntityRenderState
    ) {
        if (entityRenderState !is LivingEntityRenderStateAccessor) return
        val lowHealthTicks20 = entityRenderState.`mcendgame$getLowHealthTicks20`()

        val openPercent = lowHealthTicks20 / 20F

        model.shoulderPadLeft.transform =
            model.shoulderPadLeft.defaultTransform.addPivot(openPercent * 1.5F, openPercent * -0.8F, 0F)
        model.shoulderPadRight.transform =
            model.shoulderPadRight.defaultTransform.addPivot(openPercent * -1.5F, openPercent * -0.8F, 0F)

        model.vambraceLeft.transform =
            model.vambraceLeft.defaultTransform.addPivot(openPercent * 1.2F, 0F, 0F)
        model.vambraceRight.transform =
            model.vambraceRight.defaultTransform.addPivot(openPercent * -1.2F, 0F, 0F)
    }
}