package de.fuballer.mcendgame.client.component.item.custom.armor.model.bound_abyss

import de.fuballer.mcendgame.main.util.ColorUtil
import net.minecraft.client.render.VertexConsumer
import kotlin.math.max

class BoundAbyssVertexConsumer(
    private val delegate: VertexConsumer,
    private val effectStrength: Double,
) : VertexConsumer {
    override fun vertex(x: Float, y: Float, z: Float): VertexConsumer {
        delegate.vertex(x, y, z)
        return this
    }

    override fun color(red: Int, green: Int, blue: Int, alpha: Int): VertexConsumer {
        val decrease = (effectStrength * 100).toInt()
        delegate.color(red, max(green - decrease, 0), max(blue - decrease, 0), alpha)

        return this
    }

    override fun color(argb: Int): VertexConsumer {
        val a = (argb shr 24) and 0xFF
        val r = (argb shr 16) and 0xFF
        val g = (argb shr 8) and 0xFF
        val b = argb and 0xFF

        val decrease = (effectStrength * 100).toInt()

        val color = ColorUtil.rgbaToInt(r, max(g - decrease, 0), max(b - decrease, 0), a)
        delegate.color(color)

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

    override fun lineWidth(width: Float): VertexConsumer {
        delegate.lineWidth(width)
        return this
    }
}