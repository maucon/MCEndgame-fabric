package de.fuballer.mcendgame.main.component.item.custom.tool

import de.fuballer.mcendgame.main.component.item.custom.UniqueItemRegistry
import de.fuballer.mcendgame.main.component.item.custom.tool.item.*
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
    val FATESPLITTER = UniqueItemRegistry.registerToolItem(
        ::Fatesplitter,
        Item.Settings().axe(CustomToolMaterials.FATESPLITTER, 9F, -3F),
        "fatesplitter"
    )
    val SERPENTS_FANG = UniqueItemRegistry.registerToolItem(
        ::SerpentsFang,
        Item.Settings().sword(CustomToolMaterials.SERPENTS_FANG, 7F, -2.4F),
        "serpents_fang"
    )
    val WINDSTRING = UniqueItemRegistry.registerToolItem(
        ::Windstring,
        Item.Settings().maxDamage(500),
        "windstring"
    )
    val HAILSTORM = UniqueItemRegistry.registerToolItem(
        ::Hailstorm,
        Item.Settings().maxDamage(500),
        "hailstorm"
    )
    val DUSK_PIERCER = UniqueItemRegistry.registerToolItem(
        ::DuskPiercer,
        Item.Settings().maxDamage(500),
        "dusk_piercer"
    )
}