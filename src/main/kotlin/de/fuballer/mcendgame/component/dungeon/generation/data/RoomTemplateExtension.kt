package de.fuballer.mcendgame.component.dungeon.generation.data

import net.minecraft.structure.StructureTemplate
import net.minecraft.util.math.Vec3i

data class RoomTemplateExtension(
    val template: StructureTemplate,
    val offset: Vec3i,
)