package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.entity.LivingEntity

data class MagicFindCommand(
    val entity: LivingEntity,
    var magicFind: Int = 0
)