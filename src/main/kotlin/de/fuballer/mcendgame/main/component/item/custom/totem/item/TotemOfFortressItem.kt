package de.fuballer.mcendgame.main.component.item.custom.totem.item

import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.item.custom.totem.TotemItem
import de.fuballer.mcendgame.main.component.item.custom.totem.TotemType

class TotemOfFortressItem(
    settings: Settings,
) : TotemItem(settings) {
    override val maxTier = 3
    override val type = TotemType.BASIC

    override fun getCustomAttributes(tier: Int) =
        when (tier) {
            0 -> listOf(CustomAttribute(CustomAttributeTypes.MORE_DAMAGE_TAKEN, tier, DoubleRoll(DoubleBounds(0.05))))
            1 -> listOf(CustomAttribute(CustomAttributeTypes.MORE_DAMAGE_TAKEN, tier, DoubleRoll(DoubleBounds(0.1))))
            2 -> listOf(CustomAttribute(CustomAttributeTypes.MORE_DAMAGE_TAKEN, tier, DoubleRoll(DoubleBounds(0.15))))
            else -> listOf()
        }
}