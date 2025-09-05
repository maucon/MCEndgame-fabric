package de.fuballer.mcendgame.main.component.item.custom.aspect.item.savagery

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItems
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonEnemiesGeneratedEvent
import de.fuballer.mcendgame.main.util.extension.mixin.EntityMixinExtension.addCustomAttribute
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber

@Injectable
object AspectOfSavageryService {
    @EventSubscriber
    fun on(event: DungeonEnemiesGeneratedEvent) {
        val amount = event.aspects[AspectItems.ASPECT_OF_SAVAGERY] ?: return

        event.enemies.forEach { enemy ->
            repeat(amount) {
                enemy.addCustomAttribute(AspectOfSavagery.MORE_DAMAGE_ATTRIBUTE)
                enemy.addCustomAttribute(AspectOfSavagery.INCREASED_LOOT_ATTRIBUTE)
            }
        }
    }
}