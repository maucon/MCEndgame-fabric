package de.fuballer.mcendgame.main.client

import de.fuballer.mcendgame.main.client.armor.ArmorRendererProvider
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
object ClientInstances {
    var armorRendererProvider: ArmorRendererProvider? = null
}