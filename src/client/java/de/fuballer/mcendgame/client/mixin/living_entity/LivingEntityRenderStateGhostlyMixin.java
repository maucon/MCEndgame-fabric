package de.fuballer.mcendgame.client.mixin.living_entity;

import de.fuballer.mcendgame.client.accessor.LivingEntityRenderStateGhostlyAccessor;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LivingEntityRenderState.class)
public class LivingEntityRenderStateGhostlyMixin implements LivingEntityRenderStateGhostlyAccessor {
    @Unique
    public boolean isGhostly = false;

    @Override
    public boolean mcendgame$isGhostly() {
        return isGhostly;
    }

    @Override
    public void mcendgame$setGhostly(boolean ghostly) {
        isGhostly = ghostly;
    }
}
