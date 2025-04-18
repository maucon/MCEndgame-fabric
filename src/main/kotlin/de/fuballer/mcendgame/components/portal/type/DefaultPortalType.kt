package de.fuballer.mcendgame.components.portal.type

import de.fuballer.mcendgame.components.portal.PortalEntity

private const val START_ANIMATION_DURATION = 80

class DefaultPortalType : PortalType() {
    override fun getId() = "default"

    override fun tickAnimation(portalEntity: PortalEntity) {
        if (portalEntity.age < START_ANIMATION_DURATION) {
            idleAnimationState.stop()
            openAnimationState.startIfNotRunning(portalEntity.age)
        } else {
            openAnimationState.stop()
            idleAnimationState.startIfNotRunning(portalEntity.age)
        }
    }
}