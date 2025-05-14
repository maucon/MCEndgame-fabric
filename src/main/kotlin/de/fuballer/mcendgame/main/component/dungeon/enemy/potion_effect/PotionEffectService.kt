package de.fuballer.mcendgame.main.component.dungeon.enemy.potion_effect

import de.fuballer.mcendgame.main.component.dungeon.enemy.EnemyGenerationSettings
import de.fuballer.mcendgame.main.util.random.RandomUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.entity.mob.MobEntity
import kotlin.random.Random

@Injectable
class PotionEffectService {
    fun addEffects(
        entity: MobEntity,
        level: Int,
        canBeInvisible: Boolean,
        random: Random,
    ) {
        val effects = mutableListOf(
            RandomUtil.pick(EnemyGenerationSettings.STRENGTH_EFFECTS, level, random).option,
            RandomUtil.pick(EnemyGenerationSettings.RESISTANCE_EFFECTS, level, random).option,
            RandomUtil.pick(EnemyGenerationSettings.SPEED_EFFECTS, level, random).option,
            RandomUtil.pick(EnemyGenerationSettings.FIRE_RESISTANCE_EFFECT, level, random).option,
            RandomUtil.pick(EnemyGenerationSettings.ON_DEATH_EFFECTS, random).option,
        )
        if (canBeInvisible) {
            effects.add(RandomUtil.pick(EnemyGenerationSettings.INVISIBILITY_EFFECT, random).option)
        }

        val effectInstances = effects.filterNotNull().map { it.getEffectInstance(false) }
        effectInstances.forEach { entity.addStatusEffect(it) }
    }
}