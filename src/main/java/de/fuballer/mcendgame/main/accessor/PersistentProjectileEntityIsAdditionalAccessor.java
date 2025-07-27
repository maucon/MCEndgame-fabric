package de.fuballer.mcendgame.main.accessor;

public interface PersistentProjectileEntityIsAdditionalAccessor {
    void mcendgame$setLoadProcessed(Boolean processed);

    boolean mcendgame$hasLoadBeenProcessed();

    void mcendgame$setIsAdditional(Boolean isAdditional);

    boolean mcendgame$isAdditional();
}
