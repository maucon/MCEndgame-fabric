package de.fuballer.mcendgame.main.messaging.dungeon

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem
import de.fuballer.mcendgame.main.util.extension.mixin.WorldMixinExtension.getDungeonAspects
import net.minecraft.entity.LivingEntity
import net.minecraft.server.world.ServerWorld

data class DungeonEnemiesGeneratedCommand(
    val world: ServerWorld,
    val enemies: List<LivingEntity>,
    val aspects: Map<AspectItem, Int>,
) {
    companion object {
        fun of(world: ServerWorld, enemies: List<LivingEntity>) = DungeonEnemiesGeneratedCommand(world, enemies, world.getDungeonAspects())
    }
}