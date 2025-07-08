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
    val DODGE_PER_MAX_HEART_BELOW_TEN = CustomAttributeType("dodge_per_max_heart_below_ten", AttributeFormats.PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val DODGE_IF_NOT_DODGED_IN_LAST_SECONDS = CustomAttributeType("dodge_if_not_dodged_in_last_seconds", AttributeFormats.PERCENT_AND_INT_ROLL, AttributeFormats.PERCENT_AND_INT_BOUNDS)
    val PROJECTILE_DODGE = CustomAttributeType("projectile_dodge", AttributeFormats.PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val LESS_DAMAGE_TAKEN = CustomAttributeType("less_damage_taken", AttributeFormats.PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)

    // OFFENSE
    val ELEMENTAL_DAMAGE = CustomAttributeType("elemental_damage", AttributeFormats.SIGNED_DOUBLE_ROLL, AttributeFormats.DOUBLE_BOUNDS)
    val INCREASED_DAMAGE = CustomAttributeType("increased_damage", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val INCREASED_ELEMENTAL_DAMAGE = CustomAttributeType("increased_elemental_damage", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val INCREASED_PROJECTILE_DAMAGE = CustomAttributeType("increased_projectile_damage", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)

    val INCREASED_DAMAGE_AGAINST_FULL_HEALTH = CustomAttributeType("increased_damage_against_full_health", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val INCREASED_DAMAGE_WHILE_LOW_HEALTH = CustomAttributeType("increased_damage_while_low_health", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val LESS_DAMAGE_TAKEN_WHILE_HIGH_HEALTH = CustomAttributeType("less_damage_taken_while_high_health", AttributeFormats.PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)

    // POISON
    val INCREASED_DAMAGE_WHILE_POISONED = CustomAttributeType("increased_damage_while_poisoned", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val INCREASED_ATTACK_DAMAGE_WHILE_POISONED = CustomAttributeType("increased_attack_damage_while_poisoned", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val INCREASED_ELEMENTAL_DAMAGE_WHILE_POISONED = CustomAttributeType("increased_elemental_damage_while_poisoned", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val REDUCED_DAMAGE_TAKEN_WHILE_POISONED = CustomAttributeType("reduced_damage_taken_while_poisoned", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val DODGE_WHILE_POISONED = CustomAttributeType("dodge_while_poisoned", AttributeFormats.PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val INCREASED_MOVEMENT_SPEED_WHILE_POISONED = CustomAttributeType("increased_movement_speed_while_poisoned", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val MAGIC_FIND_WHILE_POISONED = CustomAttributeType("magic_find_while_poisoned", AttributeFormats.SIGNED_INT_ROLL, AttributeFormats.INT_BOUNDS)
    val POISON_DAMAGE_IMMUNITY = CustomAttributeType("poison_damage_immunity", AttributeFormats.EMPTY_ROLL, AttributeFormats.EMPTY_BOUNDS)

    // WITHER
    val INCREASED_DAMAGE_WHILE_WITHERED = CustomAttributeType("increased_damage_while_withered", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val INCREASED_ATTACK_DAMAGE_WHILE_WITHERED = CustomAttributeType("increased_attack_damage_while_withered", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val ARMOR_WHILE_WITHERED = CustomAttributeType("armor_while_withered", AttributeFormats.SIGNED_DOUBLE_ROLL, AttributeFormats.DOUBLE_BOUNDS)
    val WITHER_DAMAGE_IMMUNITY = CustomAttributeType("wither_damage_immunity", AttributeFormats.EMPTY_ROLL, AttributeFormats.EMPTY_BOUNDS)

    // MISC
    val MAGIC_FIND = CustomAttributeType("magic_find", AttributeFormats.SIGNED_INT_ROLL, AttributeFormats.INT_BOUNDS)
    val MAGIC_FIND_PER_MAX_HEART = CustomAttributeType("magic_find_per_max_heart", AttributeFormats.SIGNED_INT_ROLL, AttributeFormats.INT_BOUNDS)
    val INCREASED_MOVEMENT_SPEED_AFTER_DODGING =
        CustomAttributeType("increased_movement_speed_after_dodging", AttributeFormats.SIGNED_PERCENT_AND_INT_ROLL, AttributeFormats.PERCENT_AND_INT_BOUNDS)

    val SHOOT_WITHER_SKULL_WHEN_HIT_BY_PROJECTILE = CustomAttributeType("shoot_wither_skull_when_hit_by_projectile", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)

    val BURNING_ENEMIES_EXPLODE_WHEN_KILLED = CustomAttributeType("burning_enemies_explode_when_killed", AttributeFormats.PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)

    val INCREASED_HEALING_RECEIVED = CustomAttributeType("increased_healing_received", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val MORE_HEALING_RECEIVED = CustomAttributeType("more_healing_received", AttributeFormats.PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)

    val FURY_ON_KILL = CustomAttributeType("fury_on_kill", AttributeFormats.EMPTY_ROLL, AttributeFormats.EMPTY_BOUNDS)
    val RESILIENCE_ON_DAMAGE_TAKEN = CustomAttributeType("resilience_on_damage_taken", AttributeFormats.EMPTY_ROLL, AttributeFormats.EMPTY_BOUNDS)

    val WOLF_COMPANION = CustomAttributeType("wolf_companion", AttributeFormats.STRING_ROLL, AttributeFormats.STRING_SHOW_ALL_OPTIONS)

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