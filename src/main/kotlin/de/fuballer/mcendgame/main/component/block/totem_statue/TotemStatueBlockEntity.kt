package de.fuballer.mcendgame.main.component.block.totem_statue

import de.fuballer.mcendgame.main.component.block.CustomBlockEntityTypes
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.math.BlockPos

private const val LEVEL_KEY = "level"

class TotemStatueBlockEntity(
    pos: BlockPos,
    state: BlockState,
) : BlockEntity(CustomBlockEntityTypes.TOTEM_STATUE, pos, state) {
    private var level = 0
    private var isActive = false

    fun getLevel() = level

    fun getLevel(level: Int) {
        this.level = level
        markDirty()
    }

    fun isActive() = isActive

    fun activate() {
        if (isActive) return

        isActive = true
        markDirty()
    }

    override fun writeNbt(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup) {
        super.writeNbt(nbt, registries)
        nbt.putInt(LEVEL_KEY, level)
    }

    override fun readNbt(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup) {
        super.readNbt(nbt, registries)
        level = nbt.getInt(LEVEL_KEY).orElse(0)
    }
}