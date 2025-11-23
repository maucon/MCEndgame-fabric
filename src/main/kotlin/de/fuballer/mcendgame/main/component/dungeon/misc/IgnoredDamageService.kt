package de.fuballer.mcendgame.main.component.dungeon.misc

import de.fuballer.mcendgame.main.component.damage.IgnoreDamageCommand
import de.fuballer.mcendgame.main.util.extension.WorldExtension.isDungeonWorld
import de.fuballer.mcendgame.main.util.extension.mixin.EntityMixinExtension.isDungeonEnemy
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.entity.LivingEntity

@Injectable
class IgnoredDamageService {
    @CommandHandler
    fun on(cmd: IgnoreDamageCommand) {
        if (!cmd.world.isDungeonWorld()) return
        if (!cmd.entity.isDungeonEnemy()) return

        val attacker = cmd.damageSource.attacker
        if (attacker !is LivingEntity) return
        if (!attacker.isDungeonEnemy()) return

        cmd.ignoreDamage = true
    }
}