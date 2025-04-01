package de.fuballer.mcendgame.components.entity.custom.entities.webshot

import de.fuballer.mcendgame.components.block.CustomBlocks
import de.fuballer.mcendgame.components.block.DecayingCobwebBlock
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.projectile.ProjectileEntity
import net.minecraft.entity.projectile.ProjectileUtil
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import kotlin.math.cos
import kotlin.math.sin

class WebshotEntity(
    type: EntityType<out WebshotEntity>,
    world: World,
    owner: LivingEntity? = null,
) : ProjectileEntity(type, world) {
    private var createdDecayingCobwebs = false
    private var particleTimer = PARTICLE_COOLDOWN

    companion object {
        const val PARTICLE_COOLDOWN = 1
    }

    init {
        if (owner != null) {
            setOwner(owner)
            setPosition(
                owner.x - (owner.width + 1.0) * 0.5 * sin(owner.bodyYaw * (Math.PI / 180.0)),
                owner.eyeY - 0.1f,
                owner.z + (owner.width + 1.0) * 0.5 * cos(owner.bodyYaw * (Math.PI / 180.0))
            )
        }
    }

    override fun initDataTracker(builder: DataTracker.Builder) {
    }

    override fun getGravity() = 0.06

    override fun tick() {
        super.tick()
        val currentVelocity = velocity

        val hitResult = ProjectileUtil.getCollision(this) { entity: Entity -> canHit(entity) }
        hitOrDeflect(hitResult)

        updateRotation()

        if (world.getStatesInBox(boundingBox).noneMatch { blockState -> blockState.isAir }) {
            discard()
            return
        } else if (this.isInsideWaterOrBubbleColumn) {
            discard()
            return
        }
        velocity = currentVelocity.multiply(0.99)
        applyGravity()
        setPosition(x + currentVelocity.x, y + currentVelocity.y, z + currentVelocity.z)

        spawnParticles()
    }

    private fun spawnParticles() {
        if (!world.isClient) return
        if (particleTimer-- > 0) return
        particleTimer = PARTICLE_COOLDOWN

        world.addParticle(ParticleTypes.CLOUD, getParticleX(0.5), randomBodyY, getParticleZ(0.5), 0.0, 0.0, 0.0)
    }

    override fun onEntityHit(entityHitResult: EntityHitResult) {
        super.onEntityHit(entityHitResult)

        val serverWorld = world as? ServerWorld ?: return
        val attacker = owner as? LivingEntity ?: return
        val entity = entityHitResult.entity

        generateDecayingCobwebs(entity.blockPos)

        val damageSource = damageSources.mobProjectile(this, attacker)
        if (entity.damage(serverWorld, damageSource, 1.0f)) {
            EnchantmentHelper.onTargetDamaged(serverWorld, entity, damageSource)
        }

        discard()
    }

    override fun onBlockHit(blockHitResult: BlockHitResult) {
        super.onBlockHit(blockHitResult)
        if (world.isClient) {
            discard()
            return
        }

        generateDecayingCobwebs(blockHitResult.blockPos)
    }

    private fun generateDecayingCobwebs(blockPos: BlockPos) {
        if (createdDecayingCobwebs) return
        createdDecayingCobwebs = true

        var tryCount = 0
        var successCount = 0
        while (tryCount < 5 || (successCount == 0 && tryCount < 25)) {
            tryCount++

            val pos = blockPos.add(random.nextBetween(-2, 2), random.nextBetween(-2, 2), random.nextBetween(-2, 2))
            if (!isValidCobwebPos(pos)) continue
            successCount++
            world.setBlockState(pos, CustomBlocks.DECAYING_COBWEB.defaultState)
            world.scheduleBlockTick(pos, CustomBlocks.DECAYING_COBWEB, DecayingCobwebBlock.TICK_INTERVAL)
        }
    }

    private fun isValidCobwebPos(blockPos: BlockPos): Boolean {
        val blockState = world.getBlockState(blockPos)
        if (!blockState.isAir) return false

        if (!world.getBlockState(blockPos.up()).isAir) return true
        if (!world.getBlockState(blockPos.down()).isAir) return true
        if (!world.getBlockState(blockPos.north()).isAir) return true
        if (!world.getBlockState(blockPos.east()).isAir) return true
        if (!world.getBlockState(blockPos.south()).isAir) return true
        if (!world.getBlockState(blockPos.west()).isAir) return true

        return false
    }
}