package de.fuballer.mcendgame.client.mixin.biped;

import de.fuballer.mcendgame.client.accessor.BipedEntityRenderStateAccessor;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.HashMap;
import java.util.Map;

@Mixin(BipedEntityRenderState.class)
public class BipedEntityRenderStateMixin implements BipedEntityRenderStateAccessor {
    @Unique
    private final Map<HideAblePart, Boolean> hiddenParts = new HashMap<>();

    @Override
    public void mcendgame$setHiddenPart(HideAblePart part, boolean hidden) {
        hiddenParts.put(part, hidden);
    }

    @Override
    public Map<HideAblePart, Boolean> mcendgame$getHiddenParts() {
        return hiddenParts;
    }
}
