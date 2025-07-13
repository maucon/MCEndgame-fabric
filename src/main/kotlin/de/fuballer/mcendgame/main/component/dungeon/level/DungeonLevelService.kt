package de.fuballer.mcendgame.main.component.dungeon.level

import de.fuballer.mcendgame.main.accessor.PlayerEntityDungeonLevelAccessor
import de.fuballer.mcendgame.main.component.dungeon.completion.DungeonCompletedEvent
import de.fuballer.mcendgame.main.component.dungeon.world.DungeonWorld
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonPlayerDeathEvent
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonPlayerIncreaseProgressCommand
import de.maucon.mauconframework.command.CommandGateway
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import kotlin.math.max

@Injectable
class DungeonLevelService {
    fun getLevel(player: PlayerEntity): Int {
        if (player !is PlayerEntityDungeonLevelAccessor) return 1

        val playerDungeonLevel = player.`mcendgame$getDungeonLevel`()

        return playerDungeonLevel.level
    }

    @EventSubscriber
    fun on(event: DungeonPlayerDeathEvent) {
        if (event.isClient) return

        val player = event.player

        val playerLevelAccessor = player as? PlayerEntityDungeonLevelAccessor ?: return
        val playerLevel = playerLevelAccessor.`mcendgame$getDungeonLevel`()

        playerLevel.level = max(playerLevel.level - 1, 1)
        playerLevel.levelProgress = 0

        playerLevelAccessor.`mcendgame$setDungeonLevel`(playerLevel)
        player.sendMessage(DungeonLevelSettings.getRegressMessage(playerLevel.level, playerLevel.levelProgress), false)
    }

    @EventSubscriber
    fun on(event: DungeonCompletedEvent) {
        val serverWorld = event.world as? ServerWorld ?: return
        val dungeonWorld = DungeonWorld(serverWorld)

        val dungeonPlayerIncreaseProgressCommand = DungeonPlayerIncreaseProgressCommand(dungeonWorld.dungeon.`mcendgame$getAspects`())
        val cmd = CommandGateway.apply(dungeonPlayerIncreaseProgressCommand)

        event.players.forEach { player ->
            val playerLevelAccessor = player as? PlayerEntityDungeonLevelAccessor ?: return@forEach
            val playerLevel = playerLevelAccessor.`mcendgame$getDungeonLevel`()

            if (playerLevel.level > event.dungeonLevel) {
                player.sendMessage(DungeonLevelSettings.NO_PROGRESS_MESSAGE, false)

                return@forEach
            }

            val helpLevelProgress = (playerLevel.levelProgress + cmd.progressGranted)
            playerLevel.level += helpLevelProgress / DungeonLevelSettings.LEVEL_INCREASE_THRESHOLD
            playerLevel.levelProgress = helpLevelProgress % DungeonLevelSettings.LEVEL_INCREASE_THRESHOLD

            playerLevelAccessor.`mcendgame$setDungeonLevel`(playerLevel)

            player.sendMessage(DungeonLevelSettings.getProgressMessage(playerLevel.level, playerLevel.levelProgress), false)
        }
    }
}