package de.fuballer.mcendgame.client.component.entity.custom.entities.elf_duelist

import de.fuballer.mcendgame.client.component.entity.custom.feature.isolated.IsolatedGeoLayer
import de.fuballer.mcendgame.main.component.entity.custom.entities.elf_duelist.ElfDuelistEntity
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.state.LivingEntityRenderState
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.base.GeoRenderState

class ElfDuelistRenderer<R>(
    context: EntityRendererFactory.Context
) : GeoEntityRenderer<ElfDuelistEntity, R>(context, ElfDuelistModel()) where R : LivingEntityRenderState, R : GeoRenderState {
    init {
        addRenderLayer(IsolatedGeoLayer(this))
    }
}