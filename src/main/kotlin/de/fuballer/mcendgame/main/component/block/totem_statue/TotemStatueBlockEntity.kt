package de.fuballer.mcendgame.main.component.block.totem_statue

import de.fuballer.mcendgame.main.component.block.CustomBlockEntityTypes
import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.encounters.totem.TotemEncounterSettings
import de.fuballer.mcendgame.main.util.extension.mixin.WorldMixinExtension.getDungeonLevel
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.listener.ClientPlayPacketListener
import net.minecraft.network.packet.Packet
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.RegistryWrapper
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3i
import net.minecraft.world.World

private const val ACTIVE_TICKS_KEY = "active_ticks"

class TotemStatueBlockEntity(
    pos: BlockPos,
    state: BlockState,
) : BlockEntity(CustomBlockEntityTypes.TOTEM_STATUE, pos, state) {
    private var activeTicks = -1
    private var spawnPositions = listOf<BlockPos>()

    companion object {
        private const val NEARBY = 2
        private val NEARBY_BLOCKS_OFFSET = getNearbyBlockOffsets()

        private fun getNearbyBlockOffsets() =
            (-NEARBY..NEARBY).flatMap { x -> (-NEARBY..NEARBY).map { z -> Vec3i(x, 0, z) } }.filter { it.x != 0 || it.z != 0 }

        fun tick(
            world: World,
            blockPos: BlockPos,
            state: BlockState,
            entity: TotemStatueBlockEntity,
        ) {
            if (!entity.isActive()) return
            entity.activeTicks++

            val serverWorld = world as? ServerWorld ?: return
            val ticks = entity.activeTicks
            when (ticks) {
                1 -> entity.createActivationParticles(serverWorld)
                50 -> entity.createSpawnPreparationParticles(serverWorld)
                100 -> entity.spawnEnemies(serverWorld)
            }

            if (ticks >= 20 && ticks % 5 == 0) entity.createActiveParticles(serverWorld)
        }
    }

    fun getActiveTicks() = activeTicks

    private fun isActive() = activeTicks >= 0

    fun tryActivate() {
        if (isActive()) return
        activeTicks = 0
        sync()
        activate()
    }

    private fun activate() {
        val serverWorld = world as? ServerWorld ?: return
        val level = serverWorld.getDungeonLevel()
        val enemyCount = TotemEncounterSettings.getEnemyCount(level)
        spawnPositions = getNearbyBlockPos(enemyCount)
    }

    private fun getNearbyBlockPos(count: Int): List<BlockPos> {
        val fullListCount = (count / NEARBY_BLOCKS_OFFSET.size).toInt()
        val offsets = List(fullListCount) { NEARBY_BLOCKS_OFFSET }.flatten().toMutableList()

        val remaining = count % NEARBY_BLOCKS_OFFSET.size
        val randomPicks = NEARBY_BLOCKS_OFFSET.shuffled().take(remaining)
        offsets.addAll(randomPicks)

        val blockPos = offsets.map { pos.add(it) }
        return blockPos
    }

    private fun createActivationParticles(world: ServerWorld) =
        world.spawnParticles(ParticleTypes.REVERSE_PORTAL, pos.x + 0.5, pos.y + 0.5, pos.z + 0.5, 35, 0.0, 0.0, 0.0, 0.1)

    private fun createActiveParticles(world: ServerWorld) =
        world.spawnParticles(ParticleTypes.END_ROD, pos.x + 0.5, pos.y + 0.5, pos.z + 0.5, 1, 0.0, 0.0, 0.0, 0.1)

    private fun createSpawnPreparationParticles(world: ServerWorld) = spawnPositions.forEach {
        world.spawnParticles(ParticleTypes.PORTAL, it.x + 0.5, it.y + 0.5, it.z + 0.5, 15, 0.0, 0.0, 0.0, 0.7)
    }

    private fun spawnEnemies(world: ServerWorld) = spawnPositions.forEach {
        world.spawnParticles(ParticleTypes.CLOUD, it.x + 0.5, it.y + 0.5, it.z + 0.5, 10, 0.1, 0.1, 0.1, 0.04)
        EntityType.ZOMBIE.spawn(world, it, SpawnReason.SPAWNER)
    }

    override fun writeNbt(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup) {
        super.writeNbt(nbt, registries)
        nbt.putInt(ACTIVE_TICKS_KEY, activeTicks)
    }

    override fun readNbt(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup) {
        super.readNbt(nbt, registries)
        activeTicks = nbt.getInt(ACTIVE_TICKS_KEY).orElse(-1)
    }

    override fun toUpdatePacket(): Packet<ClientPlayPacketListener> = BlockEntityUpdateS2CPacket.create(this)

    override fun toInitialChunkDataNbt(registries: RegistryWrapper.WrapperLookup): NbtCompound = createNbt(registries)

    private fun sync() {
        val serverWorld = world as? ServerWorld ?: return
        serverWorld.chunkManager.markForUpdate(pos)
    }
}