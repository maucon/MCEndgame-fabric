package de.fuballer.mcendgame.main.component.item.custom.aspect

import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.item.ItemStack
import net.minecraft.util.collection.DefaultedList

@Injectable
class AspectService {
    fun getAffectingAspects(
        affectingItemStacks: DefaultedList<ItemStack>,
    ): Map<AspectItem, Int> {
        val aspectItemStacks = affectingItemStacks
            .filter { it.item is AspectItem }
            .sortedBy { (it.item as AspectItem).tier }

        val affectingAspects = HashMap<AspectItem, Int>()
        val disabledAspects = mutableSetOf<AspectItem>()

        aspectItemStacks.forEach {
            val item = it.item as AspectItem

            if (disabledAspects.contains(item)) return@forEach
            if ((affectingAspects[item] ?: 0) >= item.limit) return@forEach

            it.decrement(1)

            disabledAspects.addAll(item.disabledAspects)

            affectingAspects[item] = (affectingAspects[item] ?: 0) + 1
        }

        return affectingAspects
    }
}