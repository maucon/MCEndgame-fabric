package de.fuballer.mcendgame.main.component.dungeon.enemy.boss

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.addCustomAttribute
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonBossDeathEvent
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonFinalBossDeathEvent
import de.fuballer.mcendgame.main.messaging.misc.LivingEntityDamagedEvent
import de.fuballer.mcendgame.main.util.extension.mixin.EntityMixinExtension.isDungeonBoss
import de.fuballer.mcendgame.main.util.extension.mixin.WorldMixinExtension.getBossesKilled
import de.fuballer.mcendgame.main.util.extension.mixin.WorldMixinExtension.getTotalBossCount
import de.fuballer.mcendgame.main.util.extension.mixin.WorldMixinExtension.increaseBossesKilled
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventGateway
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld

@Injectable
object DungeonBossService {
    @EventSubscriber(sync = true)
    fun on(event: DungeonBossDeathEvent) {
        val world = event.world as? ServerWorld ?: return
        world.increaseBossesKilled()

        if (world.getBossesKilled() < world.getTotalBossCount()) return
        val finalBossKilledEvent = DungeonFinalBossDeathEvent.of(event)
        EventGateway.publish(finalBossKilledEvent)
    }

    @EventSubscriber(sync = true)
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

    fun enhanceBoss(boss: MobEntity) {
        val world = boss.entityWorld as? ServerWorld ?: return
        val killedBosses = world.getBossesKilled()
        if (killedBosses == 0) return

        DungeonBossSettings.getAttributePerKilledBoss(killedBosses).forEach { boss.addCustomAttribute(it) }
    }
}