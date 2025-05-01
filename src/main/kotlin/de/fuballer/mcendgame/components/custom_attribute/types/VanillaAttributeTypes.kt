package de.fuballer.mcendgame.components.custom_attribute.types

import de.fuballer.mcendgame.components.custom_attribute.AttributeFormats
import de.fuballer.mcendgame.components.custom_attribute.data.VanillaAttributeType
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import kotlin.reflect.KVisibility
import kotlin.reflect.full.memberProperties

object VanillaAttributeTypes {
    // DEFENSE
    val ARMOR = VanillaAttributeType(EntityAttributes.ARMOR, EntityAttributeModifier.Operation.ADD_VALUE, "armor", AttributeFormats.SIGNED_DOUBLE_ROLL, AttributeFormats.DOUBLE_BOUNDS)
    val ARMOR_TOUGHNESS = VanillaAttributeType(EntityAttributes.ARMOR_TOUGHNESS, EntityAttributeModifier.Operation.ADD_VALUE, "armor_toughness", AttributeFormats.SIGNED_DOUBLE_ROLL, AttributeFormats.DOUBLE_BOUNDS)
    val MAX_HEALTH = VanillaAttributeType(EntityAttributes.MAX_HEALTH, EntityAttributeModifier.Operation.ADD_VALUE, "max_health", AttributeFormats.SIGNED_DOUBLE_ROLL, AttributeFormats.DOUBLE_BOUNDS)

    // OFFENSE
    val ATTACK_DAMAGE = VanillaAttributeType(EntityAttributes.ATTACK_DAMAGE, EntityAttributeModifier.Operation.ADD_VALUE, "attack_damage", AttributeFormats.SIGNED_DOUBLE_ROLL, AttributeFormats.DOUBLE_BOUNDS)
    val INCREASED_ATTACK_DAMAGE = VanillaAttributeType(EntityAttributes.ATTACK_DAMAGE, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE, "increased_attack_damage", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val INCREASED_ATTACK_SPEED = VanillaAttributeType(EntityAttributes.ATTACK_SPEED, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE, "increased_attack_speed", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)

    // MISC
    val INCREASED_MOVEMENT_SPEED = VanillaAttributeType(EntityAttributes.MOVEMENT_SPEED, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE, "increased_movement_speed", AttributeFormats.SIGNED_PERCENT_ROLL, AttributeFormats.PERCENT_BOUNDS)
    val LUCK = VanillaAttributeType(EntityAttributes.LUCK, EntityAttributeModifier.Operation.ADD_VALUE, "luck", AttributeFormats.SIGNED_DOUBLE_ROLL, AttributeFormats.DOUBLE_BOUNDS)

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