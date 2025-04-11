package de.fuballer.client.mcendgame.components.entity.custom.entities.elf_duelist

import de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist.ElfDuelistAttackPose
import net.minecraft.client.render.entity.state.LivingEntityRenderState

class ElfDuelistRenderState : LivingEntityRenderState() {
    var idleAnimationState: Float = 0F //0-1
    var aggressionAnimationState: Float = 0F //0-1

    var prevAttackPose: ElfDuelistAttackPose = ElfDuelistAttackPose.DEFAULT
    var attackPose: ElfDuelistAttackPose = ElfDuelistAttackPose.DEFAULT
    var attackAnimationTime: Float = 0F //time since pose changed in ticks
}