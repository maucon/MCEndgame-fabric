package de.fuballer.mcendgame.main.component.item.custom.crystal

import de.fuballer.mcendgame.main.component.item.custom.crystal.item.CalibrationCrystalItem
import de.fuballer.mcendgame.main.component.item.custom.crystal.item.SacrificialCrystalItem
import de.fuballer.mcendgame.main.util.minecraft.RegistryUtil
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
object CrystalItems {
    val CALIBRATION_CRYSTAL = RegistryUtil.registerCrystalItem(::CalibrationCrystalItem, "calibration_crystal")
    val SACRIFICIAL_CRYSTAL = RegistryUtil.registerCrystalItem(::SacrificialCrystalItem, "sacrificial_crystal")
}