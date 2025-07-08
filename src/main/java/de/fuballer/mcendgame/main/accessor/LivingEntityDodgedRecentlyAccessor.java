package de.fuballer.mcendgame.main.accessor;

public interface LivingEntityDodgedRecentlyAccessor {
    void mcendgame$updateDodge();

    boolean mcendgame$hasDodged(int ticks);
}
