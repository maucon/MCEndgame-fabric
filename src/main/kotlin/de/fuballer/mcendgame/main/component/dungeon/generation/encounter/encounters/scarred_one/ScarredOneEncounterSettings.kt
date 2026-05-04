package de.fuballer.mcendgame.main.component.dungeon.generation.encounter.encounters.scarred_one

import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.custom_attribute.types.VanillaAttributeTypes
import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.encounters.scarred_one.data.ScarredOneEffect
import de.fuballer.mcendgame.main.util.random.RandomOption
import de.fuballer.mcendgame.main.util.random.RandomUtil
import kotlin.random.Random

object ScarredOneEncounterSettings {
    const val BASE_PROBABILITY = 1.0 // per dungeon

    fun getPositiveEffects(count: Int = 1) = RandomUtil.pickRepeatIfNeeded(POSITIVE, Random.Default, count).map { it.roll() }
    fun getNegativeEffects(count: Int = 1) = RandomUtil.pickRepeatIfNeeded(NEGATIVE, Random.Default, count).map { it.roll() }

    val POSITIVE = listOf(
        RandomOption(5, ScarredOneEffect(RollableCustomAttribute(CustomAttributeTypes.MORE_DAMAGE_TAKEN, 0, DoubleBounds(-0.15, -0.1)), ScarredOneEffectTargetGroup.ALLIES)),
        RandomOption(5, ScarredOneEffect(RollableCustomAttribute(VanillaAttributeTypes.INCREASED_SCALE, 0, DoubleBounds(0.05, 0.1)), ScarredOneEffectTargetGroup.ALLIES)),
    )

    val NEGATIVE = listOf(
        RandomOption(5, ScarredOneEffect(RollableCustomAttribute(CustomAttributeTypes.MORE_DAMAGE_TAKEN, 0, DoubleBounds(-0.1, -0.05)), ScarredOneEffectTargetGroup.ENEMIES)),
        RandomOption(5, ScarredOneEffect(RollableCustomAttribute(VanillaAttributeTypes.MORE_SCALE, 0, DoubleBounds(-0.4, -0.25)), ScarredOneEffectTargetGroup.ENEMIES)),
    )
}