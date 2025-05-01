package de.fuballer.mcendgame.component.portal.type

import de.fuballer.mcendgame.component.portal.PortalEntity

class DefaultPortalType : PortalType() {
    override fun getId() = "default"

    override fun tickAnimation(portalEntity: PortalEntity) {
        idleAnimationState.startIfNotRunning(portalEntity.age)
    }
}