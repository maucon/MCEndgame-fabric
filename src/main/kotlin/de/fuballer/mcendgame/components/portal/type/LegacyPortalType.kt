package de.fuballer.mcendgame.components.portal.type

import de.fuballer.mcendgame.components.portal.PortalEntity

class LegacyPortalType : PortalType() {
    companion object {
        const val ID = "legacy"
    }

    override fun getId() = ID

    override fun tickAnimation(portalEntity: PortalEntity) {
        idleAnimationState.startIfNotRunning(portalEntity.age)
    }
}