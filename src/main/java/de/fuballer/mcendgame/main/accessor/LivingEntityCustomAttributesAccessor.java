package de.fuballer.mcendgame.main.accessor;

import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttribute;

import java.util.List;

public interface LivingEntityCustomAttributesAccessor {
    void mcendgame$addCustomAttribute(CustomAttribute customAttribute);

    List<CustomAttribute> mcendgame$getCustomAttributes();
}
