package de.fuballer.mcendgame.main.component.dungeon.level

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.storage.ReadView
import net.minecraft.storage.WriteView

private const val DUNGEON_LEVEL_NBT = "PlayerDungeonLevel"

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

        val CODEC: Codec<PlayerDungeonLevel> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.INT.fieldOf("level").forGetter(PlayerDungeonLevel::level),
                Codec.INT.fieldOf("level_progress").forGetter(PlayerDungeonLevel::levelProgress),
            ).apply(instance, ::PlayerDungeonLevel)
        }


        fun write(dungeonLevel: PlayerDungeonLevel, view: WriteView) {
            view.put(DUNGEON_LEVEL_NBT, CODEC, dungeonLevel)
        }

        fun read(view: ReadView): PlayerDungeonLevel = view.read<PlayerDungeonLevel>(DUNGEON_LEVEL_NBT, CODEC).orElseGet { PlayerDungeonLevel() }
    }
}