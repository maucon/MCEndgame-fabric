package de.fuballer.mcendgame.main.component.item.custom.aspect

import de.fuballer.mcendgame.main.component.item.custom.aspect.item.curio.AspectOfCurio
import de.fuballer.mcendgame.main.component.item.custom.aspect.item.dominion.AspectOfDominion
import de.fuballer.mcendgame.main.component.item.custom.aspect.item.fortune.AspectOfFortune
import de.fuballer.mcendgame.main.component.item.custom.aspect.item.greed.AspectOfGreed
import de.fuballer.mcendgame.main.component.item.custom.aspect.item.hordes.AspectOfHordes
import de.fuballer.mcendgame.main.component.item.custom.aspect.item.impatience.AspectOfImpatience
import de.fuballer.mcendgame.main.component.item.custom.aspect.item.tyranny.AspectOfTyranny
import de.fuballer.mcendgame.main.util.minecraft.RegistryUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.util.Rarity

@Injectable
object AspectItems {
    val ASPECT_OF_TYRANNY = RegistryUtil.registerAspectItem(::AspectOfTyranny, "aspect_of_tyranny", Rarity.UNCOMMON)
    val ASPECT_OF_GREED = RegistryUtil.registerAspectItem(::AspectOfGreed, "aspect_of_greed", Rarity.UNCOMMON)
    val ASPECT_OF_DOMINION = RegistryUtil.registerAspectItem(::AspectOfDominion, "aspect_of_dominion", Rarity.UNCOMMON)
    val ASPECT_OF_IMPATIENCE = RegistryUtil.registerAspectItem(::AspectOfImpatience, "aspect_of_impatience", Rarity.UNCOMMON)
    val ASPECT_OF_HORDES = RegistryUtil.registerAspectItem(::AspectOfHordes, "aspect_of_hordes", Rarity.UNCOMMON)
    val ASPECT_OF_CURIO = RegistryUtil.registerAspectItem(::AspectOfCurio, "aspect_of_curio", Rarity.UNCOMMON)
    val ASPECT_OF_FORTUNE = RegistryUtil.registerAspectItem(::AspectOfFortune, "aspect_of_fortune", Rarity.UNCOMMON)
}