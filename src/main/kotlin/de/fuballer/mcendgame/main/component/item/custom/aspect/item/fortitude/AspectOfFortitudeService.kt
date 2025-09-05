package de.fuballer.mcendgame.main.component.item.custom.aspect.item.fortitude

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItems
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonEnemiesGeneratedEvent
import de.fuballer.mcendgame.main.util.extension.mixin.EntityMixinExtension.addCustomAttribute
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber

@Injectable
object AspectOfFortitudeService {
    @EventSubscriber
    fun on(event: DungeonEnemiesGeneratedEvent) {
        val amount = event.aspects[AspectItems.ASPECT_OF_FORTITUDE] ?: return

        event.enemies.forEach { enemy ->
            repeat(amount) {
                enemy.addCustomAttribute(AspectOfFortitude.LESS_DAMAGE_TAKEN_ATTRIBUTE)
                enemy.addCustomAttribute(AspectOfFortitude.INCREASED_LOOT_ATTRIBUTE)
            }
        }
    }
}