package de.fuballer.mcendgame.main.accessor;

import de.fuballer.mcendgame.main.component.custom_attribute.effects.data.AuraStatusEffect;

public interface LivingEntityAuraAccessor {
    void mcendgame$addAllyAuraStatusEffect(AuraStatusEffect effect);

    void mcendgame$addEnemyAuraStatusEffect(AuraStatusEffect effect);
}
