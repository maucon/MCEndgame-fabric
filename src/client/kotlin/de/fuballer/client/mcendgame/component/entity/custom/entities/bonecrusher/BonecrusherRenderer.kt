package de.fuballer.client.mcendgame.component.entity.custom.entities.bonecrusher

import de.fuballer.mcendgame.components.entity.custom.entities.bonecrusher.BonecrusherEntity
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.state.LivingEntityRenderState
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.base.GeoRenderState

class BonecrusherRenderer<R>(
    context: EntityRendererFactory.Context
) : GeoEntityRenderer<BonecrusherEntity, R>(context, BonecrusherModel()) where R : LivingEntityRenderState, R : GeoRenderState