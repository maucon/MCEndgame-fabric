package de.fuballer.mcendgame.main.component.custom_attribute.data

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.custom_attribute.types.VanillaAttributeTypes
import de.fuballer.mcendgame.main.util.minecraft.CodecUtil
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.registry.entry.RegistryEntry

sealed class AttributeType(
    val key: String,
    val formatRolls: (List<AttributeRoll<*>>) -> List<String>,
    val formatBounds: (List<AttributeBounds<*>>) -> List<String>
) {
    companion object {
        val CODEC: Codec<AttributeType> = CodecUtil.ofTwo(VanillaAttributeType.CODEC, CustomAttributeType.CODEC)
    }
}

class VanillaAttributeType(
    val attribute: RegistryEntry<EntityAttribute>,
    val scaleType: EntityAttributeModifier.Operation,
    key: String,
    formatRolls: (List<AttributeRoll<*>>) -> List<String>,
    formatBounds: (List<AttributeBounds<*>>) -> List<String>
) : AttributeType(key, formatRolls, formatBounds) {
    companion object {
        val CODEC: Codec<VanillaAttributeType> =
            RecordCodecBuilder.create { instance ->
                instance.group(
                    Codec.STRING.fieldOf("vkey").forGetter(VanillaAttributeType::key)
                ).apply(instance, VanillaAttributeTypes::getByKey)
            }
    }
}

class CustomAttributeType(
    key: String,
    formatRolls: (List<AttributeRoll<*>>) -> List<String>,
    formatBounds: (List<AttributeBounds<*>>) -> List<String>
) : AttributeType(key, formatRolls, formatBounds) {
    companion object {
        val CODEC: Codec<CustomAttributeType> =
            RecordCodecBuilder.create { instance ->
                instance.group(
                    Codec.STRING.fieldOf("ckey").forGetter(CustomAttributeType::key)
                ).apply(instance, CustomAttributeTypes::getByKey)
            }
    }
}