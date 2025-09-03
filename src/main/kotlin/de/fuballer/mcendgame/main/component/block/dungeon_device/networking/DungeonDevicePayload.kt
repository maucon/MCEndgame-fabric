package de.fuballer.mcendgame.main.component.block.dungeon_device.networking

import de.fuballer.mcendgame.main.component.dungeon.level.PlayerDungeonLevel
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

data class DungeonDevicePayload(
    val pos: BlockPos,
    val worldKey: RegistryKey<World>,
    val playerId: UUID,
    val playerDungeonLevel: PlayerDungeonLevel,
) : CustomPayload {
    companion object {
        val ID = CustomPayload.Id<DungeonDevicePayload>(PAYLOAD_ID)

        val CODEC: PacketCodec<RegistryByteBuf, DungeonDevicePayload> = PacketCodec.tuple(
            BlockPos.PACKET_CODEC, DungeonDevicePayload::pos,
            RegistryKey.createPacketCodec(RegistryKeys.WORLD), DungeonDevicePayload::worldKey,
            Uuids.PACKET_CODEC, DungeonDevicePayload::playerId,
            PlayerDungeonLevel.PACKET_CODEC, DungeonDevicePayload::playerDungeonLevel,
            ::DungeonDevicePayload
        )

        val EMPTY = DungeonDevicePayload(
            BlockPos.ORIGIN,
            RegistryKey.of(
                RegistryKeys.WORLD,
                IdentifierUtil.default("non_existing")
            ),
            UUID.randomUUID(),
            PlayerDungeonLevel(-1, -1)
        )
    }

    override fun getId(): CustomPayload.Id<out CustomPayload> = ID
}