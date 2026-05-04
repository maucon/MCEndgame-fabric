package de.fuballer.mcendgame.main.component.dungeon.generation.encounter.encounters.scarred_one.data

import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.encounters.scarred_one.ScarredOneEffectTargetGroup

data class ScarredOneEffect(
    val attribute: RollableCustomAttribute,
    val targets: ScarredOneEffectTargetGroup,
) {
    fun roll() = RolledScarredOneEffect(attribute.roll(), targets)
}