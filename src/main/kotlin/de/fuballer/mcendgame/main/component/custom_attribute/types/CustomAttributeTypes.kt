package de.fuballer.mcendgame.main.component.custom_attribute.types

import de.fuballer.mcendgame.main.component.custom_attribute.AttributeFormats
import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttributeType
import kotlin.reflect.KVisibility
import kotlin.reflect.full.memberProperties

object CustomAttributeTypes {
    private val ERROR = CustomAttributeType("error", AttributeFormats.EMPTY_ROLL, AttributeFormats.EMPTY_BOUNDS)

    // DEFENSE
    val WARD = CustomAttributeType("ward", AttributeFormats.SIGNED_DOUBLE_ROLL, AttributeFormats.DOUBLE_BOUNDS)
    val DODGE = CustomAttributeType("dodge", AttributeFormats.PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val LESS_DAMAGE_TAKEN = CustomAttributeType("less_damage_taken", AttributeFormats.PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)

    // OFFENSE
    val ELEMENTAL_DAMAGE = CustomAttributeType("elemental_damage", AttributeFormats.SIGNED_DOUBLE_ROLL, AttributeFormats.DOUBLE_BOUNDS)
    val INCREASED_DAMAGE = CustomAttributeType("increased_damage", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val INCREASED_ELEMENTAL_DAMAGE = CustomAttributeType("increased_elemental_damage", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val INCREASED_PROJECTILE_DAMAGE = CustomAttributeType("increased_projectile_damage", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)

    val INCREASED_DAMAGE_AGAINST_FULL_HEALTH = CustomAttributeType("increased_damage_against_full_health", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val INCREASED_DAMAGE_WHILE_LOW_HEALTH = CustomAttributeType("increased_damage_while_low_health", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val LESS_DAMAGE_TAKEN_WHILE_HIGH_HEALTH = CustomAttributeType("less_damage_taken_while_high_health", AttributeFormats.PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)

    val INCREASED_DAMAGE_WHILE_POISONED = CustomAttributeType("increased_damage_while_poisoned", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val INCREASED_ATTACK_DAMAGE_WHILE_POISONED = CustomAttributeType("increased_attack_damage_while_poisoned", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val INCREASED_ELEMENTAL_DAMAGE_WHILE_POISONED = CustomAttributeType("increased_elemental_damage_while_poisoned", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val REDUCED_DAMAGE_TAKEN_WHILE_POISONED = CustomAttributeType("reduced_damage_taken_while_poisoned", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val DODGE_WHILE_POISONED = CustomAttributeType("dodge_while_poisoned", AttributeFormats.PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val INCREASED_MOVEMENT_SPEED_WHILE_POISONED = CustomAttributeType("increased_movement_speed_while_poisoned", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val MAGIC_FIND_WHILE_POISONED = CustomAttributeType("magic_find_while_poisoned", AttributeFormats.SIGNED_INT_ROLL, AttributeFormats.INT_BOUNDS) //TODO implementation of effects
    val POISON_DAMAGE_IMMUNITY = CustomAttributeType("poison_damage_immunity", AttributeFormats.EMPTY_ROLL, AttributeFormats.EMPTY_BOUNDS)

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