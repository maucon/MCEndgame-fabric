package de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist

import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.MobEntityRenderer
import kotlin.math.max
import kotlin.math.min

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

        renderState.idleAnimationState =
            entity.dataTracker.get(ElfDuelistEntity.IDLE_TICKS) / ElfDuelistEntity.FULL_IDLE_TICKS.toFloat()
        updateAggressionAnimationState(entity, renderState, tickDelta)
    }

    private fun updateAggressionAnimationState(
        entity: ElfDuelistEntity,
        renderState: ElfDuelistRenderState,
        tickDelta: Float
    ) {
        if (entity.dataTracker.get(ElfDuelistEntity.HAS_TARGET)) {
            val timeSinceTargetGained =
                max(entity.age - entity.dataTracker.get(ElfDuelistEntity.TARGET_GAINED) + tickDelta, 0F)
            renderState.aggressionAnimationState = min(timeSinceTargetGained / 10F, 1F)
            return
        }

        val timeSinceTargetLost = max(entity.age - entity.dataTracker.get(ElfDuelistEntity.TARGET_LOST) + tickDelta, 0F)
        renderState.aggressionAnimationState = max(1 - timeSinceTargetLost / 10F, 0F)
    }
}