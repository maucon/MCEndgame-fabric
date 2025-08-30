package de.fuballer.mcendgame.client.accessor;

public interface LivingEntityRenderStateAccessor {
    float mcendgame$getHealth();

    void mcendgame$setHealth(float health);

    float mcendgame$getMaxHealth();

    void mcendgame$setMaxHealth(float maxHealth);

    int mcendgame$getLowHealthTicks20();

    void mcendgame$setLowHealthTicks20(int lowHealthTicks20);

    boolean mcendgame$isGhostly();

    void mcendgame$setGhostly(boolean ghostly);
}
