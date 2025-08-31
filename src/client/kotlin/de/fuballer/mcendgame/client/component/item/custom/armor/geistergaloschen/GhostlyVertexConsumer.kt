package de.fuballer.mcendgame.client.component.item.custom.armor.geistergaloschen

import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.VertexConsumerProvider

class GhostlyVertexConsumer(
    private val delegate: VertexConsumer,
) : VertexConsumer {
    companion object {
        const val TINT_R = 0
        const val TINT_G = 255
        const val TINT_B = 0
        const val TINT_A = 120

        fun convertToGhostlyVertexConsumerProvider(original: VertexConsumerProvider) =
            VertexConsumerProvider { layer -> GhostlyVertexConsumer(original.getBuffer(layer)) }
    }

    override fun vertex(x: Float, y: Float, z: Float): VertexConsumer {
        delegate.vertex(x, y, z)
        return this
    }

    override fun color(red: Int, green: Int, blue: Int, alpha: Int): VertexConsumer {
        delegate.color(TINT_R, TINT_G, TINT_B, TINT_A)
        return this
    }

    override fun texture(u: Float, v: Float): VertexConsumer {
        delegate.texture(u, v)
        return this
    }

    override fun overlay(u: Int, v: Int): VertexConsumer {
        delegate.overlay(u, v)
        return this
    }

    override fun light(u: Int, v: Int): VertexConsumer {
        delegate.light(u, v)
        return this
    }

    override fun normal(x: Float, y: Float, z: Float): VertexConsumer {
        delegate.normal(x, y, z)
        return this
    }
}