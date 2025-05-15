package de.fuballer.mcendgame.main.component.item_filter.db

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import de.maucon.mauconframework.stereotype.Entity
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import net.minecraft.util.Uuids
import java.util.*

data class ItemFilterEntity(
    /** id of a player */
    override var id: UUID,
    var items: MutableSet<Item>
) : Entity<UUID> {
    constructor(id: UUID) : this(id, mutableSetOf())

    companion object {
        val CODEC: Codec<ItemFilterEntity> =
            RecordCodecBuilder.create { instance ->
                instance.group(
                    Uuids.CODEC.fieldOf("id").forGetter(ItemFilterEntity::id),
                    Identifier.CODEC.listOf().fieldOf("items")
                        .forGetter { entity ->
                            entity.items
                                .filter { it != Items.AIR }
                                .map { Registries.ITEM.getId(it) }
                        }
                ).apply(instance) { id, itemIds ->
                    val items = itemIds
                        .map { Registries.ITEM.get(it) }
                        .filter { it != Items.AIR }
                        .toMutableSet()

                    ItemFilterEntity(id, items)
                }
            }
    }
}