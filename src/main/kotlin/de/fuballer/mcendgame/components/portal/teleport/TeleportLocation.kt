package de.fuballer.mcendgame.components.portal.teleport

import de.fuballer.mcendgame.configuration.RuntimeConfig
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.Identifier
import net.minecraft.util.math.Vec3d

private const val WORLD_ID = "WorldKey"
private const val VEC_X = "X"
private const val VEC_Y = "Y"
private const val VEC_Z = "Z"
private const val X_ROT = "RotationX"
private const val Y_ROT = "RotationY"

data class TeleportLocation(
    val world: ServerWorld,
    val coordinates: Vec3d,
    val xRot: Float = 0.0F,
    val yRot: Float = 0.0F
) {
    fun toNBT(): NbtCompound {
        val nbt = NbtCompound()

        nbt.putString(WORLD_ID, world.registryKey.value.toString())

        nbt.putDouble(VEC_X, coordinates.x)
        nbt.putDouble(VEC_Y, coordinates.y)
        nbt.putDouble(VEC_Z, coordinates.z)

        nbt.putFloat(X_ROT, xRot)
        nbt.putFloat(Y_ROT, yRot)

        return nbt
    }

    companion object {
        fun fromNBT(nbt: NbtCompound): TeleportLocation? {
            val worldIdString = nbt.getString(WORLD_ID)
            val (worldIdNamespace, worldIdPath) = worldIdString.split(":")
            val worldIdentifier = Identifier.of(worldIdNamespace, worldIdPath)

            val worlds = RuntimeConfig.SERVER.worlds
            val world = worlds.firstOrNull { it.registryKey.value == worldIdentifier }
                ?: return null

            val vecX = nbt.getDouble(VEC_X)
            val vecY = nbt.getDouble(VEC_Y)
            val vecZ = nbt.getDouble(VEC_Z)
            val coords = Vec3d(vecX, vecY, vecZ)

            val xRot = nbt.getFloat(X_ROT)
            val yRot = nbt.getFloat(Y_ROT)

            return TeleportLocation(world, coords, xRot, yRot)
        }
    }
}