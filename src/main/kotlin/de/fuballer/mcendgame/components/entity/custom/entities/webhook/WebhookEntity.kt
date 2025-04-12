package de.fuballer.mcendgame.components.entity.custom.entities.webhook

import de.fuballer.mcendgame.components.block.CustomBlocks
import de.fuballer.mcendgame.components.entity.custom.interfaces.HookAttackMob
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.world.World
import kotlin.math.cos
import kotlin.math.sin

class WebhookEntity(
    type: EntityType<out WebhookEntity>,
    world: World,
    owner: LivingEntity? = null,
) : PersistentProjectileEntity(type, world) {
    init {
        if (owner != null) {
            this.owner = owner
            setPosition(
                owner.x - (owner.width + 1.0) * 0.5 * sin(owner.bodyYaw * (Math.PI / 180.0)),
                owner.eyeY - 0.1f,
                owner.z + (owner.width + 1.0) * 0.5 * cos(owner.bodyYaw * (Math.PI / 180.0))
            )
        }
    }

    override fun getGravity() = 0.06

    override fun onEntityHit(entityHitResult: EntityHitResult) {
        val serverWorld = world as? ServerWorld ?: return
        val attacker = owner as? LivingEntity ?: return
        val entity = entityHitResult.entity

        val damageSource = damageSources.mobProjectile(this, attacker)
        if (entity.damage(serverWorld, damageSource, 1.0f)) {
            EnchantmentHelper.onTargetDamaged(serverWorld, entity, damageSource)
        }

        discard()

        val hooker = owner as? HookAttackMob ?: return
        hooker.addHookedEntity(entity.uuid)
    }

    override fun onBlockHit(blockHitResult: BlockHitResult) {
        val blockState = world.getBlockState(blockHitResult.blockPos)
        blockState.onProjectileHit(world, blockState, blockHitResult, this)

        if (world.isClient) return
        discard()
    }

    override fun getDefaultItemStack() = ItemStack(CustomBlocks.DECAYING_COBWEB)
}