package de.fuballer.mcendgame.components.entity.custom

import de.fuballer.mcendgame.components.entity.custom.swamp_golem.SwampGolemEntity
import de.fuballer.mcendgame.util.RegistryUtil
import de.maucon.mauconframework.annotation.Injectable
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
}