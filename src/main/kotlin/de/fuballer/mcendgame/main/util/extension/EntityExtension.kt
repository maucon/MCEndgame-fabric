package de.fuballer.mcendgame.main.util.extension

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asIntRoll
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getAllCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.util.extension.WorldExtension.isDungeonWorld
import de.fuballer.mcendgame.main.util.extension.mixin.EntityMixinExtension.isDungeonEnemy
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.Tameable
import net.minecraft.entity.decoration.ArmorStandEntity
import net.minecraft.entity.mob.Monster
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.entity.passive.IronGolemEntity
import net.minecraft.entity.passive.VillagerEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.Vec3d

object EntityExtension {
    fun LivingEntity.isAlly(entity: Entity): Boolean {
        if (this == entity) return true

        if (this.isOrIsTameableOf(PlayerEntity::class.java) && entity.isOrIsTameableOf(PlayerEntity::class.java)) return true
        if (this.isOrIsTameableOf(Monster::class.java) && entity.isOrIsTameableOf(Monster::class.java)) return true

        return false
    }

    fun LivingEntity.isEnemy(entity: Entity): Boolean {
        if (this == entity) return false

        if (this.isOrIsTameableOf(PlayerEntity::class.java) && entity.isOrIsTameableOf(Monster::class.java)) return true
        if (this.isOrIsTameableOf(Monster::class.java) && entity.isOrIsTameableOf(PlayerEntity::class.java)) return true

        return false
    }

    fun Entity.isValidSecondaryTarget(primaryTarget: Entity, attacker: Entity): Boolean {
        if (this == attacker || this == primaryTarget) return false

        if (world.isDungeonWorld()) {
            return isValidSecondaryTargetInDungeon(primaryTarget)
        }
        return isValidSecondaryTargetOutsideDungeon(primaryTarget, attacker)
    }

    private fun Entity.isValidSecondaryTargetInDungeon(primaryTarget: Entity): Boolean {
        if (this !is LivingEntity || primaryTarget !is LivingEntity) return false
        return this.isDungeonEnemy() == primaryTarget.isDungeonEnemy()
    }

    private fun Entity.isValidSecondaryTargetOutsideDungeon(primaryTarget: Entity, attacker: Entity): Boolean {
        if (this.type == primaryTarget.type) return true

        if (attacker.isOrIsTameableOf(PlayerEntity::class.java)) {
            if (this.isOrIsTameableOf(Monster::class.java)) return true
        }

        if (primaryTarget is ArmorStandEntity) return this is ArmorStandEntity
        if (primaryTarget.isOrIsTameableOf(Monster::class.java)) return this.isOrIsTameableOf(Monster::class.java)
        if (primaryTarget is AnimalEntity) return this is AnimalEntity
        if (primaryTarget is VillagerEntity) return this is VillagerEntity || this is AnimalEntity || this is IronGolemEntity
        if (primaryTarget.isOrIsTameableOf(PlayerEntity::class.java)) return this.isOrIsTameableOf(PlayerEntity::class.java)

        return false
    }

    private fun Entity.isOrIsTameableOf(clazz: Class<*>): Boolean {
        if (clazz.isInstance(this)) return true

        val tameable = this as? Tameable ?: return false
        val owner = tameable.owner ?: return false
        return clazz.isInstance(owner)
    }

    fun Entity.centerPos(): Vec3d = pos.add(0.0, height.toDouble(), 0.0)

    const val DEFAULT_BOW_FULL_PULL_TICKS = 20
    fun LivingEntity.getBowFullPullTicks(): Int {
        return getAdditionalBowPullTicks() + DEFAULT_BOW_FULL_PULL_TICKS
    }

    fun LivingEntity.getAdditionalBowPullTicks(): Int {
        val attributes = getAllCustomAttributes()[CustomAttributeTypes.BOW_PULL_TICKS] ?: return 0
        return attributes.sumOf { it.rolls[0].asIntRoll().getActualRoll() }
    }
}