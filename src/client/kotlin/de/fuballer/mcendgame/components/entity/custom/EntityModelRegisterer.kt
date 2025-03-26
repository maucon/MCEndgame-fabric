package de.fuballer.mcendgame.components.entity.custom

import de.fuballer.mcendgame.components.entity.custom.entities.arachne.ArachneEntityModel
import de.fuballer.mcendgame.components.entity.custom.entities.arachne.ArachneRenderer
import de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist.ElfDuelistEntityModel
import de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist.ElfDuelistRenderer
import de.fuballer.mcendgame.components.entity.custom.entities.swamp_golem.SwampGolemEntityModel
import de.fuballer.mcendgame.components.entity.custom.entities.swamp_golem.SwampGolemRenderer
import de.maucon.mauconframework.annotation.Initialize
import de.maucon.mauconframework.annotation.Injectable
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry

@Injectable
object EntityModelRegisterer {
    @Initialize
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
    }
}