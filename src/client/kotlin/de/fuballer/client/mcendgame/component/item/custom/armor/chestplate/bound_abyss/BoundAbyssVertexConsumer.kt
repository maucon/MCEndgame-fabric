package de.fuballer.client.mcendgame.component.item.custom.armor.chestplate.bound_abyss

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