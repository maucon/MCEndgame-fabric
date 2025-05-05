package de.fuballer.mcendgame.main.component.dungeon.device.networking

import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Uuids
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.*

private val PAYLOAD_ID = IdentifierUtil.default("open_dungeon")

data class OpenDungeonPayload(
    val pos: BlockPos,
    val worldKey: RegistryKey<World>,
    val playerId: UUID
) : CustomPayload {
    companion object {
        val ID = CustomPayload.Id<OpenDungeonPayload>(PAYLOAD_ID)

        val CODEC: PacketCodec<RegistryByteBuf, OpenDungeonPayload> = PacketCodec.tuple(
            BlockPos.PACKET_CODEC, OpenDungeonPayload::pos,
            RegistryKey.createPacketCodec(RegistryKeys.WORLD), OpenDungeonPayload::worldKey,
            Uuids.PACKET_CODEC, OpenDungeonPayload::playerId,
            ::OpenDungeonPayload
        )

        val EMPTY = OpenDungeonPayload(BlockPos.ORIGIN, RegistryKey.of(RegistryKeys.WORLD, IdentifierUtil.default("non_existing")), UUID.randomUUID())
    }

    override fun getId(): CustomPayload.Id<out CustomPayload> = ID
}