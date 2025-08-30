package de.fuballer.mcendgame.client.component.entity.custom.feature

import de.fuballer.mcendgame.client.component.entity.custom.feature.webbed.WebbedFeatureRenderer
import de.fuballer.mcendgame.client.messaging.RegisterLivingEntityFeatureRendererCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.client.render.entity.feature.FeatureRendererContext
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.state.LivingEntityRenderState

@Injectable
class FeatureRegisterer {
    @CommandHandler
    fun on(cmd: RegisterLivingEntityFeatureRendererCommand) {
        @Suppress("UNCHECKED_CAST")
        val livingEntityRenderer = cmd.entityRenderer as FeatureRendererContext<LivingEntityRenderState, EntityModel<LivingEntityRenderState>>

        val webbedFeatureRenderer = WebbedFeatureRenderer(livingEntityRenderer, cmd.context)
        cmd.registrationHelper.register(webbedFeatureRenderer)
    }
}