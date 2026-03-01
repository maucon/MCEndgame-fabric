package de.fuballer.mcendgame.client.component.entity.custom.entities.portal

import de.fuballer.mcendgame.client.component.entity.custom.entities.portal.type.default_.DefaultPortalRenderType
import de.fuballer.mcendgame.client.component.entity.custom.entities.portal.type.PortalRenderType
import net.minecraft.client.render.entity.state.LivingEntityRenderState
import net.minecraft.entity.AnimationState
import software.bernie.geckolib.constant.dataticket.DataTicket

class PortalRenderState : LivingEntityRenderState() {
    var type: PortalRenderType = DefaultPortalRenderType()

    val openAnimationState = AnimationState()
    val idleAnimationState = AnimationState()
    val closeAnimationState = AnimationState()

    override fun getDataMap(): Map<DataTicket<*>, Any> = mapOf()
}