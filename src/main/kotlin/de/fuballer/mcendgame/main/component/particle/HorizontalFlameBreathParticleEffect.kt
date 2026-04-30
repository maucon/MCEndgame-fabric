package de.fuballer.mcendgame.main.component.particle

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.particle.ParticleEffect
import net.minecraft.particle.ParticleType
import java.util.function.Function

class HorizontalFlameBreathParticleEffect(
    val directionX: Double,
    val directionY: Double,
    val directionZ: Double,
    val spreadAngle: Double
) : ParticleEffect {
    companion object {
        val CODEC: MapCodec<HorizontalFlameBreathParticleEffect> = RecordCodecBuilder.mapCodec(
            Function { instance ->
                instance.group(
                    Codec.DOUBLE.fieldOf("direction_x").forGetter(Function { particleEffect -> particleEffect.directionX }),
                    Codec.DOUBLE.fieldOf("direction_y").forGetter(Function { particleEffect -> particleEffect.directionY }),
                    Codec.DOUBLE.fieldOf("direction_z").forGetter(Function { particleEffect -> particleEffect.directionZ }),
                    Codec.DOUBLE.fieldOf("spread_angle").forGetter(Function { particleEffect -> particleEffect.spreadAngle }),
                ).apply(instance, ::HorizontalFlameBreathParticleEffect)
            }
        )

        val PACKET_CODEC: PacketCodec<RegistryByteBuf, HorizontalFlameBreathParticleEffect> =
            PacketCodec.tuple(
                PacketCodecs.DOUBLE, { it.directionX },
                PacketCodecs.DOUBLE, { it.directionY },
                PacketCodecs.DOUBLE, { it.directionZ },
                PacketCodecs.DOUBLE, { it.spreadAngle },
                ::HorizontalFlameBreathParticleEffect
            )
    }

    override fun getType(): ParticleType<*> = CustomParticleTypes.HORIZONTAL_FLAME_BREATH
}