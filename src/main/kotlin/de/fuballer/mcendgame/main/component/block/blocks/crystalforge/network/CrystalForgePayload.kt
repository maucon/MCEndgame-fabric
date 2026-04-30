package de.fuballer.mcendgame.main.component.block.blocks.crystalforge.network

import de.fuballer.mcendgame.main.component.block.blocks.crystalforge.CrystalForgeBlock
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload

class CrystalForgePayload : CustomPayload {
    companion object {
        val IDENTIFIER = IdentifierUtil.default("${CrystalForgeBlock.ID}.forge")
        val ID = CustomPayload.Id<CrystalForgePayload>(IDENTIFIER)

        val CODEC: PacketCodec<RegistryByteBuf, CrystalForgePayload> = PacketCodec.of({ _, _ -> }, { _ -> CrystalForgePayload() })
    }

    override fun getId() = ID
}