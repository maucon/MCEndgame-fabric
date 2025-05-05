package de.fuballer.mcendgame.main.component.dungeon.generation.data

import net.minecraft.structure.StructureTemplate
import net.minecraft.util.math.Vec3i

data class RoomTemplateExtension(
    val template: StructureTemplate,
    val offset: Vec3i,
)