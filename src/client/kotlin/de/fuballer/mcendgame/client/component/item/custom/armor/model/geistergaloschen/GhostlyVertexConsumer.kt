package de.fuballer.mcendgame.client.component.item.custom.armor.model.geistergaloschen

import de.fuballer.mcendgame.main.util.ColorUtil
import net.minecraft.client.render.VertexConsumer

class GhostlyVertexConsumer(
    private val delegate: VertexConsumer,
) : VertexConsumer {
    companion object {
        private const val TINT_R = 10
        private const val TINT_G = 235
        private const val TINT_B = 200
        private const val TINT_A = 120
        private val TINT_ARGB = ColorUtil.rgbaToInt(TINT_R, TINT_G, TINT_B, TINT_A)
    }

    override fun vertex(x: Float, y: Float, z: Float): VertexConsumer {
        delegate.vertex(x, y, z)
        return this
    }

    override fun color(red: Int, green: Int, blue: Int, alpha: Int): VertexConsumer {
        delegate.color(TINT_R, TINT_G, TINT_B, TINT_A)
        return this
    }

    override fun color(argb: Int): VertexConsumer {
        delegate.color(TINT_ARGB)
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

    override fun lineWidth(width: Float): VertexConsumer? {
        delegate.lineWidth(width)
        return this
    }
}