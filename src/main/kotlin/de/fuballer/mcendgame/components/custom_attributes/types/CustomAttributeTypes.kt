package de.fuballer.mcendgame.components.custom_attributes.types

import de.fuballer.mcendgame.components.custom_attributes.AttributeFormats
import de.fuballer.mcendgame.components.custom_attributes.data.CustomAttributeType
import kotlin.reflect.KVisibility
import kotlin.reflect.full.memberProperties

object CustomAttributeTypes {
    private val ERROR = CustomAttributeType("error", AttributeFormats.EMPTY_ROLL, AttributeFormats.EMPTY_BOUNDS)

    val INCREASED_PROJECTILE_DAMAGE = CustomAttributeType("increased_projectile", AttributeFormats.SIGNED_INCREASE_ROLL, AttributeFormats.INCREASE_BOUNDS)
    val INCREASED_ELEMENTAL_DAMAGE = CustomAttributeType("increased_elemental_damage", AttributeFormats.INCREASE_ROLL, AttributeFormats.INCREASE_BOUNDS)
    val FLAT_ELEMENTAL_DAMAGE = CustomAttributeType("flat_elemental_damage", AttributeFormats.TWO_INT_ROLL, AttributeFormats.TWO_INT_BOUNDS)
    val DAMAGE_AGAINST_FULL_LIFE = CustomAttributeType("damage_against_full_life", AttributeFormats.INCREASE_ROLL, AttributeFormats.INCREASE_BOUNDS)

    // region get by key
    fun getByKey(key: String): CustomAttributeType {
        return attributeTypes[key] ?: ERROR
    }

    private val attributeTypes = CustomAttributeTypes::class.memberProperties
        .filter { it.visibility == KVisibility.PUBLIC }
        .map { it(CustomAttributeTypes) as CustomAttributeType }
        .associateBy { it.key }
    // endregion
}