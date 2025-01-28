package de.fuballer.mcendgame.components.dungeon.type

import de.fuballer.mcendgame.components.dungeon.generation.layout.DungeonLayoutType
import de.fuballer.mcendgame.components.dungeon.type.data.RolledDungeonType
import de.fuballer.mcendgame.util.random.RandomOption
import de.fuballer.mcendgame.util.random.RandomUtil
import kotlin.random.Random

enum class DungeonType(
    private val mapTypes: List<RandomOption<DungeonLayoutType>>,
//    private val entityTypes: List<RandomOption<EntityTypeStats>>,
//    private val bossEntityTypes: List<RandomOption<EntityTypeStats>>
) {
    STRONGHOLD(
        listOf(
            RandomOption(1, DungeonLayoutType.STRONGHOLD),
        ),
//        listOf(
//            RandomOption(40, ZombieTypeStats),
//            RandomOption(20, HuskTypeStats),
//            RandomOption(15, SkeletonTypeStats),
//        ),
//        listOf(
//            RandomOption(1, ZombieTypeStats),
//            RandomOption(1, HuskTypeStats),
//            RandomOption(1, SkeletonTypeStats),
//        )
    );

    fun roll(random: Random): RolledDungeonType {
//        require(bossEntityTypes.size >= 3) { "DungeonType: '${this.name}' has less than 3 bosses" }

        return RolledDungeonType(
            RandomUtil.pick(mapTypes, random).option,
//            entityTypes,
//            RandomUtil.pick(bossEntityTypes, random, 3)
        )
    }
}