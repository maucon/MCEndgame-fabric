package de.fuballer.mcendgame.main.component.totem.db

import de.maucon.mauconframework.stereotype.Entity
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier
import java.util.*

class PlayerTotemVanillaAttributesEntity(
    override var id: UUID,
    var attributes: List<Pair<RegistryEntry<EntityAttribute>, Identifier>> = listOf(),
) : Entity<UUID>