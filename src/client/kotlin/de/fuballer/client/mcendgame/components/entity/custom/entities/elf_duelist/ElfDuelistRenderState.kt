package de.fuballer.client.mcendgame.components.entity.custom.entities.elf_duelist

import net.minecraft.client.render.entity.state.LivingEntityRenderState

class ElfDuelistRenderState : LivingEntityRenderState() {
    var idleAnimationState: Float = 0F //0-1
    var aggressionAnimationState: Float = 0F //0-1

    var prevAttackPose: de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist.ElfDuelistAttackPose =
        de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist.ElfDuelistAttackPose.DEFAULT
    var attackPose: de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist.ElfDuelistAttackPose =
        de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist.ElfDuelistAttackPose.DEFAULT
    var attackAnimationTime: Float = 0F //time since pose changed in ticks
}