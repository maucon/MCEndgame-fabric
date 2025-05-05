package de.fuballer.mcendgame.client.mixin.biped;

import de.fuballer.mcendgame.client.accessor.BipedEntityRenderStateAccessor;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BipedEntityRenderState.class)
public class BipedEntityRenderStateMixin implements BipedEntityRenderStateAccessor {
    @Unique
    private boolean hideLegs = false;
    private boolean hideBoots = false;

    @Override
    public void mcendgame$setHideLegs(boolean hide) {
        hideLegs = hide;
    }

    @Override
    public boolean mcendgame$getHideLegs() {
        return hideLegs;
    }

    @Override
    public void mcendgame$setHideBoots(boolean hide) {
        hideBoots = hide;
    }

    @Override
    public boolean mcendgame$getHideBoots() {
        return hideBoots;
    }
}
