package de.fuballer.mcendgame.components.entity.custom

import de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist.ElfDuelistEntity
import de.fuballer.mcendgame.components.entity.custom.entities.swamp_golem.SwampGolemEntity
import de.fuballer.mcendgame.util.RegistryUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup

@Injectable
object CustomEntities {
    val SWAMP_GOLEM = RegistryUtil.registerEntity(
        "swamp_golem",
        EntityType.Builder.create({ type, world -> SwampGolemEntity(type, world) }, SpawnGroup.MONSTER)
            .dimensions(0.8f, 1.95f)
            .eyeHeight(1.65f)
            .passengerAttachments(1.8125f)
            .vehicleAttachment(-0.7f)
            .maxTrackingRange(8)
    )
    val ELF_DUELIST = RegistryUtil.registerEntity(
        "elf_duelist",
        EntityType.Builder.create({ type, world -> ElfDuelistEntity(type, world) }, SpawnGroup.MONSTER)
            .dimensions(0.45f, 1.99f)
            .eyeHeight(1.8f)
            .passengerAttachments(1.8125f)
            .vehicleAttachment(-0.7f)
            .maxTrackingRange(8)
    )
}