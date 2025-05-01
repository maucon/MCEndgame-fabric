package de.fuballer.mcendgame.components.entity.custom.entities.webshot

import de.fuballer.mcendgame.components.block.CustomBlocks
import de.fuballer.mcendgame.components.block.DecayingCobwebBlock
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.item.ItemStack
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
) : PersistentProjectileEntity(type, world) {
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

    override fun getGravity() = 0.06

    override fun tick() {
        super.tick()
        spawnParticles()
    }

    private fun spawnParticles() {
        if (!world.isClient) return
        if (particleTimer-- > 0) return
        particleTimer = PARTICLE_COOLDOWN

        world.addParticleClient(ParticleTypes.CLOUD, getParticleX(0.5), randomBodyY, getParticleZ(0.5), 0.0, 0.0, 0.0)
    }

    override fun onEntityHit(entityHitResult: EntityHitResult) {
        val serverWorld = world as? ServerWorld ?: return
        val attacker = owner as? LivingEntity ?: return
        val entity = entityHitResult.entity

        generateDecayingCobwebs(entity.blockPos)

        val damageSource = damageSources.mobProjectile(this, attacker)
        if (entity.damage(serverWorld, damageSource, 1.0f)) {
            EnchantmentHelper.onTargetDamaged(serverWorld, entity, damageSource)
        }

        if (world.isClient) return
        discard()
    }

    override fun onBlockHit(blockHitResult: BlockHitResult) {
        val blockState = world.getBlockState(blockHitResult.blockPos)
        blockState.onProjectileHit(world, blockState, blockHitResult, this)

        if (world.isClient) return
        discard()

        generateDecayingCobwebs(blockHitResult.blockPos)
    }

    override fun getDefaultItemStack() = ItemStack(CustomBlocks.DECAYING_COBWEB)

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