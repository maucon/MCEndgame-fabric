package de.fuballer.mcendgame.main.component.item.custom.totem

import de.fuballer.mcendgame.main.component.item.custom.totem.item.TotemOfBastionItem
import de.fuballer.mcendgame.main.util.minecraft.RegistryUtil
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
object TotemItems {
    val TOTEM_OF_BASTION = RegistryUtil.registerTotemItem(::TotemOfBastionItem, "totem_of_bastion")
}