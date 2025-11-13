package de.fuballer.mcendgame.main.component.totem.db

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import de.maucon.mauconframework.stereotype.Entity
import net.minecraft.item.ItemStack
import net.minecraft.util.Uuids
import java.util.*

class PlayerTotemsEntity(
    override var id: UUID,
    var totems: List<ItemStack> = listOf(),
) : Entity<UUID> {
    companion object {
        val CODEC: Codec<PlayerTotemsEntity> =
            RecordCodecBuilder.create { instance ->
                instance.group(
                    Uuids.CODEC.fieldOf("id").forGetter(PlayerTotemsEntity::id),
                    ItemStack.CODEC.listOf().fieldOf("totems").forGetter(PlayerTotemsEntity::totems),
                ).apply(instance) { id, totems -> PlayerTotemsEntity(id, totems) }
            }
    }
}