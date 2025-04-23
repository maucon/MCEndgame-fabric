package de.fuballer.mcendgame.components.entity.custom

import de.fuballer.mcendgame.components.entity.custom.entities.arachne.ArachneEntity
import de.fuballer.mcendgame.components.entity.custom.entities.bonecrusher.BonecrusherEntity
import de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist.ElfDuelistEntity
import de.fuballer.mcendgame.components.entity.custom.entities.swamp_golem.SwampGolemEntity
import de.fuballer.mcendgame.components.entity.custom.entities.webhook.WebhookEntity
import de.fuballer.mcendgame.components.entity.custom.entities.webshot.WebshotEntity
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
    val ARACHNE = RegistryUtil.registerEntity(
        "arachne",
        EntityType.Builder.create({ type, world -> ArachneEntity(type, world) }, SpawnGroup.MONSTER)
            .dimensions(1.5f, 1.8f)
            .eyeHeight(1.7f)
            .passengerAttachments(1.8125f)
            .vehicleAttachment(-0.7f)
            .maxTrackingRange(8)
    )
    val WEBSHOT = RegistryUtil.registerEntity(
        "webshot",
        EntityType.Builder.create({ type, world -> WebshotEntity(type, world) }, SpawnGroup.MISC)
            .dimensions(0.4f, 0.4f)
    )
    val WEBHOOK = RegistryUtil.registerEntity(
        "webhook",
        EntityType.Builder.create({ type, world -> WebhookEntity(type, world) }, SpawnGroup.MISC)
            .dimensions(0.4f, 0.4f)
    )
    val BONECRUSHER = RegistryUtil.registerEntity(
        "bonecrusher",
        EntityType.Builder.create({ type, world -> BonecrusherEntity(type, world) }, SpawnGroup.MONSTER)
            .dimensions(0.7f, 2.99f)
            .eyeHeight(2.85f)
            .maxTrackingRange(8)
    )
}