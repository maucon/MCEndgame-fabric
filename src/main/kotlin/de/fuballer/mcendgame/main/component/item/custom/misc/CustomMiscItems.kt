package de.fuballer.mcendgame.main.component.item.custom.misc

import de.fuballer.mcendgame.main.component.item.custom.UniqueItemRegistry
import de.fuballer.mcendgame.main.component.item.custom.misc.horn.FrigidCry
import de.fuballer.mcendgame.main.component.item.custom.misc.horn.MoltenRoar
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
            .component(DataComponentTypes.INSTRUMENT, InstrumentComponent(Instruments.DREAM_GOAT_HORN)),
        "verdant_echo",
    )
    val MOLTEN_ROAR = UniqueItemRegistry.registerMiscItem(
        ::MoltenRoar,
        Item.Settings()
            .maxCount(1)
            .component(DataComponentTypes.INSTRUMENT, InstrumentComponent(Instruments.SEEK_GOAT_HORN)),
        "molten_roar",
    )
    val FRIGID_CRY = UniqueItemRegistry.registerMiscItem(
        ::FrigidCry,
        Item.Settings()
            .maxCount(1)
            .component(DataComponentTypes.INSTRUMENT, InstrumentComponent(Instruments.FEEL_GOAT_HORN)),
        "frigid_cry",
    )
}