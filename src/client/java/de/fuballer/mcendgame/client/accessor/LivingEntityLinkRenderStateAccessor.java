package de.fuballer.mcendgame.client.accessor;

import de.fuballer.mcendgame.client.component.entity.custom.data.MultipleEntityConnectionData;

public interface LivingEntityLinkRenderStateAccessor {
    void mcendgame$setMaxLinkDistance(double distance);

    double mcendgame$getMaxLinkDistance();

    void mcendgame$setLinksData(MultipleEntityConnectionData data);

    MultipleEntityConnectionData mcendgame$getLinksData();
}
