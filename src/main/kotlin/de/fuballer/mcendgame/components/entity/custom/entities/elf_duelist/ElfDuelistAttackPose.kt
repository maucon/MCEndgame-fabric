package de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist

import io.netty.buffer.ByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs

enum class ElfDuelistAttackPose {
    DEFAULT,
    UPWARDS_SLICE_LEFT,
    UPWARDS_SLICE_RIGHT;

    companion object {
        val PACKET_CODEC: PacketCodec<ByteBuf, ElfDuelistAttackPose> = PacketCodecs.indexed(
            { index: Int -> ElfDuelistAttackPose.entries[index] },
            { value: ElfDuelistAttackPose -> value.ordinal })

        val POSE_SEQUENCES = mapOf(
            DEFAULT to listOf(
                ElfDuelistAttack(DEFAULT, UPWARDS_SLICE_LEFT, 6F, 3, 2),
                ElfDuelistAttack(DEFAULT, UPWARDS_SLICE_RIGHT, 6F, 3, 2),
            ),
            UPWARDS_SLICE_LEFT to listOf(
                ElfDuelistAttack(UPWARDS_SLICE_LEFT, DEFAULT, 6F, 3, 5),
            ),
            UPWARDS_SLICE_RIGHT to listOf(
                ElfDuelistAttack(UPWARDS_SLICE_RIGHT, DEFAULT, 6F, 3, 5),
            ),
        )

        fun getAttack(
            oldPose: ElfDuelistAttackPose,
            newPose: ElfDuelistAttackPose,
        ) = POSE_SEQUENCES[oldPose]?.find { it.newPose == newPose }
    }
}