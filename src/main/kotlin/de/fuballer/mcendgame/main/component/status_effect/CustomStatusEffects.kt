package de.fuballer.mcendgame.main.component.status_effect

import de.fuballer.mcendgame.main.component.status_effect.resilience.ResilienceEffect
import de.fuballer.mcendgame.main.util.minecraft.RegistryUtil
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
object CustomStatusEffects {
    val FURY = RegistryUtil.registerStatusEffect("fury", FuryEffect())
    val RESILIENCE = RegistryUtil.registerStatusEffect("resilience", ResilienceEffect())
}