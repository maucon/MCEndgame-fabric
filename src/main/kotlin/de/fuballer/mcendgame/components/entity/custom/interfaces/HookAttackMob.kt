package de.fuballer.mcendgame.components.entity.custom.interfaces

import de.fuballer.mcendgame.components.entity.custom.networking.EntityHookEntityPayload
import net.fabricmc.fabric.api.networking.v1.PlayerLookup
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.Vec3d
import java.util.*

interface HookAttackMob {
    val hooker: Entity

    val hookPullCount: Int
    val hookPullInterval: Int
    val hookPullStrength: Double
    val hookPullAdditionalY: Double
    val hookedEntityIds: MutableList<Int> // client-side
    val hookedEntityUuidMap: MutableMap<UUID, Pair<Int, Int>> // server-side

    fun shootHookAt(target: LivingEntity)

    fun addHookedEntity(hookedUuid: UUID) {
        if (hookedEntityUuidMap.containsKey(hookedUuid)) {
            hookedEntityUuidMap[hookedUuid] = Pair(0, hookedEntityUuidMap[hookedUuid]?.second ?: 0)
            return
        }
        hookedEntityUuidMap[hookedUuid] = Pair(0, 0)

        val serverWorld = hooker.world as? ServerWorld ?: return

        val hookedEntity = serverWorld.getEntity(hookedUuid) ?: return
        for (player in PlayerLookup.tracking(serverWorld, hookedEntity.blockPos)) {
            ServerPlayNetworking.send(player, EntityHookEntityPayload(hooker.id, hookedEntity.id, false))
        }
    }

    fun removeHookedEntity(hookedUuid: UUID) {
        hookedEntityUuidMap.remove(hookedUuid)

        val serverWorld = hooker.world as? ServerWorld ?: return
        val hookedEntity = serverWorld.getEntity(hookedUuid) ?: return

        for (player in PlayerLookup.tracking(serverWorld, hookedEntity.blockPos)) {
            ServerPlayNetworking.send(player, EntityHookEntityPayload(hooker.id, hookedEntity.id, true))
        }
    }

    fun addHookedEntity(hookedId: Int) {
        hookedEntityIds.add(hookedId)
    }

    fun removeHookedEntity(hookedId: Int) {
        hookedEntityIds.remove(hookedId)
    }

    fun tickHooks() {
        val world = hooker.world as? ServerWorld ?: return
        updateHookedEntities(world)
    }

    fun updateHookedEntities(
        world: ServerWorld
    ) {
        val toRemove = mutableListOf<UUID>()

        for (hookedUuid in hookedEntityUuidMap.keys) {
            val hookedEntity = world.getEntity(hookedUuid)
            if (hookedEntity == null || !hookedEntity.isAlive) {
                toRemove.add(hookedUuid)
                continue
            }

            var (pullCount, pullCooldown) = hookedEntityUuidMap[hookedUuid] ?: Pair(0, 0)
            if (++pullCooldown >= hookPullInterval) {
                pullCooldown = 0

                if (pullCount++ > hookPullCount) {
                    toRemove.add(hookedUuid)
                    continue
                }

                pullHookedEntity(hookedEntity)
            }

            hookedEntityUuidMap[hookedUuid] = Pair(pullCount, pullCooldown)
        }

        for (entityUuid in toRemove) {
            removeHookedEntity(entityUuid)
        }
    }

    fun pullHookedEntity(
        hooked: Entity
    ) {
        val direction = Vec3d(hooker.x - hooked.x, hooker.y - hooked.y, hooker.z - hooked.z)
        val normalizedDirection = direction.normalize()
        val velocity = normalizedDirection.multiply(hookPullStrength)
        hooked.setVelocity(velocity.x, velocity.y + hookPullAdditionalY, velocity.z)
        if (hooked is PlayerEntity) hooked.velocityModified = true
    }
}