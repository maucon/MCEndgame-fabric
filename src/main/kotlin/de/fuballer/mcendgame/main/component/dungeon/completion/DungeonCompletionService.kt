package de.fuballer.mcendgame.main.component.dungeon.completion

import de.fuballer.mcendgame.main.messaging.dungeon.DungeonBossDeathCommand
import de.fuballer.mcendgame.main.util.extension.mixin.WorldMixinExtension.isDungeonCompleted
import de.fuballer.mcendgame.main.util.extension.mixin.WorldMixinExtension.setDungeonCompleted
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventGateway
import net.minecraft.server.world.ServerWorld

@Injectable
class DungeonCompletionService {
    @CommandHandler
    fun on(cmd: DungeonBossDeathCommand) {
        val dungeonWorld = cmd.world
        if (dungeonWorld !is ServerWorld) return

        val players = dungeonWorld.players.toList()

        if (dungeonWorld.isDungeonCompleted()) return
        dungeonWorld.setDungeonCompleted()

        val dungeonCompletedEvent = DungeonCompletedEvent(cmd.isClient, dungeonWorld, players)
        EventGateway.launchPublish(dungeonCompletedEvent)
    }
}