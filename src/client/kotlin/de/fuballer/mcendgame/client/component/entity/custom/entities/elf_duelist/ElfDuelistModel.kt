package de.fuballer.mcendgame.client.component.entity.custom.entities.elf_duelist

import de.fuballer.mcendgame.main.component.entity.custom.entities.elf_duelist.ElfDuelistEntity
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.base.GeoRenderState

class ElfDuelistModel : GeoModel<ElfDuelistEntity>() {
    companion object {
        val MODEL_IDENTIFIER = IdentifierUtil.default("geckolib/models/entity/elf_duelist.geo.json")
        val TEXTURE_IDENTIFIER = IdentifierUtil.default("textures/entity/elf_duelist/elf_duelist.png")
        val ANIMATION_IDENTIFIER = IdentifierUtil.default("geckolib/animations/entity/elf_duelist.animation.json")
    }

    override fun getModelResource(renderState: GeoRenderState) = MODEL_IDENTIFIER

    override fun getTextureResource(renderState: GeoRenderState) = TEXTURE_IDENTIFIER

    override fun getAnimationResource(entity: ElfDuelistEntity) = ANIMATION_IDENTIFIER
}