package de.fuballer.mcendgame.components.item.custom.tool

import de.fuballer.mcendgame.util.RegistryUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.item.Item

@Injectable
object CustomToolItems {
    val BLOODHARVEST = RegistryUtil.registerItem(
        ::Item,
        Item.Settings()
            .sword(CustomToolMaterials.BLOODHARVEST, 7F, -2.4F),
        "bloodharvest"
    )
    val TWINFIRE = RegistryUtil.registerItem(
        ::Item,
        Item.Settings()
            .sword(CustomToolMaterials.TWINFIRE, 7F, -2.4F),
        "twinfire"
    )
}