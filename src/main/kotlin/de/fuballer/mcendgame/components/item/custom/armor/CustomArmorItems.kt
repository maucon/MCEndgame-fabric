package de.fuballer.mcendgame.components.item.custom.armor

import de.fuballer.mcendgame.components.item.custom.armor.materials.*
import de.fuballer.mcendgame.util.RegistryUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.item.equipment.EquipmentType

@Injectable
object CustomArmorItems {
    val ICEBORNE = RegistryUtil.registerArmorItem(IceborneArmorMaterial, EquipmentType.HELMET, "iceborne")
    val BOUND_ABYSS = RegistryUtil.registerArmorItem(BoundAbyssArmorMaterial, EquipmentType.CHESTPLATE, "bound_abyss")
    val DRUIDS_HELMET = RegistryUtil.registerArmorItem(DruidsArmorMaterial, EquipmentType.HELMET, "druids_helmet")
    val DRUIDS_CHESTPLATE = RegistryUtil.registerArmorItem(DruidsArmorMaterial, EquipmentType.CHESTPLATE, "druids_chestplate")
    val DRUIDS_LEGGINGS = RegistryUtil.registerArmorItem(DruidsArmorMaterial, EquipmentType.LEGGINGS, "druids_leggings")
    val DRUIDS_BOOTS = RegistryUtil.registerArmorItem(DruidsArmorMaterial, EquipmentType.BOOTS, "druids_boots")
    val EMBERCHANT = RegistryUtil.registerArmorItem(EmberchantArmorMaterial, EquipmentType.HELMET, "emberchant")
    val LAMIAS_GIFT = RegistryUtil.registerArmorItem(LamiasGiftArmorMaterial, EquipmentType.LEGGINGS, "lamias_gift")
    val GEISTERGALOSCHEN = RegistryUtil.registerArmorItem(GeistergaloschenArmorMaterial, EquipmentType.BOOTS, "geistergaloschen")
}