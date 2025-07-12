package de.fuballer.mcendgame.main.component.item.custom.aspect

import de.fuballer.mcendgame.main.component.item.custom.aspect.item.aspect_of_greed.AspectOfGreed
import de.fuballer.mcendgame.main.component.item.custom.aspect.item.aspect_of_tyranny.AspectOfTyranny
import de.fuballer.mcendgame.main.util.minecraft.RegistryUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.util.Rarity

@Injectable
object AspectItems {
    val ASPECT_OF_TYRANNY = RegistryUtil.registerAspectItem(::AspectOfTyranny, "aspect_of_tyranny", Rarity.UNCOMMON)
    val ASPECT_OF_GREED = RegistryUtil.registerAspectItem(::AspectOfGreed, "aspect_of_greed", Rarity.UNCOMMON)
}