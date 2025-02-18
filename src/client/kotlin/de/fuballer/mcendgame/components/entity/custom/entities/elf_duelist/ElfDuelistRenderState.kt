package de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist

import net.minecraft.client.render.entity.state.LivingEntityRenderState

class ElfDuelistRenderState : LivingEntityRenderState() {
    var idleAnimationState: Float = 0F //0-1
    var aggressionAnimationState: Float = 0F //0-1
}