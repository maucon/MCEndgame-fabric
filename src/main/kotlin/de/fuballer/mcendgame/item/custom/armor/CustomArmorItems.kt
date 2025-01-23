package de.fuballer.mcendgame.item.custom.armor

import de.fuballer.mcendgame.item.custom.armor.helmet.iceborne.IceborneArmorMaterial
import de.fuballer.mcendgame.util.RegistryKeyUtil
import de.fuballer.mcendgame.util.RegistryUtil
import de.maucon.mauconframework.annotation.Injectable
import net.minecraft.item.Item
import net.minecraft.item.equipment.EquipmentType
import net.minecraft.registry.RegistryKey

@Injectable
object CustomArmorItems {
    val ICEBORNE = RegistryUtil.registerArmorItem(IceborneArmorMaterial, EquipmentType.HELMET, "iceborne")
}