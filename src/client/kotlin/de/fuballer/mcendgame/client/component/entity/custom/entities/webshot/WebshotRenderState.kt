package de.fuballer.mcendgame.client.component.entity.custom.entities.webshot

import net.minecraft.client.render.entity.state.ProjectileEntityRenderState
import software.bernie.geckolib.constant.dataticket.DataTicket

class WebshotRenderState : ProjectileEntityRenderState() {
    override fun getDataMap(): Map<DataTicket<*>, Any> = mapOf()
}