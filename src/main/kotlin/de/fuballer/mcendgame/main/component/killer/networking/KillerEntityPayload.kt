package de.fuballer.mcendgame.main.component.killer.networking

import de.fuballer.mcendgame.main.component.killer.db.KillerEntity
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload

private val PAYLOAD_ID = IdentifierUtil.default("killer_entity")

data class KillerEntityPayload(
    val killerEntity: KillerEntity,
) : CustomPayload {
    companion object {
        val ID = CustomPayload.Id<KillerEntityPayload>(PAYLOAD_ID)

        val CODEC: PacketCodec<RegistryByteBuf, KillerEntityPayload> = PacketCodec.tuple(
            KillerEntity.PACKET_CODEC, KillerEntityPayload::killerEntity,
            ::KillerEntityPayload
        )
    }

    override fun getId(): CustomPayload.Id<out CustomPayload> = ID
}