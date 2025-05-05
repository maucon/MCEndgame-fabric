package de.fuballer.mcendgame.main.component.dungeon.type

import de.fuballer.mcendgame.main.component.dungeon.generation.layout.DungeonLayoutType
import de.fuballer.mcendgame.main.component.dungeon.type.data.RolledDungeonType
import de.fuballer.mcendgame.main.component.entity.EntityTypeStats
import de.fuballer.mcendgame.main.component.entity.types.PiglinStats
import de.fuballer.mcendgame.main.component.entity.types.SkeletonStats
import de.fuballer.mcendgame.main.component.entity.types.WitherSkeletonStats
import de.fuballer.mcendgame.main.component.entity.types.ZombieStats
import de.fuballer.mcendgame.main.component.entity.types.boss.ArachneBossStats
import de.fuballer.mcendgame.main.component.entity.types.boss.BonecrusherBossStats
import de.fuballer.mcendgame.main.component.entity.types.boss.ElfDuelistBossStats
import de.fuballer.mcendgame.main.util.random.RandomOption
import de.fuballer.mcendgame.main.util.random.RandomUtil
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
            RandomOption(1, BonecrusherBossStats),
            RandomOption(1, ElfDuelistBossStats),
        ),
        3
    ),
    NETHER(
        listOf(
            RandomOption(1, DungeonLayoutType.NETHER),
        ),
        listOf(
            RandomOption(40, PiglinStats),
            RandomOption(15, SkeletonStats),
            RandomOption(5, WitherSkeletonStats),
        ),
        listOf(
            RandomOption(1, ArachneBossStats),
            RandomOption(1, BonecrusherBossStats),
            RandomOption(1, ElfDuelistBossStats),
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