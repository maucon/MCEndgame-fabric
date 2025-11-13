package de.fuballer.mcendgame.client.mixin.ghostly_appearance.accessors;

import net.minecraft.client.render.RenderPhase;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Optional;

@Mixin(RenderPhase.Texture.class)
public interface RenderPhaseTextureAccessorMixin {
    @Accessor("id")
    Optional<Identifier> getId();
}
