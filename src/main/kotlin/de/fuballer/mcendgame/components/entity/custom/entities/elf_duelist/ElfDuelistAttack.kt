package de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist

data class ElfDuelistAttack(
    val oldPose: ElfDuelistAttackPose,
    val newPose: ElfDuelistAttackPose,
    val animationTime: Float,
    val damageTime: Long,
    val cooldownAfter: Long,
) {
}