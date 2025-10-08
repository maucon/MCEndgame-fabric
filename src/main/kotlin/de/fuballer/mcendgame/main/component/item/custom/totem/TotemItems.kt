package de.fuballer.mcendgame.main.component.item.custom.totem

import de.fuballer.mcendgame.main.component.item.custom.totem.item.TotemOfBastionItem
import de.fuballer.mcendgame.main.component.item.custom.totem.item.TotemOfForceItem
import de.fuballer.mcendgame.main.component.item.custom.totem.item.TotemOfFortressItem
import de.fuballer.mcendgame.main.component.item.custom.totem.item.TotemOfFrenzyItem
import de.fuballer.mcendgame.main.component.item.custom.totem.item.TotemOfGraceItem
import de.fuballer.mcendgame.main.component.item.custom.totem.item.TotemOfImpactItem
import de.fuballer.mcendgame.main.component.item.custom.totem.item.TotemOfSwiftnessItem
import de.fuballer.mcendgame.main.component.item.custom.totem.item.TotemOfThicknessItem
import de.fuballer.mcendgame.main.component.item.custom.totem.item.TotemOfVanguardItem
import de.fuballer.mcendgame.main.util.minecraft.RegistryUtil
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
object TotemItems {
    val TOTEM_OF_BASTION = RegistryUtil.registerTotemItem(::TotemOfBastionItem, "totem_of_bastion")
    val TOTEM_OF_FORCE = RegistryUtil.registerTotemItem(::TotemOfForceItem, "totem_of_force")
    val TOTEM_OF_FORTRESS = RegistryUtil.registerTotemItem(::TotemOfFortressItem, "totem_of_fortress")
    val TOTEM_OF_FRENZY = RegistryUtil.registerTotemItem(::TotemOfFrenzyItem, "totem_of_frenzy")
    val TOTEM_OF_GRACE = RegistryUtil.registerTotemItem(::TotemOfGraceItem, "totem_of_grace")
    val TOTEM_OF_IMPACT = RegistryUtil.registerTotemItem(::TotemOfImpactItem, "totem_of_impact")
    val TOTEM_OF_SWIFTNESS = RegistryUtil.registerTotemItem(::TotemOfSwiftnessItem, "totem_of_swiftness")
    val TOTEM_OF_THICKNESS = RegistryUtil.registerTotemItem(::TotemOfThicknessItem, "totem_of_thickness")
    val TOTEM_OF_VANGUARD = RegistryUtil.registerTotemItem(::TotemOfVanguardItem, "totem_of_vanguard")
}