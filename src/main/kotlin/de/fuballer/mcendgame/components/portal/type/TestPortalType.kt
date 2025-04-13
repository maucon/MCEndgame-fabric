package de.fuballer.mcendgame.components.portal.type

import de.fuballer.mcendgame.components.portal.PortalEntity

class TestPortalType : PortalType() {
    companion object {
        const val ID = "test"
    }

    override fun getId() = ID

    override fun tickAnimation(portalEntity: PortalEntity) {
        openAnimationState.startIfNotRunning(portalEntity.age)
    }
}