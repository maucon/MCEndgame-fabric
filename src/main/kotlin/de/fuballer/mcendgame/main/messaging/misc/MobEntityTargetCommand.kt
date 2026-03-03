package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity
import net.minecraft.world.World

data class MobEntityTargetCommand(
    val world: World,
    val entity: MobEntity,
    val target: LivingEntity?,
    var canTarget: Boolean = true,
) {
    companion object {
        fun of(entity: MobEntity, target: LivingEntity?) = MobEntityTargetCommand(entity.entityWorld, entity, target)
    }
}