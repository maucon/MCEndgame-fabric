package de.fuballer.mcendgame.main.component.item.custom.aspect.item.fortune

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItems
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonGenerateEnemiesCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
object AspectOfFortuneService {
    @CommandHandler
    fun onGenerateEnemies(cmd: DungeonGenerateEnemiesCommand) {
        if (!cmd.aspects.contains(AspectItems.ASPECT_OF_FORTUNE)) return
        cmd.lootGoblinLuckyAttributes = true
    }
}