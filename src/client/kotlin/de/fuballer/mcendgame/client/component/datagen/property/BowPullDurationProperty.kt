package de.fuballer.mcendgame.client.component.datagen.property

import com.mojang.serialization.MapCodec
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asIntRoll
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getAllCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import net.minecraft.client.render.item.property.numeric.NumericProperty
import net.minecraft.client.render.item.property.numeric.UseDurationProperty
import net.minecraft.client.world.ClientWorld
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import kotlin.math.min

class BowPullDurationProperty() : NumericProperty {
    companion object {
        const val BASE_MAX_PULL_DURATION = 20
        val CODEC: MapCodec<BowPullDurationProperty> = MapCodec.unit(BowPullDurationProperty())
    }

    override fun getCodec(): MapCodec<BowPullDurationProperty> = CODEC

    override fun getValue(stack: ItemStack, world: ClientWorld?, holder: LivingEntity?, seed: Int): Float {
        if (holder == null || holder.activeItem != stack) return 0F
        val ticksUsed = UseDurationProperty.getTicksUsedSoFar(stack, holder)
        val maxTicks = getMaxPullTicks(holder)
        return min(ticksUsed.toFloat() / maxTicks, 1F)
    }

    private fun getMaxPullTicks(holder: LivingEntity): Int {
        val attributes = holder.getAllCustomAttributes()[CustomAttributeTypes.BOW_PULL_TICKS] ?: return BASE_MAX_PULL_DURATION
        val sum = attributes.sumOf { it.rolls[0].asIntRoll().getActualRoll() }
        return BASE_MAX_PULL_DURATION + sum
    }
}