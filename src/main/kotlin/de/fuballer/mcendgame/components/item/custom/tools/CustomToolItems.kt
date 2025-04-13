package de.fuballer.mcendgame.components.item.custom.tools

import de.fuballer.mcendgame.util.RegistryUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.item.SwordItem

@Injectable
object CustomToolItems {
    val BLOODHARVEST = RegistryUtil.registerToolItem(
        CustomToolMaterials.BLOODHARVEST,
        7.0F,
        -2.4F,
        "bloodharvest",
        ::SwordItem
    )
}