package de.fuballer.mcendgame.main.component.dungeon.enemy.boss

import de.fuballer.mcendgame.main.messaging.dungeon.DungeonBossDeathCommand
import de.fuballer.mcendgame.main.messaging.misc.LivingEntityDamagedEvent
import de.fuballer.mcendgame.main.util.extension.mixin.EntityMixinExtension.getLootMultiplier
import de.fuballer.mcendgame.main.util.extension.mixin.EntityMixinExtension.isDungeonBoss
import de.fuballer.mcendgame.main.util.extension.mixin.EntityMixinExtension.setLootMultiplier
import de.fuballer.mcendgame.main.util.extension.mixin.WorldMixinExtension.getBossesKilled
import de.fuballer.mcendgame.main.util.extension.mixin.WorldMixinExtension.increaseBossesKilled
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import kotlin.math.pow

@Injectable
object DungeonBossService {
    @CommandHandler(priority = 0)
    fun on(cmd: DungeonBossDeathCommand) {
        val world = cmd.world as? ServerWorld ?: return
        world.increaseBossesKilled()
    }

    @EventSubscriber
    fun on(event: LivingEntityDamagedEvent) {
        if (!event.damaged.isDungeonBoss()) return
        val mobEntity = event.damaged as? MobEntity ?: return
        if (!mobEntity.isAiDisabled) return

        val player = event.damageSource.attacker as? PlayerEntity
        activateBoss(mobEntity, player)
    }

    fun activateBoss(
        boss: MobEntity,
        activatedBy: PlayerEntity? = null,
    ) {
        boss.isAiDisabled = false
        enhanceBoss(boss)

        if (activatedBy == null) return
        if (activatedBy.isCreative || activatedBy.isSpectator) return
        boss.target = activatedBy
    }

    fun enhanceBoss(
        boss: MobEntity,
    ) {
        val world = boss.world as? ServerWorld ?: return
        val killedBosses = world.getBossesKilled()
        if (killedBosses == 0) return

        //TODO enhance boss once custom entity attributes are added (reduced dmg taken & increased dmg dealt per killed boss)

        var lootMultiplier = boss.getLootMultiplier()
        lootMultiplier *= DungeonBossSettings.LOOT_MULTIPLIER_PER_KILLED_BOSS.pow(killedBosses)
        boss.setLootMultiplier(lootMultiplier)
    }
}