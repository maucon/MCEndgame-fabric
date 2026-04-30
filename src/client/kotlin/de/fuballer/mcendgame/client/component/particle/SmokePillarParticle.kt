package de.fuballer.mcendgame.client.component.particle

import net.minecraft.client.particle.AbstractSlowingParticle
import net.minecraft.client.particle.Particle
import net.minecraft.client.particle.ParticleFactory
import net.minecraft.client.particle.SpriteProvider
import net.minecraft.client.texture.Sprite
import net.minecraft.client.world.ClientWorld
import net.minecraft.particle.SimpleParticleType
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class SmokePillarParticle(
    clientWorld: ClientWorld,
    x: Double,
    y: Double,
    z: Double,
    velocityX: Double,
    velocityY: Double,
    velocityZ: Double,
    sprite: Sprite,
) : AbstractSlowingParticle(clientWorld, x, y, z, velocityX, velocityY, velocityZ, sprite) {
    private var centerX = x
    private var centerZ = z
    private var angle = 6.28319 * Random.nextDouble()
    private var radius = 0.2 + 0.1 * Random.nextDouble()
    private var yVelocity = 0.2

    init {
        maxAge = 50

        val color = random.nextFloat() * 0.3F
        red = color
        green = color
        blue = color
    }

    override fun tick() {
        super.tick()

        angle += 0.2
        radius += 0.01
        yVelocity -= 0.003

        val offsetX = cos(angle) * radius
        val offsetZ = sin(angle) * radius

        x = centerX + offsetX
        z = centerZ + offsetZ
        velocityY = yVelocity
    }

    override fun getRenderType(): RenderType = RenderType.PARTICLE_ATLAS_OPAQUE

    class Factory(
        private val spriteProvider: SpriteProvider,
    ) : ParticleFactory<SimpleParticleType> {
        override fun createParticle(
            simpleParticleType: SimpleParticleType,
            clientWorld: ClientWorld,
            x: Double,
            y: Double,
            z: Double,
            velocityX: Double,
            velocityY: Double,
            velocityZ: Double,
            random: net.minecraft.util.math.random.Random,
        ): Particle {
            return SmokePillarParticle(clientWorld, x, y, z, velocityX, velocityY, velocityZ, spriteProvider.getSprite(random))
        }
    }
}