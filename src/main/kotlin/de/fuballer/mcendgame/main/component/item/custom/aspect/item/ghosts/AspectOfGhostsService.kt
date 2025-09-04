package de.fuballer.mcendgame.main.component.item.custom.aspect.item.ghosts

import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItems
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonEnemiesGeneratedEvent
import de.fuballer.mcendgame.main.util.extension.mixin.EntityMixinExtension.addCustomAttribute
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber

@Injectable
object AspectOfGhostsService {
    private val ATTRIBUTE = CustomAttribute(CustomAttributeTypes.GHOSTLY_APPEARANCE)

    @EventSubscriber
    fun on(event: DungeonEnemiesGeneratedEvent) {
        if (!event.aspects.contains(AspectItems.ASPECT_OF_GHOSTS)) return
        event.enemies.forEach { it.addCustomAttribute(ATTRIBUTE) }
    }
}