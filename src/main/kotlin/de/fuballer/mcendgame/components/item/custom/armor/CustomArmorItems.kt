package de.fuballer.mcendgame.components.item.custom.armor

import de.fuballer.mcendgame.components.item.custom.armor.helmet.iceborne.BoundAbyssArmorMaterial
import de.fuballer.mcendgame.components.item.custom.armor.helmet.iceborne.IceborneArmorMaterial
import de.fuballer.mcendgame.util.RegistryUtil
import de.maucon.mauconframework.annotation.Injectable
import net.minecraft.item.equipment.EquipmentType

@Injectable
object CustomArmorItems {
    val ICEBORNE = RegistryUtil.registerArmorItem(IceborneArmorMaterial, EquipmentType.HELMET, "iceborne")
    val BOUND_ABYSS = RegistryUtil.registerArmorItem(BoundAbyssArmorMaterial, EquipmentType.CHESTPLATE, "bound_abyss")
}