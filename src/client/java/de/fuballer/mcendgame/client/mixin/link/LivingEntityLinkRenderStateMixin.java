package de.fuballer.mcendgame.client.mixin.link;

import de.fuballer.mcendgame.client.accessor.LivingEntityLinkRenderStateAccessor;
import de.fuballer.mcendgame.client.component.entity.custom.data.MultipleEntityConnectionData;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LivingEntityRenderState.class)
public class LivingEntityLinkRenderStateMixin implements LivingEntityLinkRenderStateAccessor {
    @Unique
    private double maxLinkDistance = 0.0;
    @Unique
    private MultipleEntityConnectionData linksData = new MultipleEntityConnectionData();

    @Override
    public void mcendgame$setMaxLinkDistance(double distance) {
        maxLinkDistance = distance;
    }

    @Override
    public double mcendgame$getMaxLinkDistance() {
        return maxLinkDistance;
    }

    @Override
    public void mcendgame$setLinksData(MultipleEntityConnectionData data) {
        linksData = data;
    }

    @Override
    public MultipleEntityConnectionData mcendgame$getLinksData() {
        return linksData;
    }
}
