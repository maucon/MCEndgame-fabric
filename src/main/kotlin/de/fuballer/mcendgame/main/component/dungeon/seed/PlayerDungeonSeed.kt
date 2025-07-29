package de.fuballer.mcendgame.main.component.dungeon.seed

import de.fuballer.mcendgame.main.component.dungeon.type.DungeonType
import net.minecraft.nbt.NbtCompound

private const val DUNGEON_SEED_NBT = "PlayerDungeonSeed"
private const val DUNGEON_SEED_SEED_NBT = "Seed"
private const val DUNGEON_SEED_TYPE_NBT = "Type"

data class PlayerDungeonSeed(
    var seed: Long,
    var type: DungeonType
) {
    companion object {
        fun write(dungeonSeed: PlayerDungeonSeed, nbt: NbtCompound) {
            val container = NbtCompound()

            container.putLong(DUNGEON_SEED_SEED_NBT, dungeonSeed.seed)
            container.putString(DUNGEON_SEED_TYPE_NBT, dungeonSeed.type.name)

            nbt.put(DUNGEON_SEED_NBT, container)
        }

        fun read(nbt: NbtCompound): PlayerDungeonSeed? {
            val container = nbt.getCompound(DUNGEON_SEED_NBT)
                .takeIf { it.isPresent }
                ?.get() ?: return null

            val seed = container.getLong(DUNGEON_SEED_SEED_NBT)
                .takeIf { it.isPresent }
                ?.get() ?: return null

            val type = container.getString(DUNGEON_SEED_TYPE_NBT)
                .map {
                    try {
                        DungeonType.valueOf(it)
                    } catch (_: IllegalArgumentException) {
                        null
                    }
                }
                .takeIf { it.isPresent }
                ?.get() ?: return null

            return PlayerDungeonSeed(seed, type)
        }
    }
}