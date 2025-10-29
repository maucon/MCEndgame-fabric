package de.fuballer.mcendgame.client.messaging

import de.fuballer.mcendgame.client.component.entity.custom.data.MultipleEntityConnectionData
import net.minecraft.entity.LivingEntity
import java.util.*

data class CreateLinkDataCommand(
    val linkedEntities: Map<UUID, Long>,
    val entity: LivingEntity,
    val tickDelta: Float,
    val data: MultipleEntityConnectionData = MultipleEntityConnectionData(),
)