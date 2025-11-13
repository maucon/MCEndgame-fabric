package de.fuballer.mcendgame.main.component.entity.custom.interfaces

import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import io.netty.buffer.ByteBuf
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricTrackedDataRegistry
import net.minecraft.entity.data.TrackedDataHandler
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs

interface CustomPosesEntity {
    fun setPose(pose: CustomPose)

    companion object {
        val CUSTOM_POSE_TDH: TrackedDataHandler<CustomPose> = TrackedDataHandler.create(CustomPose.PACKET_CODEC)
            .also { FabricTrackedDataRegistry.register(IdentifierUtil.default("custom_pose_tracked_data"),it) }
    }

    enum class CustomPose {
        IDLING,
        WALKING,
        WALKING_BW,
        WALKING_RIGHT,
        WALKING_LEFT,
        SLAMMING,
        MELEE_ATTACKING,
        SPITTING;

        companion object {
            val PACKET_CODEC: PacketCodec<ByteBuf, CustomPose> = PacketCodecs.indexed(
                { index: Int -> entries[index] },
                { value: CustomPose -> value.ordinal })
        }
    }
}