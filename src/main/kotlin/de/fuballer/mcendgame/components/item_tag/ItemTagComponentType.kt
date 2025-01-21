package de.fuballer.mcendgame.components.item_tag

import de.fuballer.mcendgame.util.CodecExtensions.setOf
import de.fuballer.mcendgame.util.RegistryKeyUtil
import de.fuballer.mcendgame.util.RegistryUtil
import de.maucon.mauconframework.annotation.Injectable
import net.minecraft.component.ComponentType

@Injectable
object ItemTagComponentType {
    private val ITEM_TAG_KEY = RegistryKeyUtil.createDataComponentTypeKey("item_tags")

    val COMPONENT_TYPE: ComponentType<MutableSet<ItemTag>> =
        RegistryUtil.registerDataComponentType(
            ComponentType.builder<MutableSet<ItemTag>>()
                .codec(ItemTag.CODEC.setOf())
                .build(),
            ITEM_TAG_KEY
        )
}