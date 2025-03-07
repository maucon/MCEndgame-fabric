package de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist

import io.netty.buffer.ByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs

enum class ElfDuelistAttackPose {
    DEFAULT,
    UPWARDS_SLICE_LEFT,
    UPWARDS_SLICE_RIGHT,
    THRUST_LEFT,
    THRUST_RIGHT;

    companion object {
        val PACKET_CODEC: PacketCodec<ByteBuf, ElfDuelistAttackPose> = PacketCodecs.indexed(
            { index: Int -> ElfDuelistAttackPose.entries[index] },
            { value: ElfDuelistAttackPose -> value.ordinal })

        val POSE_SEQUENCES = mapOf(
            DEFAULT to listOf(
                ElfDuelistAttack(DEFAULT, UPWARDS_SLICE_LEFT, 6F, 3, 2),
                ElfDuelistAttack(DEFAULT, UPWARDS_SLICE_RIGHT, 6F, 3, 2),
                ElfDuelistAttack(DEFAULT, THRUST_LEFT, 5F, 4, 3),
                ElfDuelistAttack(DEFAULT, THRUST_RIGHT, 5F, 4, 3),
            ),
            UPWARDS_SLICE_LEFT to listOf(
                ElfDuelistAttack(UPWARDS_SLICE_LEFT, DEFAULT, 6F, 3, 5),
                ElfDuelistAttack(UPWARDS_SLICE_LEFT, THRUST_LEFT, 5F, 4, 3),
                ElfDuelistAttack(UPWARDS_SLICE_LEFT, THRUST_RIGHT, 6F, 4, 4),
            ),
            UPWARDS_SLICE_RIGHT to listOf(
                ElfDuelistAttack(UPWARDS_SLICE_RIGHT, DEFAULT, 6F, 3, 5),
                ElfDuelistAttack(UPWARDS_SLICE_RIGHT, THRUST_RIGHT, 5F, 4, 3),
                ElfDuelistAttack(UPWARDS_SLICE_RIGHT, THRUST_LEFT, 6F, 4, 4),
            ),
            THRUST_LEFT to listOf(
                ElfDuelistAttack(THRUST_LEFT, DEFAULT, 6F, 0, 2),
            ),
            THRUST_RIGHT to listOf(
                ElfDuelistAttack(THRUST_RIGHT, DEFAULT, 6F, 0, 2),
            ),
        )

        fun getAttack(
            oldPose: ElfDuelistAttackPose,
            newPose: ElfDuelistAttackPose,
        ) = POSE_SEQUENCES[oldPose]?.find { it.newPose == newPose }
    }
}