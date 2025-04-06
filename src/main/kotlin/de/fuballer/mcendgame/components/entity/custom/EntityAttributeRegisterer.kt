package de.fuballer.mcendgame.components.entity.custom

import de.fuballer.mcendgame.components.entity.custom.swamp_golem.SwampGolemEntity
import de.maucon.mauconframework.initializer.Initializer
import de.maucon.mauconframework.di.annotation.Injectable
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry

@Injectable
object EntityAttributeRegisterer {
    @Initializer
    fun register() {
        FabricDefaultAttributeRegistry.register(CustomEntities.SWAMP_GOLEM, SwampGolemEntity.createAttributes())
        FabricDefaultAttributeRegistry.register(CustomEntities.ELF_DUELIST, ElfDuelistEntity.createAttributes())
    }
}