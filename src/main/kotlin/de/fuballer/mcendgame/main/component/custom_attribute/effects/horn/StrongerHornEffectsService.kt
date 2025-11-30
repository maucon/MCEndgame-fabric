package de.fuballer.mcendgame.main.component.custom_attribute.effects.horn

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getAllCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.item.custom.misc.horn.command.HornUseCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class StrongerHornEffectsService {
    @CommandHandler
    fun on(cmd: HornUseCommand) {
        cmd.user.getAllCustomAttributes()[CustomAttributeTypes.STRONGER_HORNS] ?: return
        cmd.isStronger = true
    }
}