package de.fuballer.mcendgame.main.component.item.custom.armor

import de.fuballer.mcendgame.main.component.item.custom.armor.item.bound_abyss.BoundAbyss
import de.fuballer.mcendgame.main.component.item.custom.armor.item.druids.DruidsBoots
import de.fuballer.mcendgame.main.component.item.custom.armor.item.druids.DruidsChestplate
import de.fuballer.mcendgame.main.component.item.custom.armor.item.druids.DruidsHelmet
import de.fuballer.mcendgame.main.component.item.custom.armor.item.druids.DruidsLeggings
import de.fuballer.mcendgame.main.component.item.custom.armor.item.emberchant.Emberchant
import de.fuballer.mcendgame.main.component.item.custom.armor.item.iceborne.Iceborne
import de.fuballer.mcendgame.main.component.item.custom.armor.item.lamias_gift.LamiasGift
import de.fuballer.mcendgame.main.component.item.custom.armor.item.wither_rose.WitherRoseBoots
import de.fuballer.mcendgame.main.component.item.custom.armor.item.wither_rose.WitherRoseChestplate
import de.fuballer.mcendgame.main.component.item.custom.armor.item.wither_rose.WitherRoseHelmet
import de.fuballer.mcendgame.main.component.item.custom.armor.item.wither_rose.WitherRoseLeggings
import de.fuballer.mcendgame.main.component.item.custom.armor.materials.*
import de.fuballer.mcendgame.main.util.minecraft.RegistryUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.item.equipment.EquipmentType

@Injectable
object CustomArmorItems {
    val ICEBORNE = RegistryUtil.registerArmorItem(::Iceborne, IceborneArmorMaterial, EquipmentType.HELMET, "iceborne")
    val BOUND_ABYSS = RegistryUtil.registerArmorItem(::BoundAbyss, BoundAbyssArmorMaterial, EquipmentType.CHESTPLATE, "bound_abyss")
    val DRUIDS_HELMET = RegistryUtil.registerArmorItem(::DruidsHelmet, DruidsArmorMaterial, EquipmentType.HELMET, "druids_helmet")
    val DRUIDS_CHESTPLATE = RegistryUtil.registerArmorItem(::DruidsChestplate, DruidsArmorMaterial, EquipmentType.CHESTPLATE, "druids_chestplate")
    val DRUIDS_LEGGINGS = RegistryUtil.registerArmorItem(::DruidsLeggings, DruidsArmorMaterial, EquipmentType.LEGGINGS, "druids_leggings")
    val DRUIDS_BOOTS = RegistryUtil.registerArmorItem(::DruidsBoots, DruidsArmorMaterial, EquipmentType.BOOTS, "druids_boots")
    val EMBERCHANT = RegistryUtil.registerArmorItem(::Emberchant, EmberchantArmorMaterial, EquipmentType.HELMET, "emberchant")
    val LAMIAS_GIFT = RegistryUtil.registerArmorItem(::LamiasGift, LamiasGiftArmorMaterial, EquipmentType.LEGGINGS, "lamias_gift")
    val WITHER_ROSE_HELMET = RegistryUtil.registerArmorItem(::WitherRoseHelmet, WitherRoseArmorMaterial, EquipmentType.HELMET, "wither_rose_helmet")
    val WITHER_ROSE_CHESTPLATE = RegistryUtil.registerArmorItem(::WitherRoseChestplate, WitherRoseArmorMaterial, EquipmentType.CHESTPLATE, "wither_rose_chestplate")
    val WITHER_ROSE_LEGGINGS = RegistryUtil.registerArmorItem(::WitherRoseLeggings, WitherRoseArmorMaterial, EquipmentType.LEGGINGS, "wither_rose_leggings")
    val WITHER_ROSE_BOOTS = RegistryUtil.registerArmorItem(::WitherRoseBoots, WitherRoseArmorMaterial, EquipmentType.BOOTS, "wither_rose_boots")
}