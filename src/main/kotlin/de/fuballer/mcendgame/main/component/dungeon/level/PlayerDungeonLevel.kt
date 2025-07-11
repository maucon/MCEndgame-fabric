package de.fuballer.mcendgame.main.component.dungeon.level

import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs

data class PlayerDungeonLevel(
    var level: Int,
    var levelProgress: Int,
) {
    companion object {
        val PACKET_CODEC: PacketCodec<RegistryByteBuf, PlayerDungeonLevel> =
            PacketCodec.tuple(
                PacketCodecs.VAR_INT, PlayerDungeonLevel::level,
                PacketCodecs.VAR_INT, PlayerDungeonLevel::levelProgress,
                ::PlayerDungeonLevel
            )
    }
}
