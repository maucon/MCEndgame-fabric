package de.fuballer.mcendgame.main.component.dungeon.generation.encounter.encounters.scarred_one

import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.EncounterType
import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.encounters.scarred_one.event.ScarredOneInteractEvent
import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.encounters.scarred_one.screen.ScarredOneEffectsPayload
import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.messaging.CollectDungeonEncountersCommand
import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.messaging.GenerateDungeonEncountersEvent
import de.fuballer.mcendgame.main.component.entity.custom.CustomEntities
import de.fuballer.mcendgame.main.component.entity.custom.entities.scarred_one.ScarredOneEntity
import de.fuballer.mcendgame.main.util.extension.Vec3iExtension.toVec3d
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.command.argument.EntityAnchorArgumentType

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
            val entity = ScarredOneEntity(CustomEntities.SCARRED_ONE, world)
            entity.setPosition(encounterLocation.location.toVec3d().add(0.5, 0.0, 0.5))
            entity.isInvulnerable = true
            entity.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, encounterLocation.facingToLocation.toVec3d().add(0.5, 1.0, 0.5))
            world.spawnEntity(entity)
        }
    }

    @EventSubscriber(sync = true)
    fun on(event: ScarredOneInteractEvent) {
        val scarredOne = event.scarredOne
        if (!scarredOne.hasRolledEffects()) {
            scarredOne.positiveEffects = ScarredOneEncounterSettings.getPositiveEffects()
            scarredOne.negativeEffects = ScarredOneEncounterSettings.getNegativeEffects()
        }

        val payload = ScarredOneEffectsPayload(scarredOne.positiveEffects, scarredOne.negativeEffects)
        ServerPlayNetworking.send(event.player, payload)
    }
}