package de.fuballer.mcendgame.components.entity.custom.entities.webshot

import net.minecraft.block.Blocks
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.entity.projectile.ProjectileUtil
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
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
    var createdCobwebs = false

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
        super.initDataTracker(builder)
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
        } else if (this.isInsideWaterOrBubbleColumn) {
            discard()
        } else {
            velocity = currentVelocity.multiply(0.99)
            applyGravity()
            setPosition(x + currentVelocity.x, y + currentVelocity.y, z + currentVelocity.z)
        }
    }

    override fun onEntityHit(entityHitResult: EntityHitResult) {
        super.onEntityHit(entityHitResult)

        val serverWorld = world as? ServerWorld ?: return
        val attacker = owner as? LivingEntity ?: return
        val entity = entityHitResult.entity

        generateCobwebs(entity.blockPos)

        val damageSource = damageSources.mobProjectile(this, attacker)
        if (entity.damage(serverWorld, damageSource, 1.0f)) {
            EnchantmentHelper.onTargetDamaged(serverWorld, entity, damageSource)
        }
    }

    override fun onBlockHit(blockHitResult: BlockHitResult) {
        super.onBlockHit(blockHitResult)
        if (world.isClient) {
            discard()
            return
        }

        generateCobwebs(blockHitResult.blockPos)
    }

    private fun generateCobwebs(blockPos: BlockPos) {
        if (createdCobwebs) return
        createdCobwebs = true

        var tryCount = 0
        var successCount = 0
        while (tryCount < 5 || (successCount == 0 && tryCount < 25)) {
            tryCount++

            val pos = blockPos.add(random.nextBetween(-2, 2), random.nextBetween(-2, 2), random.nextBetween(-2, 2))
            if (!isValidCobwebPos(pos)) continue
            successCount++
            world.setBlockState(pos, Blocks.COBWEB.defaultState)
        }
    }

    private fun isValidCobwebPos(blockPos: BlockPos): Boolean {
        val blockState = world.getBlockState(blockPos)
        if (!blockState.isAir) return false

        for (x in -1..1) {
            for (y in -1..1) {
                for (z in -1..1) {
                    if (x == 0 && y == 0 && z == 0) continue
                    val adjacentPos = blockPos.add(x, y, z)
                    if (!world.getBlockState(adjacentPos).isAir) return true
                }
            }
        }

        return false
    }

    override fun getDefaultItemStack() = ItemStack(Items.COBWEB)
}