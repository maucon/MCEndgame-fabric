package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.entity.LivingEntity
import net.minecraft.world.World

/**
 * server-side only
 */
data class LivingEntityDropCommand(
    val world: World,
    val entity: LivingEntity,
    val causedByPlayer: Boolean,

    var dropLoot: Boolean = true,
    var dropEquipment: Boolean = true,
    var dropInventory: Boolean = true,
    var dropExperience: Boolean = true,
) {
    constructor(entity: LivingEntity, causedByPlayer: Boolean)
            : this(entity.entityWorld, entity, causedByPlayer)
}