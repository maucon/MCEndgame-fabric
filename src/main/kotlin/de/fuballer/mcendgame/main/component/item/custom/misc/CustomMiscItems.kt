package de.fuballer.mcendgame.main.component.item.custom.misc

import de.fuballer.mcendgame.main.component.item.custom.UniqueItemRegistry
import de.fuballer.mcendgame.main.component.item.custom.misc.horn.VerdantEcho
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.InstrumentComponent
import net.minecraft.item.Instruments
import net.minecraft.item.Item

@Injectable
object CustomMiscItems {
    val VERDANT_ECHO = UniqueItemRegistry.registerMiscItem(
        ::VerdantEcho,
        Item.Settings()
            .maxCount(1)
            .component(DataComponentTypes.INSTRUMENT, InstrumentComponent(Instruments.FEEL_GOAT_HORN)),
        "verdant_echo",
    )
}