package de.fuballer.mcendgame.main.component.item.custom.armor.item.wither_rose

import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.HidePlayerModelPartArmor
import net.minecraft.entity.player.PlayerModelPart
import net.minecraft.item.Item

class WitherRoseLeggings(
    settings: Settings,
) : Item(settings), HidePlayerModelPartArmor {
    override val hiddenPlayerModelParts = listOf(
        PlayerModelPart.LEFT_PANTS_LEG,
        PlayerModelPart.RIGHT_PANTS_LEG
    )
}