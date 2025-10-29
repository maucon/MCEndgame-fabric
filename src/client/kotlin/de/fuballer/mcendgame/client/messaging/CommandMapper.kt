package de.fuballer.mcendgame.client.messaging

import de.fuballer.mcendgame.main.accessor.LivingEntityLinkAttributeAccessor
import de.maucon.mauconframework.command.CommandGateway
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents
import net.minecraft.client.MinecraftClient
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.text.Text

@Injectable
object CommandMapper {
    @Initializer
    fun onItemTooltip() = ItemTooltipCallback.EVENT.register { itemStack: ItemStack, context: Item.TooltipContext, tooltipType: TooltipType, texts: MutableList<Text> ->
        val cmd = RenderItemTooltipCommand(itemStack, context, tooltipType, texts)
        CommandGateway.apply(cmd)
    }

    @Initializer
    fun onLivingEntityFeatureRendererRegistration() = LivingEntityFeatureRendererRegistrationCallback.EVENT.register { type, renderer, registrationHelper, context ->
        val cmd = RegisterLivingEntityFeatureRendererCommand(type, renderer, registrationHelper, context)
        CommandGateway.apply(cmd)
    }

    fun onEntitiesRendered() = WorldRenderEvents.AFTER_ENTITIES.register { context ->
        val client = MinecraftClient.getInstance()
        val player = client.player ?: return@register
        if (!client.options.perspective.isFirstPerson) return@register

        val matrixStack = context.matrixStack()
        val vertexConsumerProvider = context.consumers()

        /*player.render

        val cmd = RenderLinksCommand(renderState, matrixStack, vertexConsumerProvider, data)
        CommandGateway.apply(cmd)*/
    }
}
