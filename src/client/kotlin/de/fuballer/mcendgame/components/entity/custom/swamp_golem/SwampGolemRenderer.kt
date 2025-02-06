package de.fuballer.mcendgame.components.entity.custom.swamp_golem

import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.MobEntityRenderer
import net.minecraft.client.render.entity.state.BipedEntityRenderState
import net.minecraft.util.Identifier

class SwampGolemRenderer(
    context: EntityRendererFactory.Context,
) :
    MobEntityRenderer<SwampGolemEntity, BipedEntityRenderState, SwampGolemEntityModel<BipedEntityRenderState>>(
        context,
        SwampGolemEntityModel(context.getPart(SwampGolemEntityModel.SWAMP_GOLEM)),
        0.65F //shadow radius
    ) {

    override fun createRenderState(): BipedEntityRenderState =
        BipedEntityRenderState()

    override fun getTexture(state: BipedEntityRenderState?): Identifier {
        return IdentifierUtil.default("textures/entity/swamp_golem/swamp_golem.png")
    }
}