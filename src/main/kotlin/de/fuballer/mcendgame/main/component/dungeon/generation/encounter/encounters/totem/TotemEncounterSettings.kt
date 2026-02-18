package de.fuballer.mcendgame.main.component.dungeon.generation.encounter.encounters.totem

import de.fuballer.mcendgame.main.component.item.custom.totem.TotemItems
import de.fuballer.mcendgame.main.util.random.RandomOption
import de.fuballer.mcendgame.main.util.random.RandomUtil
import net.minecraft.item.ItemStack
import kotlin.random.Random

object TotemEncounterSettings {
    const val BASE_PROBABILITY = 0.15 //per dungeon

    fun getEnemyCount(level: Int): Int {
        val random = Random.nextDouble(1 + level * 0.1)
        val additional = random.toInt() + if (Random.nextDouble() < random % 1) 1 else 0
        return 3 + additional
    }

    fun getTotemReward(dungeonLevel: Int): ItemStack = RandomUtil.pickOne(TOTEM_OPTIONS.filter { it.value <= dungeonLevel }.keys.toList()).option.copy()

    val TOTEM_OPTIONS = mapOf(
        // basic
        RandomOption(150, TotemItems.TOTEM_OF_BASTION.getStack(0)) to 0,
        RandomOption(50, TotemItems.TOTEM_OF_BASTION.getStack(1)) to 5,
        RandomOption(10, TotemItems.TOTEM_OF_BASTION.getStack(2)) to 10,
        RandomOption(150, TotemItems.TOTEM_OF_FORCE.getStack(0)) to 0,
        RandomOption(50, TotemItems.TOTEM_OF_FORCE.getStack(1)) to 5,
        RandomOption(10, TotemItems.TOTEM_OF_FORCE.getStack(2)) to 10,
        RandomOption(150, TotemItems.TOTEM_OF_FORTRESS.getStack(0)) to 0,
        RandomOption(50, TotemItems.TOTEM_OF_FORTRESS.getStack(1)) to 5,
        RandomOption(10, TotemItems.TOTEM_OF_FORTRESS.getStack(2)) to 10,
        RandomOption(150, TotemItems.TOTEM_OF_FRENZY.getStack(0)) to 0,
        RandomOption(50, TotemItems.TOTEM_OF_FRENZY.getStack(1)) to 5,
        RandomOption(10, TotemItems.TOTEM_OF_FRENZY.getStack(2)) to 10,
        RandomOption(150, TotemItems.TOTEM_OF_GRACE.getStack(0)) to 0,
        RandomOption(50, TotemItems.TOTEM_OF_GRACE.getStack(1)) to 5,
        RandomOption(10, TotemItems.TOTEM_OF_GRACE.getStack(2)) to 10,
        RandomOption(150, TotemItems.TOTEM_OF_IMPACT.getStack(0)) to 0,
        RandomOption(50, TotemItems.TOTEM_OF_IMPACT.getStack(1)) to 5,
        RandomOption(10, TotemItems.TOTEM_OF_IMPACT.getStack(2)) to 10,
        RandomOption(150, TotemItems.TOTEM_OF_SWIFTNESS.getStack(0)) to 0,
        RandomOption(50, TotemItems.TOTEM_OF_SWIFTNESS.getStack(1)) to 5,
        RandomOption(10, TotemItems.TOTEM_OF_SWIFTNESS.getStack(2)) to 10,
        RandomOption(150, TotemItems.TOTEM_OF_THICKNESS.getStack(0)) to 0,
        RandomOption(50, TotemItems.TOTEM_OF_THICKNESS.getStack(1)) to 5,
        RandomOption(10, TotemItems.TOTEM_OF_THICKNESS.getStack(2)) to 10,
        RandomOption(150, TotemItems.TOTEM_OF_VANGUARD.getStack(0)) to 0,
        RandomOption(50, TotemItems.TOTEM_OF_VANGUARD.getStack(1)) to 5,
        RandomOption(10, TotemItems.TOTEM_OF_VANGUARD.getStack(2)) to 10,

        // effect
        RandomOption(100, TotemItems.TOTEM_OF_DEFIANCE.getStack(0)) to 6,
        RandomOption(33, TotemItems.TOTEM_OF_DEFIANCE.getStack(1)) to 10,
        RandomOption(7, TotemItems.TOTEM_OF_DEFIANCE.getStack(2)) to 14,
        RandomOption(100, TotemItems.TOTEM_OF_RECOVERY.getStack(0)) to 6,
        RandomOption(33, TotemItems.TOTEM_OF_RECOVERY.getStack(1)) to 10,
        RandomOption(7, TotemItems.TOTEM_OF_RECOVERY.getStack(2)) to 14,

        // ultimate
        RandomOption(35, TotemItems.TOTEM_OF_VOLLEY.getStack(0)) to 8,
        RandomOption(10, TotemItems.TOTEM_OF_VOLLEY.getStack(1)) to 12,
        RandomOption(2, TotemItems.TOTEM_OF_VOLLEY.getStack(2)) to 16,
    )
}