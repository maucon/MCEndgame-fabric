package de.fuballer.mcendgame.client.mixin.renderer;

import net.minecraft.block.SkullBlock;
import net.minecraft.client.render.block.entity.SkullBlockEntityRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(SkullBlockEntityRenderer.class)
public interface SkullBlockEntityRendererAccessorMixin {
    @Accessor("TEXTURES")
    static Map<SkullBlock.SkullType, Identifier> getTextures() {
        throw new UnsupportedOperationException();
    }
}
