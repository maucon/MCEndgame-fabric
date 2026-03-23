package de.fuballer.mcendgame.main.component.item.custom.aspect.item.fortitude

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItems
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonEnemiesGeneratedCommand
import de.fuballer.mcendgame.main.util.extension.mixin.EntityMixinExtension.addCustomAttribute
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
object AspectOfFortitudeService {
    @CommandHandler
    fun on(command: DungeonEnemiesGeneratedCommand) {
        val amount = command.aspects[AspectItems.ASPECT_OF_FORTITUDE] ?: return

        command.enemies.forEach { enemy ->
            repeat(amount) {
                enemy.addCustomAttribute(AspectOfFortitude.LESS_DAMAGE_TAKEN_ATTRIBUTE)
                enemy.addCustomAttribute(AspectOfFortitude.INCREASED_LOOT_ATTRIBUTE)
            }
        }
    }
}