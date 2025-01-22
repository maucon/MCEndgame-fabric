package de.fuballer.mcendgame.item.custom.armor

import de.fuballer.mcendgame.MCEndgame
import de.fuballer.mcendgame.item.custom.armor.helmet.iceborne.IceborneArmorMaterial
import de.maucon.mauconframework.annotation.Injectable
import net.minecraft.item.ArmorItem
import net.minecraft.item.Item
import net.minecraft.item.equipment.EquipmentType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

@Injectable
object CustomArmorItems {
    private fun register(item: Item, registryKey: RegistryKey<Item>) =
        Registry.register(Registries.ITEM, registryKey.value, item)

    private fun registerArmor(material: CustomArmorMaterial, type: EquipmentType, registryKey: RegistryKey<Item>) =
        register(
            ArmorItem(
                material.INSTANCE,
                type,
                Item.Settings().registryKey(registryKey)
                    .maxDamage(type.getMaxDamage(material.BASE_DURABILITY))
            ), registryKey
        )

    private val ICEBORNE_KEY: RegistryKey<Item> =
        RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MCEndgame.MOD_ID, "iceborne"))
    val ICEBORNE = registerArmor(IceborneArmorMaterial, EquipmentType.HELMET, ICEBORNE_KEY)
}