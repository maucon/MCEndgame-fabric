package de.fuballer.client.mcendgame.components.item.custom.armor

import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback
import net.minecraft.client.render.entity.feature.FeatureRendererContext
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.state.BipedEntityRenderState

@Injectable
class ArmorFeatureRegisterer {
    @Initializer
    fun init() {
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register { _, renderer, registrationHelper, context ->
            if (renderer.model !is BipedEntityModel) return@register
            @Suppress("UNCHECKED_CAST") val bipedEntityRenderer =
                renderer as FeatureRendererContext<BipedEntityRenderState, BipedEntityModel<BipedEntityRenderState>>
            registrationHelper.register(CustomHumanoidArmorFeatureRenderer(bipedEntityRenderer, context))
        }
    }
}