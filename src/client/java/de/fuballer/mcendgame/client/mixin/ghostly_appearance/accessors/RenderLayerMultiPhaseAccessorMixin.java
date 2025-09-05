package de.fuballer.mcendgame.client.mixin.ghostly_appearance.accessors;

import net.minecraft.client.render.RenderLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(RenderLayer.MultiPhase.class)
public interface RenderLayerMultiPhaseAccessorMixin {
    @Accessor("phases")
    RenderLayer.MultiPhaseParameters getPhases();
}
