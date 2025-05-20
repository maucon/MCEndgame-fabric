package de.fuballer.mcendgame.main.component.tags

import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey

@Injectable
object CustomTags {
    val DUNGEON_BREAKABLE = createBlockTag("dungeon_break_able")

    private fun createBlockTag(id: String) = TagKey.of(RegistryKeys.BLOCK, IdentifierUtil.default(id))
}