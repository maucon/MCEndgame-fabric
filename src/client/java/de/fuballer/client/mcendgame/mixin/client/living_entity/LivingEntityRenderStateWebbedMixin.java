package de.fuballer.client.mcendgame.mixin.client.living_entity;

import de.fuballer.client.mcendgame.accessor.LivingEntityRenderStateWebbedAccessor;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LivingEntityRenderState.class)
public class LivingEntityRenderStateWebbedMixin implements LivingEntityRenderStateWebbedAccessor {
    @Unique
    public boolean isWebbed;

    @Override
    public void mcendgame$setWebbed(boolean webbed) {
        isWebbed = webbed;
    }

    @Override
    public boolean mcendgame$isWebbed() {
        return isWebbed;
    }
}
