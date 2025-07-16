package de.fuballer.mcendgame.main.component.dungeon.level

import de.fuballer.mcendgame.main.component.dungeon.completion.DungeonCompletedEvent
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonPlayerDeathEvent
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonPlayerIncreaseProgressCommand
import de.maucon.mauconframework.command.CommandGateway
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import kotlin.math.max

@Injectable
class DungeonLevelService {
    @EventSubscriber
    fun on(event: DungeonPlayerDeathEvent) {
        if (event.isClient) return

        val dungeonPlayer = event.dungeonPlayer
        val player = dungeonPlayer.playerEntity

        val playerLevel = dungeonPlayer.dungeonLevel

        playerLevel.level = max(playerLevel.level - 1, 1)
        playerLevel.levelProgress = 0

        dungeonPlayer.dungeonLevel = playerLevel
        player.sendMessage(DungeonLevelSettings.getRegressMessage(playerLevel.level, playerLevel.levelProgress), false)
    }

    @EventSubscriber
    fun on(event: DungeonCompletedEvent) {
        val dungeonWorld = event.dungeonWorld

        val dungeonPlayerIncreaseProgressCommand = DungeonPlayerIncreaseProgressCommand(dungeonWorld.aspects)
        val cmd = CommandGateway.apply(dungeonPlayerIncreaseProgressCommand)

        event.dungeonPlayers.forEach { dungeonPlayer ->
            val playerLevel = dungeonPlayer.dungeonLevel

            if (playerLevel.level > dungeonWorld.level) {
                dungeonPlayer.playerEntity.sendMessage(DungeonLevelSettings.NO_PROGRESS_MESSAGE, false)

                return@forEach
            }

            val helpLevelProgress = (playerLevel.levelProgress + cmd.progressGranted)
            playerLevel.level += helpLevelProgress / DungeonLevelSettings.LEVEL_INCREASE_THRESHOLD
            playerLevel.levelProgress = helpLevelProgress % DungeonLevelSettings.LEVEL_INCREASE_THRESHOLD

            dungeonPlayer.dungeonLevel = playerLevel
            dungeonPlayer.playerEntity.sendMessage(DungeonLevelSettings.getProgressMessage(playerLevel.level, playerLevel.levelProgress), false)
        }
    }
}