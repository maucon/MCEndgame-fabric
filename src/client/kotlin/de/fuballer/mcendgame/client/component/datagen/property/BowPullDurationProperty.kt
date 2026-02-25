package de.fuballer.mcendgame.client.component.datagen.property

import com.mojang.serialization.MapCodec
import de.fuballer.mcendgame.main.util.extension.EntityExtension.getBowFullPullTicks
import net.minecraft.client.render.item.property.numeric.NumericProperty
import net.minecraft.client.render.item.property.numeric.UseDurationProperty
import net.minecraft.client.world.ClientWorld
import net.minecraft.item.ItemStack
import net.minecraft.util.HeldItemContext
import kotlin.math.min

class BowPullDurationProperty() : NumericProperty {
    companion object {
        val CODEC: MapCodec<BowPullDurationProperty> = MapCodec.unit(BowPullDurationProperty())
    }

    override fun getCodec(): MapCodec<BowPullDurationProperty> = CODEC

    override fun getValue(stack: ItemStack, world: ClientWorld?, context: HeldItemContext?, seed: Int): Float {
        val holder = context?.entity ?: return 0F
        if (holder.activeItem != stack) return 0F
        val ticksUsed = UseDurationProperty.getTicksUsedSoFar(stack, holder)
        val maxTicks = holder.getBowFullPullTicks()
        return min(ticksUsed.toFloat() / maxTicks, 1F)
    }
}