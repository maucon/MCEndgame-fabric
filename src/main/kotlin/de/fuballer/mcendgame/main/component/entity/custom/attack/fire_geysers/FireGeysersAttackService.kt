package de.fuballer.mcendgame.main.component.entity.custom.attack.fire_geysers

import de.fuballer.mcendgame.main.component.damage.dealing.DamageDealingService.dealGenericAttackDamage
import de.fuballer.mcendgame.main.component.particle.CustomParticleTypes
import de.fuballer.mcendgame.main.functional.scheduler.Scheduler
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.particle.BlockStateParticleEffect
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.world.World
import kotlin.math.min

@Injectable
class FireGeysersAttackService(
    private val scheduler: Scheduler,
) {
    @EventSubscriber(sync = true)
    fun on(event: FireGeysersAttackEvent) {
        val attacker = event.attacker
        val world = event.attacker.entityWorld as? ServerWorld ?: return

        val positions = chosePositions(world, attacker.blockPos, event.radius, event.geyserProbability, event.geyserCountLimit)

        createParticles(world, positions, event.indicatorDuration, event.pillarDuration)
        dealDamage(world, positions, event.indicatorDuration, event.pillarDuration, attacker)
    }

    private fun chosePositions(
        world: World,
        startPos: BlockPos,
        radius: Int,
        probability: Double,
        countLimit: Int
    ): List<BlockPos> {
        val possiblePositions = getPossiblePositions(world, startPos, radius)
        val count = min((possiblePositions.size * probability).toInt(), countLimit)
        return possiblePositions.shuffled().take(count)
    }

    private fun getPossiblePositions(
        world: World,
        startPos: BlockPos,
        radius: Int,
    ): List<BlockPos> {
        val squaredRadius = radius * radius

        val possiblePositions = mutableListOf<BlockPos>()

        var heads = listOf(startPos)
        val checkedPositions = mutableSetOf(startPos)
        while (heads.isNotEmpty()) {
            val newHeads = mutableListOf<BlockPos>()

            for (head in heads) {
                if (head.getSquaredDistance(startPos) > squaredRadius) continue

                val below = head.down()
                val belowState = world.getBlockState(below)
                if (belowState.isSolidBlock(world, below)) {
                    possiblePositions.add(head)
                }

                val neighbors = listOf(
                    head.north(),
                    head.south(),
                    head.east(),
                    head.west(),
                    head.up(),
                    head.down()
                )

                for (neighbor in neighbors) {
                    if (neighbor in checkedPositions) continue

                    val state = world.getBlockState(neighbor)
                    if (!state.isSolidBlock(world, neighbor)) {
                        newHeads.add(neighbor)
                        checkedPositions.add(neighbor)
                    }
                }
            }

            heads = newHeads
        }

        return possiblePositions
    }

    private fun createParticles(
        world: ServerWorld,
        positions: List<BlockPos>,
        indicatorDuration: Int,
        pillarDuration: Int,
    ) {
        scheduler.repeatingForDuration(1, indicatorDuration) {
            positions.forEach {
                val centerPos = it.toCenterPos().subtract(0.0, 0.2, 0.0)
                world.spawnParticles(
                    BlockStateParticleEffect(ParticleTypes.BLOCK, world.getBlockState(it.down())),
                    centerPos.x,
                    centerPos.y,
                    centerPos.z,
                    1,
                    0.1,
                    0.1,
                    0.1,
                    0.3
                )
            }
        }

        val halfIndicatorDuration = indicatorDuration / 2
        scheduler.repeatingForDuration(halfIndicatorDuration, 4, halfIndicatorDuration + pillarDuration) {
            positions.forEach {
                val centerPos = it.toCenterPos().subtract(0.0, 0.2, 0.0)
                world.spawnParticles(
                    ParticleTypes.LAVA,
                    centerPos.x,
                    centerPos.y,
                    centerPos.z,
                    1,
                    0.1,
                    0.1,
                    0.1,
                    0.1
                )
            }
        }

        scheduler.delayed(indicatorDuration) {
            positions.forEach {
                val centerPos = it.toCenterPos().subtract(0.0, 0.2, 0.0)
                world.spawnParticles(
                    ParticleTypes.FLAME,
                    centerPos.x,
                    centerPos.y,
                    centerPos.z,
                    7,
                    0.1,
                    0.1,
                    0.1,
                    0.2
                )
            }
        }

        scheduler.repeatingForDuration(indicatorDuration, 2, pillarDuration) {
            positions.forEach {
                val centerPos = it.toCenterPos().subtract(0.0, 0.2, 0.0)
                world.spawnParticles(
                    BlockStateParticleEffect(ParticleTypes.BLOCK, world.getBlockState(it.down())),
                    centerPos.x,
                    centerPos.y,
                    centerPos.z,
                    1,
                    0.1,
                    0.1,
                    0.1,
                    0.3
                )
                world.spawnParticles(
                    CustomParticleTypes.FLAME_PILLAR,
                    centerPos.x,
                    centerPos.y,
                    centerPos.z,
                    1,
                    0.0,
                    0.0,
                    0.0,
                    0.0
                )
                world.spawnParticles(
                    CustomParticleTypes.SMOKE_PILLAR,
                    centerPos.x,
                    centerPos.y,
                    centerPos.z,
                    2,
                    0.0,
                    0.0,
                    0.0,
                    0.0
                )
            }
        }
    }

    private fun dealDamage(
        world: ServerWorld,
        positions: List<BlockPos>,
        indicatorDuration: Int,
        pillarDuration: Int,
        attacker: Entity,
    ) {
        scheduler.delayed(indicatorDuration) {
            val targets = getTargets(world, positions, attacker)
            targets.forEach {
                it.dealGenericAttackDamage(1F, attacker) // TODO deal generic elemental damage without knockback
                it.setOnFireForTicks(80)
                it.addVelocity(0.0, 0.5, 0.0)
                it.velocityDirty = true
            }
        }

        scheduler.repeatingForDuration(indicatorDuration, 2, pillarDuration) {
            val targets = getTargets(world, positions, attacker)
            targets.forEach {
                it.dealGenericAttackDamage(1F, attacker)  // TODO deal generic elemental damage without knockback
                it.setOnFireForTicks(80)
            }
        }
    }

    private fun getTargets(
        world: ServerWorld,
        positions: List<BlockPos>,
        attacker: Entity,
    ): Set<LivingEntity> {
        val targets = mutableSetOf<LivingEntity>()

        positions.forEach {
            val box = Box(it.x - 0.2, it.y - 1.0, it.z - 0.2, it.x + 1.2, it.y + 4.0, it.z + 1.2)
            targets.addAll(world.getEntitiesByClass(LivingEntity::class.java, box) { target -> target != attacker })
        }

        return targets
    }
}