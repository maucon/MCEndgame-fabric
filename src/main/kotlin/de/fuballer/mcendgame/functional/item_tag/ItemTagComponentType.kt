package de.fuballer.mcendgame.functional.item_tag

import de.fuballer.mcendgame.util.CodecExtensions.setOf
import de.fuballer.mcendgame.util.RegistryUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.component.ComponentType

@Injectable
object ItemTagComponentType {
    val COMPONENT_TYPE: ComponentType<MutableSet<ItemTag>> =
        RegistryUtil.registerDataComponentType(
            ComponentType.builder<MutableSet<ItemTag>>()
                .codec(ItemTag.CODEC.setOf())
                .build(),
            "item_tags"
        )
}