package de.fuballer.mcendgame.main.component.item.custom.misc.horn

import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.types.VanillaAttributeTypes
import de.fuballer.mcendgame.main.component.item.custom.UniqueAttributesHornItem
import de.fuballer.mcendgame.main.util.extension.EntityExtension.isEnemy
import de.fuballer.mcendgame.main.util.extension.mixin.EntityMixinExtension.isDungeonBoss
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World

private const val NEARBY_ENEMY_RANGE = 10.0

private const val HEAL = 2f
private const val ABSORPTION_DURATION = 200
private const val ABSORPTION_AMPLIFIER = 0

class VerdantEcho(
    settings: Settings,
) : UniqueAttributesHornItem(settings) {
    override fun getCustomAttributes() = listOf(
        RollableCustomAttribute(VanillaAttributeTypes.ATTACK_DAMAGE, 0, DoubleBounds(1.0, 3.0)),
    )

    override fun getAttributeModifierSlot() = AttributeModifierSlot.HAND

    override fun onUse(world: World, user: PlayerEntity) {
        val nearbyEnemies = world.getOtherEntities(user, user.boundingBox.expand(NEARBY_ENEMY_RANGE)) { user.isEnemy(it) && user.distanceTo(it) <= NEARBY_ENEMY_RANGE }
        val nearbyBoss = nearbyEnemies.any { (it as? LivingEntity)?.isDungeonBoss() == true }

        if (user.health < user.maxHealth) user.heal(HEAL)

        val absorption = StatusEffectInstance(StatusEffects.ABSORPTION, ABSORPTION_DURATION, ABSORPTION_AMPLIFIER, false, true, true)
        user.addStatusEffect(absorption)
    }
}