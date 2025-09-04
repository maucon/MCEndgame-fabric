package de.fuballer.mcendgame.main.messaging.dungeon

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity
import net.minecraft.world.World

data class DungeonFinalBossDeathEvent(
    val isClient: Boolean,
    val world: World,
    val bossEntity: MobEntity,
    val killer: LivingEntity?,
) {
    companion object {
        fun of(e: DungeonBossDeathEvent) = DungeonFinalBossDeathEvent(e.isClient, e.world, e.bossEntity, e.killer)
    }
}