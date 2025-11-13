package de.fuballer.mcendgame.main.component.item.custom.totem

import de.fuballer.mcendgame.main.component.item.custom.totem.item.*
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
object TotemItems {
    val TOTEM_OF_BASTION = TotemItemRegistry.registerTotemItem(::TotemOfBastionItem, "totem_of_bastion")
    val TOTEM_OF_FORCE = TotemItemRegistry.registerTotemItem(::TotemOfForceItem, "totem_of_force")
    val TOTEM_OF_FORTRESS = TotemItemRegistry.registerTotemItem(::TotemOfFortressItem, "totem_of_fortress")
    val TOTEM_OF_FRENZY = TotemItemRegistry.registerTotemItem(::TotemOfFrenzyItem, "totem_of_frenzy")
    val TOTEM_OF_GRACE = TotemItemRegistry.registerTotemItem(::TotemOfGraceItem, "totem_of_grace")
    val TOTEM_OF_IMPACT = TotemItemRegistry.registerTotemItem(::TotemOfImpactItem, "totem_of_impact")
    val TOTEM_OF_SWIFTNESS = TotemItemRegistry.registerTotemItem(::TotemOfSwiftnessItem, "totem_of_swiftness")
    val TOTEM_OF_THICKNESS = TotemItemRegistry.registerTotemItem(::TotemOfThicknessItem, "totem_of_thickness")
    val TOTEM_OF_VANGUARD = TotemItemRegistry.registerTotemItem(::TotemOfVanguardItem, "totem_of_vanguard")
}