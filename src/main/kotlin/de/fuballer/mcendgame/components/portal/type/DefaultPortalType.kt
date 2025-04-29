package de.fuballer.mcendgame.components.portal.type

import de.fuballer.mcendgame.components.portal.PortalEntity

class DefaultPortalType : PortalType() {
    override fun getId() = "default"

    override fun tickAnimation(portalEntity: PortalEntity) {
        idleAnimationState.startIfNotRunning(portalEntity.age)
    }
}