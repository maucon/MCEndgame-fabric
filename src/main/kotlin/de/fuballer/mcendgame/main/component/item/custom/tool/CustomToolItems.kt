package de.fuballer.mcendgame.main.component.item.custom.tool

import de.fuballer.mcendgame.main.component.item.custom.UniqueItemRegistry
import de.fuballer.mcendgame.main.component.item.custom.tool.item.Bloodharvest
import de.fuballer.mcendgame.main.component.item.custom.tool.item.Twinfire
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.item.Item

@Injectable
object CustomToolItems {
    val BLOODHARVEST = UniqueItemRegistry.registerToolItem(
        ::Bloodharvest,
        Item.Settings().sword(CustomToolMaterials.BLOODHARVEST, 7F, -2.4F),
        "bloodharvest"
    )
    val TWINFIRE = UniqueItemRegistry.registerToolItem(
        ::Twinfire,
        Item.Settings().sword(CustomToolMaterials.TWINFIRE, 7F, -2.4F),
        "twinfire"
    )
}