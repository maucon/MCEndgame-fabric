package de.fuballer.mcendgame.components.custom_attributes.types

import de.fuballer.mcendgame.components.custom_attributes.AttributeFormats
import de.fuballer.mcendgame.components.custom_attributes.data.VanillaAttributeType
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import kotlin.reflect.KVisibility
import kotlin.reflect.full.memberProperties

object VanillaAttributeTypes {
    val ARMOR = VanillaAttributeType(EntityAttributes.ARMOR.value(), EntityAttributeModifier.Operation.ADD_VALUE, "armor", AttributeFormats.SIGNED_INT_ROLL, AttributeFormats.INT_BOUNDS)

    // region get by key
    fun getByKey(key: String): VanillaAttributeType {
        return attributeTypes[key]!!
    }

    private val attributeTypes = VanillaAttributeTypes::class.memberProperties
        .filter { it.visibility == KVisibility.PUBLIC }
        .map { it(VanillaAttributeTypes) as VanillaAttributeType }
        .associateBy { it.key }
    // endregion
}