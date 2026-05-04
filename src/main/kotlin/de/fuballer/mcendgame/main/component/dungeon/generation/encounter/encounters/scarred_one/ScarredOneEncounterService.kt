package de.fuballer.mcendgame.main.component.dungeon.generation.encounter.encounters.scarred_one

import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.EncounterType
import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.messaging.CollectDungeonEncountersCommand
import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.messaging.GenerateDungeonEncountersEvent
import de.fuballer.mcendgame.main.util.extension.Vec3iExtension.toVec3d
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.command.argument.EntityAnchorArgumentType
import net.minecraft.entity.EntityType
import net.minecraft.entity.passive.VillagerEntity

@Injectable
class ScarredOneEncounterService {
    @CommandHandler
    fun on(cmd: CollectDungeonEncountersCommand) {
        if (cmd.random.nextDouble() >= ScarredOneEncounterSettings.BASE_PROBABILITY) return
        cmd.add(EncounterType.SCARRED_ONE)
    }

    @EventSubscriber(sync = true)
    fun on(event: GenerateDungeonEncountersEvent) {
        val amount = event.encounters[EncounterType.SCARRED_ONE] ?: return
        if (amount <= 0) return

        val locations = event.takeStartLocations(amount)
        if (locations.isEmpty()) return

        val world = event.world
        locations.forEach { encounterLocation ->
            val entity = VillagerEntity(EntityType.VILLAGER, world)
            entity.setPosition(encounterLocation.location.toVec3d())
            entity.isInvulnerable = true
            entity.isAiDisabled = true
            entity.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, encounterLocation.facingToLocation.toVec3d())
            world.spawnEntity(entity)
        }
    }
}