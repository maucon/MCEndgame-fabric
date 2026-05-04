package de.fuballer.mcendgame.main.component.dungeon.generation.encounter.encounters.scarred_one.screen

import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.encounters.scarred_one.data.RolledScarredOneEffect
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.network.packet.CustomPayload

private val PAYLOAD_ID = IdentifierUtil.default("scarred_one_effects")

data class ScarredOneEffectsPayload(
    val positiveEffects: List<RolledScarredOneEffect>,
    val negativeEffects: List<RolledScarredOneEffect>,
) : CustomPayload {
    companion object {
        val ID = CustomPayload.Id<ScarredOneEffectsPayload>(PAYLOAD_ID)

        val CODEC: PacketCodec<RegistryByteBuf, ScarredOneEffectsPayload> = PacketCodec.tuple(
            PacketCodecs.collection(::ArrayList, RolledScarredOneEffect.PACKET_CODEC), ScarredOneEffectsPayload::positiveEffects,
            PacketCodecs.collection(::ArrayList, RolledScarredOneEffect.PACKET_CODEC), ScarredOneEffectsPayload::negativeEffects,
            ::ScarredOneEffectsPayload
        )
    }

    override fun getId(): CustomPayload.Id<out CustomPayload> = ID
}