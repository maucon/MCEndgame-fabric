package de.fuballer.mcendgame.main.component.item.custom.aspect

import de.fuballer.mcendgame.main.component.item.custom.aspect.item.AspectOfTyranny
import de.fuballer.mcendgame.main.util.minecraft.RegistryUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.util.Rarity

@Injectable
object AspectItems {
    val ASPECT_OF_TYRANNY = RegistryUtil.registerAspectItem(::AspectOfTyranny, "aspect_of_tyranny", Rarity.UNCOMMON)
}