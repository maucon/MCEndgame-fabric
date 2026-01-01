package de.fuballer.mcendgame.main.messaging.misc

import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttribute
import net.minecraft.text.Text

data class GetCustomAttributesTextsCommand(
    val attributes: List<CustomAttribute>,
    val detailed: Boolean = false,
    var texts: List<Text> = listOf(),
)