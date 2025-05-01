package de.fuballer.mcendgame.component.portal.type

import de.fuballer.mcendgame.component.portal.PortalEntity

class LegacyPortalType : PortalType() {
    companion object {
        const val ID = "legacy"
    }

    override fun getId() = ID

    override fun tickAnimation(portalEntity: PortalEntity) {
        idleAnimationState.startIfNotRunning(portalEntity.age)
    }
}