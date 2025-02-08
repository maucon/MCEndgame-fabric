package de.fuballer.mcendgame.components.entity.custom.swamp_golem

import io.netty.buffer.ByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs

enum class SwampGolemPose {
    IDLING,
    SLAMMING;

    companion object {
        val PACKET_CODEC: PacketCodec<ByteBuf, SwampGolemPose> = PacketCodecs.indexed(
            { index: Int -> entries[index] },
            { value: SwampGolemPose -> value.ordinal })
    }
}