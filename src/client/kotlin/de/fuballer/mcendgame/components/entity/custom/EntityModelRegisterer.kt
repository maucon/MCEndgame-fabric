package de.fuballer.mcendgame.components.entity.custom

import de.fuballer.mcendgame.components.entity.custom.entities.arachne.ArachneEntityModel
import de.fuballer.mcendgame.components.entity.custom.entities.arachne.ArachneRenderer
import de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist.ElfDuelistEntityModel
import de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist.ElfDuelistRenderer
import de.fuballer.mcendgame.components.entity.custom.entities.swamp_golem.SwampGolemEntityModel
import de.fuballer.mcendgame.components.entity.custom.entities.swamp_golem.SwampGolemRenderer
import de.maucon.mauconframework.di.annotation.Injectable
import de.fuballer.mcendgame.components.entity.custom.entities.webhook.WebhookRenderer
import de.fuballer.mcendgame.components.entity.custom.entities.webshot.WebshotEntityModel
import de.fuballer.mcendgame.components.entity.custom.entities.webshot.WebshotRenderer
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry

@Injectable
object EntityModelRegisterer {
    @Initializer
    fun register() {
        EntityModelLayerRegistry.registerModelLayer(
            SwampGolemEntityModel.SWAMP_GOLEM,
            SwampGolemEntityModel.Companion::getTexturedModelData
        )
        EntityRendererRegistry.register(CustomEntities.SWAMP_GOLEM, ::SwampGolemRenderer)

        EntityModelLayerRegistry.registerModelLayer(
            ElfDuelistEntityModel.ELF_DUELIST,
            ElfDuelistEntityModel.Companion::getTexturedModelData
        )
        EntityRendererRegistry.register(CustomEntities.ELF_DUELIST, ::ElfDuelistRenderer)

        EntityModelLayerRegistry.registerModelLayer(
            ArachneEntityModel.ARACHNE,
            ArachneEntityModel.Companion::getTexturedModelData
        )
        EntityRendererRegistry.register(CustomEntities.ARACHNE, ::ArachneRenderer)

        EntityModelLayerRegistry.registerModelLayer(
            WebshotEntityModel.WEBSHOT,
            WebshotEntityModel.Companion::getTexturedModelData
        )
        EntityRendererRegistry.register(CustomEntities.WEBSHOT, ::WebshotRenderer)

        EntityRendererRegistry.register(CustomEntities.WEBHOOK, ::WebhookRenderer)
    }
}