package de.fuballer.mcendgame.main.component.item.custom.crystal

import de.fuballer.mcendgame.main.component.item.custom.crystal.item.CalibrationCrystalItem
import de.fuballer.mcendgame.main.component.item.custom.crystal.item.CorruptionCrystalItem
import de.fuballer.mcendgame.main.component.item.custom.crystal.item.PermutationCrystalItem
import de.fuballer.mcendgame.main.component.item.custom.crystal.item.ReforgeCrystalItem
import de.fuballer.mcendgame.main.component.item.custom.crystal.item.SacrificeCrystalItem
import de.fuballer.mcendgame.main.util.minecraft.RegistryUtil
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
object CrystalItems {
    val CALIBRATION_CRYSTAL = RegistryUtil.registerCrystalItem(::CalibrationCrystalItem, "calibration_crystal")
    val SACRIFICIAL_CRYSTAL = RegistryUtil.registerCrystalItem(::SacrificeCrystalItem, "sacrifice_crystal")
    val PERMUTATION_CRYSTAL = RegistryUtil.registerCrystalItem(::PermutationCrystalItem, "permutation_crystal")
    val REFORGE_CRYSTAL = RegistryUtil.registerCrystalItem(::ReforgeCrystalItem, "reforge_crystal")
    val CORRUPTION_CRYSTAL = RegistryUtil.registerCrystalItem(::CorruptionCrystalItem, "corruption_crystal")
}