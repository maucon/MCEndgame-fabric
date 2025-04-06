package de.fuballer.mcendgame.components.dungeon.generation.builder

import de.fuballer.mcendgame.components.dungeon.generation.data.PlaceableRoom
import de.fuballer.mcendgame.util.RotationUtil
import de.fuballer.mcendgame.util.Vec3iExtensions.rotateYDeg
import de.fuballer.mcendgame.util.Vec3iExtensions.toBlockPos
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.server.world.ServerWorld
import net.minecraft.structure.StructurePlacementData
import net.minecraft.structure.StructureTemplate
import net.minecraft.util.math.Vec3i

@Injectable
class DungeonBuilderService {
    fun build(
        world: ServerWorld,
        rooms: List<PlaceableRoom>
    ) {
        for (room in rooms) {
            placeTemplate(world, room.type.template, room.position, room.rotation)

            for (extension in room.type.extensions) {
                val rotOffset = extension.offset.rotateYDeg(room.rotation)
                val position = rotOffset.add(room.position)
                placeTemplate(world, extension.template, position, room.rotation)
            }
        }
    }

    private fun placeTemplate(
        world: ServerWorld,
        template: StructureTemplate,
        position: Vec3i,
        rotation: Double // in degree
    ) {
        val structurePlacementData = StructurePlacementData()
            .addProcessor(IgnoreMarkerStructureProcessor())
            .setRotation(RotationUtil.getAsRotation(rotation))

        val pos = position.toBlockPos()
        template.place(world, pos, pos, structurePlacementData, world.random, 2)
    }
}