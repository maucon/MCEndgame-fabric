package de.fuballer.mcendgame.component.entity.custom.goals

import de.fuballer.mcendgame.component.entity.custom.interfaces.CustomAttacksMob
import net.minecraft.entity.mob.MobEntity
import software.bernie.geckolib.animatable.GeoEntity

class CustomAttacksGoal<T>(
    private val attacker: T,
) : DisableAbleGoal() where T : MobEntity, T : GeoEntity, T : CustomAttacksMob<T> {
    override fun canStart(): Boolean {
        if (!super.canStart()) return false
        val target = attacker.target ?: return false
        return target.isAlive
    }

    override fun shouldContinue() = canStart()

    override fun tick() {
        if (!attacker.canAttack()) return
        val attack = attacker.getRandomAttack(attacker) ?: return
        attacker.attack(attacker, attack)
    }
}