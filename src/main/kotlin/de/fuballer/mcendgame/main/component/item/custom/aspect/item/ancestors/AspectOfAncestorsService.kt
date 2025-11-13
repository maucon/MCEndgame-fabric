package de.fuballer.mcendgame.main.component.item.custom.aspect.item.ancestors

import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.EncounterType
import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.messaging.CollectDungeonEncountersCommand
import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItems
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class AspectOfAncestorsService {
    @CommandHandler
    fun on(cmd: CollectDungeonEncountersCommand) {
        val aspectCount = cmd.aspects[AspectItems.ASPECT_OF_ANCESTORS] ?: return
        cmd.add(EncounterType.TOTEM, aspectCount)
    }
}