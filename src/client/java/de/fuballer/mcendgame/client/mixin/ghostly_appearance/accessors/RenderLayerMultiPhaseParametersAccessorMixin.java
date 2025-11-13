package de.fuballer.mcendgame.client.mixin.ghostly_appearance.accessors;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(RenderLayer.MultiPhaseParameters.class)
public interface RenderLayerMultiPhaseParametersAccessorMixin {
    @Accessor("texture")
    RenderPhase.TextureBase getTextureBase();
}
