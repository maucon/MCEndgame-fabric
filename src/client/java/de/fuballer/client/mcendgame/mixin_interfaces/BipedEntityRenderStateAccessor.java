package de.fuballer.client.mcendgame.mixin_interfaces;

public interface BipedEntityRenderStateAccessor {
    void mcendgame$setHideLegs(boolean hide);
    boolean mcendgame$getHideLegs();

    void mcendgame$setHideBoots(boolean hide);
    boolean mcendgame$getHideBoots();
}
