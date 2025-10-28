package de.fuballer.mcendgame.client.component.entity.custom.data

import net.minecraft.util.math.Vec3d

data class LinkVertexData(
    val pos: Vec3d,
    val color: Int,
    val light: Int,
    val thicknessFactor: Double,
)