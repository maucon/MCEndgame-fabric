package de.fuballer.mcendgame.components.dungeon.type

import de.fuballer.mcendgame.components.dungeon.generation.layout.DungeonLayoutType
import de.fuballer.mcendgame.components.dungeon.type.data.RolledDungeonType
import de.fuballer.mcendgame.components.entity.EntityTypeStats
import de.fuballer.mcendgame.components.entity.types.SkeletonStats
import de.fuballer.mcendgame.components.entity.types.ZombieStats
import de.fuballer.mcendgame.components.entity.types.boss.ArachneBossStats
import de.fuballer.mcendgame.util.random.RandomOption
import de.fuballer.mcendgame.util.random.RandomUtil
import kotlin.random.Random

enum class DungeonType(
    private val mapTypes: List<RandomOption<DungeonLayoutType>>,
    private val entityTypes: List<RandomOption<EntityTypeStats>>,
    private val bossEntityTypes: List<RandomOption<EntityTypeStats>>,
    val bossCount: Int,
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
            RandomOption(1, ArachneBossStats),
        ),
        3
    );

    fun roll(random: Random): RolledDungeonType =
        RolledDungeonType(
            RandomUtil.pick(mapTypes, random).option,
            entityTypes,
            RandomUtil.pickAllowRepeatIfNecessary(bossEntityTypes, random, bossCount)
        )
}