package de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist

import net.minecraft.client.render.entity.state.LivingEntityRenderState
import net.minecraft.entity.AnimationState

class ElfDuelistRenderState : LivingEntityRenderState() {
    val idleAnimationState: AnimationState = AnimationState()

    var aggressionAnimationState: Float = 0F //0-1
}