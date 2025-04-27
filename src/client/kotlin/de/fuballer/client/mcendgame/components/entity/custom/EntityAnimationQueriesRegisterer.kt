package de.fuballer.client.mcendgame.components.entity.custom

import de.fuballer.mcendgame.MCEndgame
import de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist.ElfDuelistEntity
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import software.bernie.geckolib.loading.math.MolangQueries

@Injectable
class EntityAnimationQueriesRegisterer {
    @Initializer
    fun init() {
        MolangQueries.setActorVariable<ElfDuelistEntity>("query.${MCEndgame.MOD_ID}_limb_swing_amplitude")
        { actor -> actor.animatable.getLimbSwingAmplitude(actor.partialTick) }

        MolangQueries.setActorVariable<ElfDuelistEntity>("query.${MCEndgame.MOD_ID}_limb_swing_cycle")
        { actor -> actor.animatable.getLimbSwingCycle(actor.partialTick) }
    }
}