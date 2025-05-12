package de.fuballer.mcendgame.main.component.item.custom.armor.interfaces

interface HideBipedBoneArmor {
    val hiddenBones: List<BipedBone>

    enum class BipedBone {
        HEAD,
        BODY,
        ARMS,
        LEGS,
    }
}