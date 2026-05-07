package de.fuballer.mcendgame.main.component.item.custom.aspect.item.duality

import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.EncounterType
import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.encounters.scarred_one.messaging.CollectScarredOneEffectCountCommand
import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.messaging.CollectDungeonEncountersCommand
import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItems
import de.fuballer.mcendgame.main.util.extension.mixin.WorldMixinExtension.getDungeonAspects
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class AspectOfDualityService {
    @CommandHandler
    fun on(cmd: CollectDungeonEncountersCommand) {
        if (!cmd.aspects.containsKey(AspectItems.ASPECT_OF_DUALITY)) return
        cmd.add(EncounterType.SCARRED_ONE)
    }

    @CommandHandler
    fun on(cmd: CollectScarredOneEffectCountCommand) {
        val aspects = cmd.world.getDungeonAspects()
        if (!aspects.containsKey(AspectItems.ASPECT_OF_DUALITY)) return
        cmd.positive *= 2
        cmd.negative *= 2
    }
}