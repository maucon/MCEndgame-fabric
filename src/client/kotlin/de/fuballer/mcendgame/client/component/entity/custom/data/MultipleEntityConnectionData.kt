package de.fuballer.mcendgame.client.component.entity.custom.data

import net.minecraft.util.math.Vec3d

data class MultipleEntityConnectionData(
    var offset: Vec3d = Vec3d.ZERO,
    var originEntity: EntityConnectionPointData = EntityConnectionPointData(),
    var connectedEntities: List<EntityConnectionPointData> = mutableListOf(),
)

data class EntityConnectionPointData(
    var pos: Vec3d = Vec3d.ZERO,
    var blockLight: Int = 0,
    var skyLight: Int = 15,
    var connectionDuration: Float = 0F
)