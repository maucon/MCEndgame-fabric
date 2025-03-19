package de.fuballer.mcendgame.components.custom_attributes

import de.fuballer.mcendgame.components.custom_attributes.CustomAttributesExtensions.setCustomAttributes
import de.fuballer.mcendgame.components.custom_attributes.data.CustomAttribute
import de.fuballer.mcendgame.functional.item_tag.ItemTag
import de.fuballer.mcendgame.functional.item_tag.ItemTagsExtensions.addItemTag
import de.maucon.mauconframework.annotation.Initialize
import de.maucon.mauconframework.annotation.Injectable
import net.fabricmc.fabric.api.event.player.AttackEntityCallback
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.world.World

@Injectable
class TestListener { // TODO REMOVE
//    @Initialize
    fun on() =
        AttackEntityCallback.EVENT.register { player: PlayerEntity, world: World, hand: Hand, _: Entity, _: EntityHitResult? ->
            if (world.isClient) return@register ActionResult.PASS
            if (hand != Hand.MAIN_HAND) return@register ActionResult.PASS

            val attributes = listOf<CustomAttribute>(

            )
            player.mainHandStack.setCustomAttributes(attributes)

            player.mainHandStack.addItemTag(ItemTag.CORRUPTED)

            return@register ActionResult.PASS
        }
}