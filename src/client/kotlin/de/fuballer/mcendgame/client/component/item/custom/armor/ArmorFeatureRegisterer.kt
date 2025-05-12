package de.fuballer.mcendgame.client.component.item.custom.armor

import de.fuballer.mcendgame.client.messaging.RegisterLivingEntityFeatureRendererCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.client.render.entity.feature.FeatureRendererContext
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.PlayerEntityModel
import net.minecraft.client.render.entity.state.BipedEntityRenderState

@Injectable
class ArmorFeatureRegisterer {
    @CommandHandler
    fun on(cmd: RegisterLivingEntityFeatureRendererCommand) {
        val renderer = cmd.entityRenderer
        if (renderer.model !is BipedEntityModel) return

        @Suppress("UNCHECKED_CAST")
        val bipedEntityRenderer = renderer as FeatureRendererContext<BipedEntityRenderState, BipedEntityModel<BipedEntityRenderState>>

        val customHumanoidArmorFeatureRenderer = CustomHumanoidArmorFeatureRenderer(bipedEntityRenderer, cmd.context)
        cmd.registrationHelper.register(customHumanoidArmorFeatureRenderer)
    }
}