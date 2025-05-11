package de.fuballer.mcendgame.client.accessor;

import java.util.Map;

public interface BipedEntityRenderStateAccessor {
    void mcendgame$setHiddenPart(HideAblePart part, boolean hidden);

    Map<HideAblePart, Boolean> mcendgame$getHiddenParts();

    enum HideAblePart {
        LEGS,
        BOOTS,
        ARMS,
        BODY,
    }
}
