package de.fuballer.client.mcendgame.components.entity.custom.entities.elf_duelist

import de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist.ElfDuelistEntity
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.state.LivingEntityRenderState
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.base.GeoRenderState

class ElfDuelistRenderer<R>(
    context: EntityRendererFactory.Context
) : GeoEntityRenderer<ElfDuelistEntity, R>(context, ElfDuelistModel()) where R : LivingEntityRenderState, R : GeoRenderState