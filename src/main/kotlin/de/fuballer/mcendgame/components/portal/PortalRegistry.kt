package de.fuballer.mcendgame.components.portal

import de.fuballer.mcendgame.util.RegistryUtil
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup

object PortalRegistry {
    fun <T : AbstractPortalEntity> register(
        name: String,
        factory: EntityType.EntityFactory<T>,
        hitboxWidth: Float = PortalSettings.DEFAULT_HITBOX_WIDTH,
        hitboxHeight: Float = PortalSettings.DEFAULT_HITBOX_HEIGHT,
    ): EntityType<T> =
        RegistryUtil.registerEntity(
            "${name}_portal",
            EntityType.Builder.create(factory, SpawnGroup.MISC)
                .dimensions(hitboxWidth, hitboxHeight)
        )
}