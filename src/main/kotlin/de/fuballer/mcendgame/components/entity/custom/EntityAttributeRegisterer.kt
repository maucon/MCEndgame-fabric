package de.fuballer.mcendgame.components.entity.custom

import de.fuballer.mcendgame.components.entity.custom.entities.arachne.ArachneEntity
import de.fuballer.mcendgame.components.entity.custom.entities.bonecrusher.BonecrusherEntity
import de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist.ElfDuelistEntity
import de.fuballer.mcendgame.components.entity.custom.entities.swamp_golem.SwampGolemEntity
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry

@Injectable
object EntityAttributeRegisterer {
    @Initializer
    fun register() {
        FabricDefaultAttributeRegistry.register(CustomEntities.SWAMP_GOLEM, SwampGolemEntity.createAttributes())
        FabricDefaultAttributeRegistry.register(CustomEntities.ELF_DUELIST, ElfDuelistEntity.createAttributes())
        FabricDefaultAttributeRegistry.register(CustomEntities.ARACHNE, ArachneEntity.createAttributes())
        FabricDefaultAttributeRegistry.register(CustomEntities.BONECRUSHER, BonecrusherEntity.createAttributes())
    }
}