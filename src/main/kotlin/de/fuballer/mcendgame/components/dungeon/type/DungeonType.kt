package de.fuballer.mcendgame.components.dungeon.type

import de.fuballer.mcendgame.components.dungeon.generation.layout.DungeonLayoutType
import de.fuballer.mcendgame.components.dungeon.type.data.RolledDungeonType
import de.fuballer.mcendgame.components.entity.EntityTypeStats
import de.fuballer.mcendgame.components.entity.types.ArachneStats
import de.fuballer.mcendgame.components.entity.types.SkeletonStats
import de.fuballer.mcendgame.components.entity.types.ZombieStats
import de.fuballer.mcendgame.util.random.RandomOption
import de.fuballer.mcendgame.util.random.RandomUtil
import kotlin.random.Random

enum class DungeonType(
    private val mapTypes: List<RandomOption<DungeonLayoutType>>,
    private val entityTypes: List<RandomOption<EntityTypeStats>>,
    private val bossEntityTypes: List<RandomOption<EntityTypeStats>>
) {
    STRONGHOLD(
        listOf(
            RandomOption(1, DungeonLayoutType.STRONGHOLD),
        ),
        listOf(
            RandomOption(40, ZombieStats),
            RandomOption(15, SkeletonStats),
        ),
        listOf(
            RandomOption(1, ArachneStats),
            RandomOption(1, ArachneStats),
            RandomOption(1, ArachneStats),
        )
    );

    fun roll(random: Random, bossCount: Int): RolledDungeonType {
        require(bossEntityTypes.size >= bossCount) { "DungeonType: '${this.name}' has less than $bossCount bosses" }

        return RolledDungeonType(
            RandomUtil.pick(mapTypes, random).option,
            entityTypes,
            RandomUtil.pick(bossEntityTypes, random, bossCount)
        )
    }
}