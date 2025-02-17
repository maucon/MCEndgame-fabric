package de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist

import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.MobEntityRenderer

class ElfDuelistRenderer(
    context: EntityRendererFactory.Context,
) :
    MobEntityRenderer<ElfDuelistEntity, ElfDuelistRenderState, ElfDuelistEntityModel>(
        context,
        ElfDuelistEntityModel(context.getPart(ElfDuelistEntityModel.ELF_DUELIST)),
        0.4F //shadow
    ) {
    override fun createRenderState(): ElfDuelistRenderState =
        ElfDuelistRenderState()

    override fun getTexture(state: ElfDuelistRenderState) =
        IdentifierUtil.default("textures/entity/elf_duelist/elf_duelist.png")

    override fun updateRenderState(
        entity: ElfDuelistEntity,
        renderState: ElfDuelistRenderState,
        tickDelta: Float
    ) {
        super.updateRenderState(entity, renderState, tickDelta)
        renderState.idleAnimationState.copyFrom(entity.idleAnimationState)
    }
}