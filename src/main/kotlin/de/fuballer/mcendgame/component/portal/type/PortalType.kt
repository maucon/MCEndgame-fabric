package de.fuballer.mcendgame.component.portal.type

import de.fuballer.mcendgame.component.portal.PortalEntity
import net.minecraft.entity.AnimationState

private val PORTAL_TYPES = mapOf(
    LegacyPortalType.ID to { LegacyPortalType() }
)

abstract class PortalType {
    val openAnimationState = AnimationState()
    val idleAnimationState = AnimationState()
    val closeAnimationState = AnimationState()

    abstract fun getId(): String

    abstract fun tickAnimation(portalEntity: PortalEntity)

    companion object {
        fun getById(typeName: String?): PortalType {
            val typeSupplier = PORTAL_TYPES[typeName]
                ?: { DefaultPortalType() }

            return typeSupplier()
        }
    }
}