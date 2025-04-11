package de.fuballer.mcendgame.components.entity.custom.feature

import de.fuballer.mcendgame.components.entity.custom.feature.webbed.WebbedFeatureRenderer
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback
import net.minecraft.client.render.entity.feature.FeatureRendererContext
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.state.LivingEntityRenderState

@Injectable
class FeatureRegisterer {
    @Initializer
    fun init() {
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register { _, renderer, registrationHelper, context ->
            val livingEntityRenderer =
                renderer as FeatureRendererContext<LivingEntityRenderState, EntityModel<LivingEntityRenderState>>
            registrationHelper.register(WebbedFeatureRenderer(livingEntityRenderer, context))
        }
    }
}