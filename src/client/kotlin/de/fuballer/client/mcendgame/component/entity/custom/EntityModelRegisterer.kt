package de.fuballer.client.mcendgame.component.entity.custom

import de.fuballer.client.mcendgame.component.entity.custom.entities.arachne.ArachneEntityModel
import de.fuballer.client.mcendgame.component.entity.custom.entities.arachne.ArachneRenderer
import de.fuballer.client.mcendgame.component.entity.custom.entities.bonecrusher.BonecrusherRenderState
import de.fuballer.client.mcendgame.component.entity.custom.entities.bonecrusher.BonecrusherRenderer
import de.fuballer.client.mcendgame.component.entity.custom.entities.elf_duelist.ElfDuelistRenderState
import de.fuballer.client.mcendgame.component.entity.custom.entities.elf_duelist.ElfDuelistRenderer
import de.fuballer.client.mcendgame.component.entity.custom.entities.portal.PortalRenderer
import de.fuballer.client.mcendgame.component.entity.custom.entities.portal.type.default_.DefaultPortalEntityModel
import de.fuballer.client.mcendgame.component.entity.custom.entities.portal.type.legacy.LegacyPortalEntityModel
import de.fuballer.client.mcendgame.component.entity.custom.entities.swamp_golem.SwampGolemEntityModel
import de.fuballer.client.mcendgame.component.entity.custom.entities.swamp_golem.SwampGolemRenderer
import de.fuballer.client.mcendgame.component.entity.custom.entities.webhook.WebhookRenderer
import de.fuballer.client.mcendgame.component.entity.custom.entities.webshot.WebshotEntityModel
import de.fuballer.client.mcendgame.component.entity.custom.entities.webshot.WebshotRenderer
import de.fuballer.mcendgame.component.entity.custom.CustomEntities
import de.fuballer.mcendgame.component.portal.Portals
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry

@Injectable
object EntityModelRegisterer {
    @Initializer
    fun register() {
        EntityModelLayerRegistry.registerModelLayer(
            SwampGolemEntityModel.SWAMP_GOLEM,
            SwampGolemEntityModel::getTexturedModelData
        )
        EntityRendererRegistry.register(CustomEntities.SWAMP_GOLEM, ::SwampGolemRenderer)

        EntityModelLayerRegistry.registerModelLayer(
            ArachneEntityModel.ARACHNE,
            ArachneEntityModel::getTexturedModelData
        )
        EntityRendererRegistry.register(CustomEntities.ARACHNE, ::ArachneRenderer)

        EntityModelLayerRegistry.registerModelLayer(
            WebshotEntityModel.WEBSHOT,
            WebshotEntityModel::getTexturedModelData
        )
        EntityRendererRegistry.register(CustomEntities.WEBSHOT, ::WebshotRenderer)

        EntityRendererRegistry.register(CustomEntities.WEBHOOK, ::WebhookRenderer)

        EntityRendererRegistry.register(CustomEntities.BONECRUSHER) { state -> BonecrusherRenderer<BonecrusherRenderState>(state) }
        EntityRendererRegistry.register(CustomEntities.ELF_DUELIST) { state -> ElfDuelistRenderer<ElfDuelistRenderState>(state) }

        EntityModelLayerRegistry.registerModelLayer(
            DefaultPortalEntityModel.PORTAL,
            DefaultPortalEntityModel::getTexturedModelData
        )
        EntityModelLayerRegistry.registerModelLayer(
            LegacyPortalEntityModel.PORTAL,
            LegacyPortalEntityModel::getTexturedModelData
        )
        EntityRendererRegistry.register(Portals.ENTITY_TYPE, ::PortalRenderer)
    }
}