package de.fuballer.mcendgame.main.component.dungeon.level

import net.minecraft.nbt.NbtCompound
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs

private const val DUNGEON_LEVEL_NBT = "PlayerDungeonLevel"
private const val DUNGEON_LEVEL_LEVEL_NBT = "Level"
private const val DUNGEON_LEVEL_PROGRESS_NBT = "Progress"

data class PlayerDungeonLevel(
    var level: Int = 1,
    var levelProgress: Int = 0,
) {
    companion object {
        val PACKET_CODEC: PacketCodec<RegistryByteBuf, PlayerDungeonLevel> =
            PacketCodec.tuple(
                PacketCodecs.VAR_INT, PlayerDungeonLevel::level,
                PacketCodecs.VAR_INT, PlayerDungeonLevel::levelProgress,
                ::PlayerDungeonLevel
            )

        fun write(dungeonLevel: PlayerDungeonLevel, nbt: NbtCompound) {
            val container = NbtCompound()

            container.putInt(DUNGEON_LEVEL_LEVEL_NBT, dungeonLevel.level)
            container.putInt(DUNGEON_LEVEL_PROGRESS_NBT, dungeonLevel.levelProgress)

            nbt.put(DUNGEON_LEVEL_NBT, container)
        }

        fun read(nbt: NbtCompound): PlayerDungeonLevel {
            val container = nbt.getCompound(DUNGEON_LEVEL_NBT)
                .takeIf { it.isPresent }
                ?.get() ?: return PlayerDungeonLevel()

            val level = container.getInt(DUNGEON_LEVEL_LEVEL_NBT)
                .takeIf { it.isPresent }
                ?.get() ?: 1

            val levelProgress = container.getInt(DUNGEON_LEVEL_PROGRESS_NBT)
                .takeIf { it.isPresent }
                ?.get() ?: 0

            return PlayerDungeonLevel(level, levelProgress)
        }
    }
}