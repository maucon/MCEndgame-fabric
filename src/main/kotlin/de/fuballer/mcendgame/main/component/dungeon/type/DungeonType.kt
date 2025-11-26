package de.fuballer.mcendgame.main.component.dungeon.type

import de.fuballer.mcendgame.main.component.dungeon.enemy.potion_effect.PotionEffect
import de.fuballer.mcendgame.main.component.dungeon.generation.layout.DungeonLayoutType
import de.fuballer.mcendgame.main.component.dungeon.type.data.RolledDungeonType
import de.fuballer.mcendgame.main.component.entity.EntityTypeStats
import de.fuballer.mcendgame.main.component.entity.types.*
import de.fuballer.mcendgame.main.component.entity.types.boss.ArachneBossStats
import de.fuballer.mcendgame.main.component.entity.types.boss.BonecrusherBossStats
import de.fuballer.mcendgame.main.component.entity.types.boss.ElfDuelistBossStats
import de.fuballer.mcendgame.main.util.random.RandomOption
import de.fuballer.mcendgame.main.util.random.RandomUtil
import net.minecraft.entity.LivingEntity
import kotlin.random.Random

enum class DungeonType(
    private val mapTypes: List<RandomOption<DungeonLayoutType>>,
    private val entityTypes: List<RandomOption<EntityTypeStats>>,
    private val bossEntityTypes: List<RandomOption<EntityTypeStats>>,
    val bossCount: Int,
    val applyMisc: (List<LivingEntity>) -> Unit = {},
) {
    STRONGHOLD(
        listOf(
            RandomOption(1, DungeonLayoutType.STRONGHOLD),
        ),
        listOf(
            RandomOption(40, ZombieStats),
            RandomOption(15, HuskStats),
            RandomOption(15, SkeletonStats),
            RandomOption(15, MeleeSkeletonStats),
            RandomOption(8, StrayStats),
            RandomOption(8, BoggedStats),
            RandomOption(3, WitherSkeletonStats),
        ),
        listOf(
            RandomOption(1, ArachneBossStats),
            RandomOption(1, BonecrusherBossStats),
            RandomOption(1, ElfDuelistBossStats),
        ),
        3,
    ),
    NETHER(
        listOf(
            RandomOption(1, DungeonLayoutType.NETHER),
        ),
        listOf(
            RandomOption(25, ZombieStats),
            RandomOption(20, HuskStats),
            RandomOption(15, SkeletonStats),
            RandomOption(8, BoggedStats),
            RandomOption(15, MeleeSkeletonStats),
            RandomOption(5, WitherSkeletonStats),
        ),
        listOf(
            RandomOption(1, ArachneBossStats),
            RandomOption(1, BonecrusherBossStats),
            RandomOption(1, ElfDuelistBossStats),
        ),
        3,
        { enemies -> enemies.forEach { it.addStatusEffect(PotionEffect.FIRE_RESISTANCE.getEffectInstance(false)) } },
    );

    fun roll(random: Random): RolledDungeonType =
        RolledDungeonType(
            RandomUtil.pickOne(mapTypes, random).option,
            entityTypes,
            RandomUtil.pickRepeatIfNeeded(bossEntityTypes, random, bossCount),
            applyMisc,
        )

    fun getEntityTypes() = entityTypes.toMutableList()
}